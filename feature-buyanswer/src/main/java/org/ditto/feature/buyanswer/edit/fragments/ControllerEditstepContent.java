package org.ditto.feature.buyanswer.edit.fragments;

import android.support.v7.widget.RecyclerView.RecycledViewPool;

import com.airbnb.epoxy.AutoModel;
import com.airbnb.epoxy.Typed4EpoxyController;
import com.google.common.base.Strings;

import org.ditto.feature.buyanswer.create.epoxymodels.ItemCommandCreateBuyanswerModel_;
import org.ditto.feature.buyanswer.edit.epoxymodels.ItemCommandUpsertContentAudioModel_;
import org.ditto.feature.buyanswer.edit.epoxymodels.ItemCommandUpsertContentTextModel_;
import org.ditto.feature.buyanswer.edit.epoxymodels.ItemContentAudioModel_;
import org.ditto.feature.buyanswer.edit.epoxymodels.ItemContentTextModel_;
import org.ditto.feature.buyanswer.edit.epoxymodels.ItemTipsModel_;
import org.ditto.lib.dbroom.buyanswer.Buyanswer;
import org.ditto.lib.dbroom.buyanswer.BuyanswerCommand;
import org.ditto.lib.dbroom.buyanswer.BuyanswerContent;

import java.util.List;

public class ControllerEditstepContent extends Typed4EpoxyController<Buyanswer, List<BuyanswerContent>, BuyanswerCommand, List<BuyanswerCommand>> {

    public interface AdapterCallbacks {

        void onItemCommandClicked(BuyanswerCommand carousel, int colorPosition);
    }


    private final AdapterCallbacks callbacks;
    private final RecycledViewPool recycledViewPool;

    @AutoModel
    ItemTipsModel_ tipsModel_;

    public ControllerEditstepContent(AdapterCallbacks callbacks, RecycledViewPool recycledViewPool) {
        this.callbacks = callbacks;
        this.recycledViewPool = recycledViewPool;
        setDebugLoggingEnabled(true);
    }

    @Override
    protected void buildModels(Buyanswer buyanswer, List<BuyanswerContent> buyanswerContents, BuyanswerCommand buyanswerCreateCommmand, List<BuyanswerCommand> upsertContentCommands) {
        addBuyanswerOrCommandOfCreate(buyanswer, buyanswerCreateCommmand);

        addBuyanswerContents(buyanswerContents);

        addBuyanswerUpsertContentCommands(upsertContentCommands);
    }

    private void addBuyanswerUpsertContentCommands(List<BuyanswerCommand> upsertContentCommands) {
        if (upsertContentCommands != null) {
            for (BuyanswerCommand command : upsertContentCommands) {
                addUpsertContentText(command);
                addUpsertContentAudio(command);
            }
        }
    }

    private void addBuyanswerContents(List<BuyanswerContent> buyanswerContents) {
        if (buyanswerContents != null) {
            for (BuyanswerContent content : buyanswerContents) {
                if (content.vo != null && content.vo.voAudio != null) {
                    add(new ItemContentAudioModel_()
                            .id(content.uuid)
                            .detail(content.vo.voAudio.getDetail()));
                }
                if (content.vo != null && content.vo.voText != null) {
                    add(new ItemContentTextModel_()
                            .id(content.uuid)
                            .detail(content.vo.voText.detail));
                }
            }
        }
    }

    private void addBuyanswerOrCommandOfCreate(Buyanswer buyanswer, BuyanswerCommand buyanswerCreateCommmand) {
        if (buyanswer != null) {
            //buyanswer has been downloaded from cloud server
            if (Strings.isNullOrEmpty(buyanswer.title)) {
                tipsModel_.tips("请提供悬赏问题的标题").addTo(this);
            } else {
                tipsModel_.tips(buyanswer.title).addTo(this);
            }
        } else if (buyanswerCreateCommmand != null
                && buyanswerCreateCommmand.content != null &&
                buyanswerCreateCommmand.content.create != null) {
            add(new ItemCommandCreateBuyanswerModel_()
                    .id(buyanswerCreateCommmand.uuid)
                    .detail(buyanswerCreateCommmand.content.create.title));
        }
    }

    private void addUpsertContentAudio(BuyanswerCommand command) {
        if (command.content != null
                && command.content.upsertContent != null
                && command.content.upsertContent.vo != null
                && command.content.upsertContent.vo.voAudio != null) {
            add(new ItemCommandUpsertContentAudioModel_()
                    .id(command.uuid)
                    .detail(command.type + ": VoText.VoAudio=" + command.content.upsertContent.vo.voAudio.getDetail())
                    .clickListener((model, parentView, clickedView, position) -> {
                        callbacks.onItemCommandClicked(command, position);
                    })
            );
        }
    }

    private void addUpsertContentText(BuyanswerCommand command) {
        if (command.content != null
                && command.content.upsertContent != null
                && command.content.upsertContent.vo != null
                && command.content.upsertContent.vo.voText != null) {
            add(new ItemCommandUpsertContentTextModel_()
                    .id(command.uuid)
                    .detail(command.type + ": VoText.detail=" + command.content.upsertContent.vo.voText.detail)
                    .clickListener((model, parentView, clickedView, position) -> {
                        callbacks.onItemCommandClicked(command, position);
                    })
            );
        }
    }

    @Override
    protected void onExceptionSwallowed(RuntimeException exception) {
        // Best practice is to throw in debug so you are aware of any issues that Epoxy notices.
        // Otherwise Epoxy does its best to swallow these exceptions and continue gracefully
        throw exception;
    }
}
