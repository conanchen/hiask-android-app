package org.ditto.feature.my.myprofile.edit;

import android.support.v7.widget.RecyclerView.RecycledViewPool;

import com.airbnb.epoxy.TypedEpoxyController;

import org.ditto.feature.base.content.models.GiftItemModel_;
import org.ditto.lib.dbroom.misc.Gift;

import java.util.List;


public class ConsultingpriceController extends TypedEpoxyController<List<Gift>> {
    public interface AdapterCallbacks {
        void onGiftClicked(Gift carousel, int colorPosition);
    }


    private final AdapterCallbacks callbacks;
    private final RecycledViewPool recycledViewPool;

    public ConsultingpriceController(AdapterCallbacks callbacks, RecycledViewPool recycledViewPool) {
        this.callbacks = callbacks;
        this.recycledViewPool = recycledViewPool;
        setDebugLoggingEnabled(true);
    }

    @Override
    protected void buildModels(List<Gift> carousels) {
        if (carousels != null) {
            for (Gift carousel:carousels) {
                add(new GiftItemModel_()
                        .id(carousel.uuid)
                        .name(carousel.name)
                        .price(carousel.price)
                        .clickListener((model, parentView, clickedView, position) -> {
                            // A model click listener is used instead of a normal click listener so that we can get
                            // the current position of the view. Since the view may have been moved when the colors
                            // were shuffled we can't rely on the position of the model when it was added here to
                            // be correct, since the model won't have been rebound when shuffled.
                            callbacks.onGiftClicked(carousel, position);
                        }));

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
