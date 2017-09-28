package org.ditto.feature.index.message;

import android.support.v7.widget.RecyclerView.RecycledViewPool;

import com.airbnb.epoxy.TypedEpoxyController;

import org.ditto.feature.index.message.epoxymodels.ItemIndexMessageModel_;
import org.ditto.lib.dbroom.index.IndexMessage;

import java.util.List;

public class MessageIndicesController extends TypedEpoxyController<List<IndexMessage>> {
    public interface AdapterCallbacks {
        void onMessageItemClicked(IndexMessage indexMessageIssue, int position);
    }


    private final AdapterCallbacks callbacks;
    private final RecycledViewPool recycledViewPool;

    public MessageIndicesController(AdapterCallbacks callbacks, RecycledViewPool recycledViewPool) {
        this.callbacks = callbacks;
        this.recycledViewPool = recycledViewPool;
        setDebugLoggingEnabled(true);
    }

    @Override
    protected void buildModels(List<IndexMessage> messageIndices) {
        if (messageIndices != null) {
            for (IndexMessage carousel : messageIndices) {
                add(new ItemIndexMessageModel_()
                        .id(carousel.uuid)
                        .title(carousel.title)
                        .detaill(carousel.detail)
                        .clickListener((model, parentView, clickedView, position) -> {
                            // A model click listener is used instead of a normal click listener so that we can get
                            // the current position of the view. Since the view may have been moved when the colors
                            // were shuffled we can't rely on the position of the model when it was added here to
                            // be correct, since the model won't have been rebound when shuffled.
                            callbacks.onMessageItemClicked(carousel, position);
                        })
                );

            }
        }
    }

    @Override
    protected void onExceptionSwallowed(RuntimeException exception) {
        // Best practice is to throw in debug so you are aware of any issues that Epoxy notices.
        // Otherwise Epoxy does its best to swallow these exceptions and continue gracefully
        throw exception;
    }
}
