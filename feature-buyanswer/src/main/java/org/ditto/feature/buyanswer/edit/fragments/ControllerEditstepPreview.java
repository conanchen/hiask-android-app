package org.ditto.feature.buyanswer.edit.fragments;

import android.support.v7.widget.RecyclerView.RecycledViewPool;

import com.airbnb.epoxy.AutoModel;
import com.airbnb.epoxy.TypedEpoxyController;
import com.google.common.base.Strings;


import org.ditto.feature.buyanswer.R;
import org.ditto.feature.buyanswer.create.epoxymodels.ItemCommandCreateBuyanswerModel_;
import org.ditto.feature.buyanswer.edit.epoxymodels.ItemCommandSetGeofenceModel_;
import org.ditto.feature.buyanswer.edit.epoxymodels.ItemCommandSetGiftModel_;
import org.ditto.feature.buyanswer.edit.epoxymodels.ItemCommandSetTime2finishModel_;
import org.ditto.feature.buyanswer.edit.epoxymodels.ItemCommandUpsertContentAudioModel_;
import org.ditto.feature.buyanswer.edit.epoxymodels.ItemCommandUpsertContentTextModel_;
import org.ditto.feature.buyanswer.edit.epoxymodels.ItemContentAudioModel_;
import org.ditto.feature.buyanswer.edit.epoxymodels.ItemTime2finishModel_;
import org.ditto.feature.buyanswer.edit.epoxymodels.ItemTipsModel_;
import org.ditto.feature.buyanswer.edit.epoxymodels.ItemContentTextModel_;
import org.ditto.feature.buyanswer.edit.epoxymodels.ItemGeofenceModel_;
import org.ditto.feature.buyanswer.edit.epoxymodels.ItemGiftModel_;
import org.ditto.lib.Constants;
import org.ditto.lib.dbroom.buyanswer.Buyanswer;
import org.ditto.lib.dbroom.buyanswer.BuyanswerCommand;
import org.ditto.lib.dbroom.buyanswer.BuyanswerContent;
import org.ditto.lib.dbroom.vo.VoGeofence;
import org.ditto.lib.dbroom.vo.VoGift;

import java.util.List;
import java.util.Map;

public class ControllerEditstepPreview extends TypedEpoxyController<Map<String, Object>> {
    public interface AdapterCallbacks {

        void onGiftClicked(BuyanswerContent carousel, int colorPosition);
    }


    private final AdapterCallbacks callbacks;
    private final RecycledViewPool recycledViewPool;

    @AutoModel
    ItemTipsModel_ tipsModel_;
    @AutoModel
    ItemTime2finishModel_ timeModel_;
    @AutoModel
    ItemGeofenceModel_ geofenceModel_;
    @AutoModel
    ItemGiftModel_ giftModel_;

    public ControllerEditstepPreview(AdapterCallbacks callbacks, RecycledViewPool recycledViewPool) {
        this.callbacks = callbacks;
        this.recycledViewPool = recycledViewPool;
        recycledViewPool.setMaxRecycledViews(R.layout.buyanswer_item_tips_model, 1);

        setDebugLoggingEnabled(true);
    }

    @Override
    protected void buildModels(Map<String, Object> datas) {
//        datas.put(Constants.DATA1, null);//buyanswer
//        datas.put(Constants.DATA2, new ArrayList<BuyanswerContent>());//buyanswerContents
//        datas.put(Constants.DATA3, new BuyanswerCommand());//buyanswerCreateCommand
//        datas.put(Constants.DATA4, new BuyanswerCommand());//buyanswerSetTime2finishCommand
//        datas.put(Constants.DATA5, new BuyanswerCommand());//buyanswerSetGiftCommand
//        datas.put(Constants.DATA6, new BuyanswerCommand());//buyanswerSetGeofenceCommand
//        datas.put(Constants.DATA7, new ArrayList<BuyanswerCommand>());//buyanswerUpsertContentCommand

        addBuyanswerOrCreateCommand(datas, Constants.DATA1, Constants.DATA3);
        addBuyanswerContents(datas, Constants.DATA2);
        addBuyanswerSetTime2finishCommand(datas, Constants.DATA4);
        addBuyanswerSetGiftCommand(datas, Constants.DATA5);
        addBuyanswerSetGeofenceCommand(datas, Constants.DATA6);
        addBuyanswerUpsertContentCommand(datas, Constants.DATA7);

    }

    private void addBuyanswerOrCreateCommand(Map<String, Object> datas, String buyanswerKey, String buyanswerCreateCommmandKey) {
        Buyanswer buyanswer = (Buyanswer) datas.get(buyanswerKey);
        BuyanswerCommand buyanswerCreateCommmand = (BuyanswerCommand) datas.get(buyanswerCreateCommmandKey);
        if (buyanswer != null) {
            if (Strings.isNullOrEmpty(buyanswer.title)) {
                tipsModel_.tips("请提供悬赏问题的标题").addTo(this);
            } else {
                tipsModel_.tips(buyanswer.title).addTo(this);
            }
        } else if (buyanswerCreateCommmand != null && buyanswerCreateCommmand.content != null && buyanswerCreateCommmand.content.create != null) {
            add(new ItemCommandCreateBuyanswerModel_().id(buyanswerCreateCommmand.uuid)
                    .detail(buyanswerCreateCommmand.content.create.title));
        }
    }


    private void addBuyanswerUpsertContentCommand(Map<String, Object> datas, String key) {
        List<BuyanswerCommand> buyanswerCommands = (List<BuyanswerCommand>) datas.get(key);
        if (buyanswerCommands != null) {
            for (BuyanswerCommand buyanswerCommand : buyanswerCommands) {
                this.addUpsertContentAudio(buyanswerCommand);
                this.addUpsertContentText(buyanswerCommand);
                //TODO: ... other upsertContent content content
            }
        }
    }

    private void addUpsertContentText(BuyanswerCommand command) {
        if (command.content.upsertContent != null
                && command.content.upsertContent.vo != null
                && command.content.upsertContent.vo.voText != null) {
            add(new ItemCommandUpsertContentTextModel_()
                    .id(command.uuid)
                    .detail(command.type + ": VoText.detail=" + command.content.upsertContent.vo.voText.detail)
                    .clickListener((model, parentView, clickedView, position) -> {
                        //callbacks.onItemCommandClicked(content, position);
                    })
            );
        }
    }

    private void addUpsertContentAudio(BuyanswerCommand command) {
        if (command.content != null &&
                command.content.upsertContent != null
                && command.content.upsertContent.vo.voAudio != null) {
            add(new ItemCommandUpsertContentAudioModel_()
                    .id(command.uuid)
                    .detail(command.type + ": VoText.VoAudio=" + command.content.upsertContent.vo.voAudio.getDetail())
                    .clickListener((model, parentView, clickedView, position) -> {
                        //callbacks.onItemCommandClicked(content, position);
                    })
            );
        }
    }


    private void addBuyanswerSetGeofenceCommand(Map<String, Object> datas, String key) {
        BuyanswerCommand buyanswerCommand = (BuyanswerCommand) datas.get(key);
        if (buyanswerCommand != null
                && buyanswerCommand.content != null
                && buyanswerCommand.content.setGeofence != null
                && buyanswerCommand.content.setGeofence.voGeofence != null) {
            VoGeofence voGeofence = buyanswerCommand.content.setGeofence.voGeofence;
            add(new ItemCommandSetGeofenceModel_()
                    .id(buyanswerCommand.uuid)
                    .centerAddress(voGeofence.centerAddress)
                    .radius(voGeofence.radius)
            );
        }
    }

    private void addBuyanswerSetGiftCommand(Map<String, Object> datas, String key) {
        BuyanswerCommand buyanswerCommand = (BuyanswerCommand) datas.get(key);
        if (buyanswerCommand != null
                && buyanswerCommand.content != null
                && buyanswerCommand.content.setGift != null
                && buyanswerCommand.content.setGift.voGift != null) {
            VoGift voGift = buyanswerCommand.content.setGift.voGift;
            add(new ItemCommandSetGiftModel_()
                    .id(buyanswerCommand.uuid)
                    .name(voGift.name)
                    .price(voGift.price)
            );
        }
    }


    private void addBuyanswerSetTime2finishCommand(Map<String, Object> datas, String key) {
        BuyanswerCommand buyanswerCommand = (BuyanswerCommand) datas.get(key);
        if (buyanswerCommand != null
                && buyanswerCommand.content != null
                && buyanswerCommand.content.setTime2finish != null) {
            BuyanswerCommand.SetTime2finish setTime2finish = buyanswerCommand.content.setTime2finish;
            add(new ItemCommandSetTime2finishModel_()
                    .id(buyanswerCommand.uuid)
                    .time(setTime2finish.time2finish)
            );
        }
    }


    private void addBuyanswerCreateCommand(Map<String, Object> datas, String key) {
        BuyanswerCommand buyanswerCommand = (BuyanswerCommand) datas.get(key);
        if (buyanswerCommand != null
                && buyanswerCommand.content != null
                && buyanswerCommand.content.create != null) {
            add(new ItemCommandCreateBuyanswerModel_()
                    .id(buyanswerCommand.uuid)
                    .detail(buyanswerCommand.content.create.title)
            );
        }
    }

    private void addBuyanswer(Map<String, Object> datas, String key) {
        Buyanswer buyanswer = (Buyanswer) datas.get(key);
        if (buyanswer != null) {
            tipsModel_.tips(buyanswer.title).addTo(this);
            timeModel_.time(buyanswer.time2finish).addTo(this);
            if (buyanswer.voGeofence != null) {
                geofenceModel_.centerAddress(buyanswer.voGeofence.centerAddress)
                        .radius(buyanswer.voGeofence.radius)
                        .addTo(this);
            }
            if (buyanswer.voGift != null) {
                giftModel_.name(buyanswer.voGift.name)
                        .price(buyanswer.voGift.price)
                        .addTo(this);
            }
        }
    }

    private void addBuyanswerContents(Map<String, Object> datas, String key) {
        List<BuyanswerContent> buyanswerContentList = (List<BuyanswerContent>) datas.get(key);
        if (buyanswerContentList != null) {
            for (BuyanswerContent content : buyanswerContentList) {
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

    @Override
    protected void onExceptionSwallowed(RuntimeException exception) {
        // Best practice is to throw in debug so you are aware of any issues that Epoxy notices.
        // Otherwise Epoxy does its best to swallow these exceptions and continue gracefully
        throw exception;
    }
}
