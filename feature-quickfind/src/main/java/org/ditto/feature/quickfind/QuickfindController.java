package org.ditto.feature.quickfind;

import android.support.v7.widget.RecyclerView.RecycledViewPool;

import com.airbnb.epoxy.TypedEpoxyController;


import org.ditto.feature.quickfind.models.QuickfindTipsModel_;

import java.util.List;

public class QuickfindController extends TypedEpoxyController<List<QuickfindTipData>> {
    public interface AdapterCallbacks {

        void onQuestionContentClicked(QuickfindTipData carousel, int colorPosition);
    }


    private final AdapterCallbacks callbacks;
    private final RecycledViewPool recycledViewPool;

    public QuickfindController(AdapterCallbacks callbacks, RecycledViewPool recycledViewPool) {
        this.callbacks = callbacks;
        this.recycledViewPool = recycledViewPool;
        setDebugLoggingEnabled(true);
    }

    @Override
    protected void buildModels(List<QuickfindTipData> datas) {

        long now = System.currentTimeMillis();
        for (int i = 0; i < datas.size(); i++) {
            QuickfindTipData data = datas.get(i);
            if(QuickfindTipData.TYPE.TEXT.equals(data.getType())) {
                add(new QuickfindTipsModel_()
                        .id(now + i)
                        .detail(data.getDetail())
                        .clickListener((model, parentView, clickedView, position) -> {
                            // A model click listener is used instead of a normal click listener so that we can get
                            // the current position of the view. Since the view may have been moved when the colors
                            // were shuffled we can't rely on the position of the model when it was added here to
                            // be correct, since the model won't have been rebound when shuffled.
                            callbacks.onQuestionContentClicked(data, position);
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
