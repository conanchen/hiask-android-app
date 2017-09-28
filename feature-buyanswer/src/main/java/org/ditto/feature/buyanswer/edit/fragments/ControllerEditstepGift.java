package org.ditto.feature.buyanswer.edit.fragments;

import android.support.v7.widget.RecyclerView.RecycledViewPool;

import com.airbnb.epoxy.AutoModel;
import com.airbnb.epoxy.Typed3EpoxyController;


import org.ditto.feature.buyanswer.R;
import org.ditto.feature.buyanswer.edit.epoxymodels.ItemCommandSetGiftModel_;
import org.ditto.feature.buyanswer.edit.epoxymodels.ItemTipsModel_;
import org.ditto.feature.buyanswer.edit.epoxymodels.ItemChooseGiftModel_;
import org.ditto.lib.dbroom.buyanswer.Buyanswer;
import org.ditto.lib.dbroom.buyanswer.BuyanswerCommand;
import org.ditto.lib.dbroom.vo.VoGift;

import java.util.List;

public class ControllerEditstepGift extends Typed3EpoxyController<Buyanswer, BuyanswerCommand, List<VoGift>> {
    public interface AdapterCallbacks {

        void onGiftClicked(VoGift carousel, int colorPosition);
    }


    private final AdapterCallbacks callbacks;
    private final RecycledViewPool recycledViewPool;

    @AutoModel
    ItemTipsModel_ tipsModel_;

    @AutoModel
    ItemCommandSetGiftModel_ setGiftModel_;


    public ControllerEditstepGift(AdapterCallbacks callbacks, RecycledViewPool recycledViewPool) {
        this.callbacks = callbacks;
        this.recycledViewPool = recycledViewPool;
        recycledViewPool.setMaxRecycledViews(R.layout.buyanswer_item_tips_model, 1);
        recycledViewPool.setMaxRecycledViews(R.layout.buyanswer_item_choose_gift_model, 100);

        setDebugLoggingEnabled(true);
    }

    @Override
    protected void buildModels(Buyanswer buyanswer, BuyanswerCommand setGiftCommand, List<VoGift> voGifts) {
        if (buyanswer != null) {
            if (buyanswer.voGift != null) {
                tipsModel_.tips(String.format("设定问题赏金%d通通币", buyanswer.voGift.price)).addTo(this);
            } else {
                tipsModel_.tips(String.format("请设定问题赏金 ？通通币")).addTo(this);
            }
        }
        if (setGiftCommand != null
                && setGiftCommand.content != null
                && setGiftCommand.content.setGift != null
                && setGiftCommand.content.setGift.voGift != null) {
            VoGift voGift = setGiftCommand.content.setGift.voGift;
            setGiftModel_
                    .name(voGift.name)
                    .price(voGift.price)
                    .addTo(this);
        }

        if (voGifts != null) {
            for (VoGift voGift : voGifts) {
                add(new ItemChooseGiftModel_()
                        .id(voGift.uuid)
                        .name(voGift.name)
                        .price(voGift.price)
                        .clickListener((model, parentView, clickedView, position) -> {
                            // A model click listener is used instead of a normal click listener so that we can get
                            // the current position of the view. Since the view may have been moved when the colors
                            // were shuffled we can't rely on the position of the model when it was added here to
                            // be correct, since the model won't have been rebound when shuffled.
                            callbacks.onGiftClicked(voGift, position);
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
