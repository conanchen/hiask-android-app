package org.ditto.feature.buyanswer.edit.fragments;

import android.support.v7.widget.RecyclerView.RecycledViewPool;

import com.airbnb.epoxy.AutoModel;
import com.airbnb.epoxy.Typed3EpoxyController;


import org.ditto.feature.buyanswer.R;
import org.ditto.feature.buyanswer.edit.epoxymodels.ItemChooseTime2finishModel_;
import org.ditto.feature.buyanswer.edit.epoxymodels.ItemCommandSetTime2finishModel_;
import org.ditto.feature.buyanswer.edit.epoxymodels.ItemTipsModel_;
import org.ditto.lib.dbroom.buyanswer.Buyanswer;
import org.ditto.lib.dbroom.buyanswer.BuyanswerCommand;

import java.util.List;

public class ControllerEditstepTime2finish extends Typed3EpoxyController<Buyanswer, BuyanswerCommand, List<Long>> {
    public interface AdapterCallbacks {

        void onItemTimeClicked(Long carousel, int position);
    }


    private final AdapterCallbacks callbacks;
    private final RecycledViewPool recycledViewPool;

    @AutoModel
    ItemTipsModel_ tipsModel_;

    @AutoModel
    ItemCommandSetTime2finishModel_ setTime2finishModel_;

    public ControllerEditstepTime2finish(AdapterCallbacks callbacks, RecycledViewPool recycledViewPool) {
        this.callbacks = callbacks;
        this.recycledViewPool = recycledViewPool;
        recycledViewPool.setMaxRecycledViews(R.layout.buyanswer_item_tips_model, 1);

        setDebugLoggingEnabled(true);
    }

    @Override
    protected void buildModels(Buyanswer buyanswer, BuyanswerCommand setTime2finishCommand, List<Long> carousels) {

        if (buyanswer != null) {
            if (buyanswer.time2finish > 0) {
                tipsModel_.tips(String.format("已设定%d分钟内回答问题", buyanswer.time2finish)).addTo(this);
            } else {
                tipsModel_.tips(String.format("请设定 ? 分钟内回答问题")).addTo(this);
            }
        }
        if (setTime2finishCommand != null
                && setTime2finishCommand.content != null &&
                setTime2finishCommand.content.setTime2finish != null) {
            setTime2finishModel_.time(setTime2finishCommand.content.setTime2finish.time2finish).addTo(this);
        }
        if (carousels != null) {
            for (long data : carousels) {
                add(new ItemChooseTime2finishModel_()
                        .id(data)
                        .time(data)
                        .clickListener((model, parentView, clickedView, position) -> {
                            // A model click listener is used instead of a normal click listener so that we can get
                            // the current position of the view. Since the view may have been moved when the colors
                            // were shuffled we can't rely on the position of the model when it was added here to
                            // be correct, since the model won't have been rebound when shuffled.
                            callbacks.onItemTimeClicked(data, position);
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
