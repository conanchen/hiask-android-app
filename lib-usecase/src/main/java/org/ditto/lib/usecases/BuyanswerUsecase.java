package org.ditto.lib.usecases;

import com.google.gson.Gson;

import org.ditto.lib.dbroom.buyanswer.BuyanswerCommand;
import org.ditto.lib.dbroom.buyanswer.BuyanswerContent;
import org.ditto.lib.dbroom.vo.Vo;
import org.ditto.lib.dbroom.vo.VoEmail;
import org.ditto.lib.dbroom.vo.VoGeofence;
import org.ditto.lib.dbroom.vo.VoGift;
import org.ditto.lib.dbroom.vo.VoText;
import org.ditto.lib.repository.RepositoryFascade;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by admin on 2017/6/25.
 */

public class BuyanswerUsecase {

    private RepositoryFascade mRepositoryFascade;
    private static final List<Long> time2finishList = new ArrayList<Long>() {
        {
            long time1 = 1;
            long time2 = 2;
            add(Long.valueOf(time1));
            add(Long.valueOf(time2));
            for (int i = 0; i < 22; i++) {
                long time = time1 + time2;
                add(Long.valueOf(time));
                time1 = time2;
                time2 = time;
            }
        }
    };
    private List<VoGift> voGifts = new ArrayList<VoGift>() {
        {
            int time1 = 1;
            int time2 = 2;
            add(  VoGift.builder().setUuid("uuid" + time1).setName(time1 + "棒棒糖").setPrice( time1).build());
            add(  VoGift.builder().setUuid("uuid" + time2).setName(time2 + "棒棒糖").setPrice( time2).build());
            for (int i = 3; i < 22; i++) {
                int time = time1 + time2;
                add(  VoGift.builder().setUuid("uuid" + time).setName(time + "棒棒糖").setPrice( time).build());
                time1 = time2;
                time2 = time;
            }
        }
    };


    @Inject
    public BuyanswerUsecase(RepositoryFascade repositoryFascade) {
        this.mRepositoryFascade = repositoryFascade;
    }

    public Observable<Long> create() {
            String buyanswerId = UUID.randomUUID().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HH:mm:ss");
            String title = "悬赏问题BuyanswerCommand：请修改标题创建于" + sdf.format(new Date());
            Vo vo = Vo.builder()
                    .setVoText(VoText.builder().setDetail("test VoText on Create Buyanswer").build())
                    .setVoEmail(VoEmail.builder().setTitle("email title").setEmail("coanksksk@xxxxx.com").build())
                    .build();
            BuyanswerCommand buyanswerCommand = BuyanswerCommand.builder()
                    .setBuyanswerUuid(buyanswerId)
                    .setContent(BuyanswerCommand.Content
                            .builder()
                            .setCreate(BuyanswerCommand.Create.builder().setTitle(title).build())
                            .build())
                    .build();
            return mRepositoryFascade.buyanswerRepository.save(buyanswerCommand);
    }

    public Observable<Long> publish(String commandUuid, String buyanswerId) {

            BuyanswerCommand buyanswerCommand = BuyanswerCommand.builder()
                    .setUuid(commandUuid)
                    .setBuyanswerUuid(buyanswerId)
                    .setContent(BuyanswerCommand.Content
                            .builder()
                            .setPublish(BuyanswerCommand.Publish.builder().setPublish(true).build())
                            .build())
                    .build();
            return mRepositoryFascade.buyanswerRepository.save(buyanswerCommand);
    }

    public Observable<Long> updateTitle(String commandUuid, String buyanswerId, String title) {

            BuyanswerCommand buyanswerCommand = BuyanswerCommand.builder()
                    .setUuid(commandUuid)
                    .setBuyanswerUuid(buyanswerId)
                    .setContent(BuyanswerCommand.Content
                            .builder()
                            .setUpdateTitle(
                                    BuyanswerCommand.UpdateTitle
                                            .builder()
                                            .setTitle(title)
                                            .build())
                            .build())
                    .build();
            return mRepositoryFascade.buyanswerRepository.save(buyanswerCommand);
    }

    public Observable<Long> upsertContent(String commandUuid, String buyanswerId, String contentUuid, VoText voText) {

            BuyanswerCommand command;
            command = BuyanswerCommand.builder()
                    .setUuid(commandUuid)
                    .setBuyanswerUuid(buyanswerId)
                    .setContent(
                            BuyanswerCommand.Content.builder()
                                    .setUpsertContent(BuyanswerCommand.UpsertContent
                                            .builder()
                                            .setContentUuid(contentUuid)
                                            .setVo(Vo.builder()
                                                    .setVoText(voText)
                                                    .build())
                                            .build())
                                    .build())
                    .build();

            //TODO:for test
            BuyanswerContent content = BuyanswerContent.builder()
                    .setBuyanswerUuid(buyanswerId)
                    .setUuid(UUID.randomUUID().toString())
                    .setVo(Vo.builder().setVoText(voText).build())
                    .build();
            mRepositoryFascade.buyanswerRepository.save(content);
            //TODO: for test
            return mRepositoryFascade.buyanswerRepository.save(command);
    }

    public Observable<Long> upsertMessage(String commandUuid, String buyanswerId, String messageUuid, VoText voText) {

            BuyanswerCommand command;
            command = BuyanswerCommand.builder()
                    .setUuid(commandUuid)
                    .setBuyanswerUuid(buyanswerId)
                    .setContent(
                            BuyanswerCommand.Content.builder()
                                    .setUpsertMessage(
                                            BuyanswerCommand.UpsertMessage
                                                    .builder()
                                                    .setMessageUuid(messageUuid)
                                                    .setToUserUuid("TESTUSERUUID0001")
                                                    .setVo(Vo
                                                            .builder()
                                                            .setVoText(voText)
                                                            .build())
                                                    .build())
                                    .build())
                    .build();

            return mRepositoryFascade.buyanswerRepository.save(command);
    }

    public Observable<Long> set(String commandUuid, String buyanswerId, BuyanswerCommand.SetTime2finish time2finish) {
            BuyanswerCommand command = BuyanswerCommand.builder()
                    .setUuid(commandUuid)
                    .setBuyanswerUuid(buyanswerId)
                    .setContent(
                            BuyanswerCommand.Content.builder()
                                    .setSetTime2finish(time2finish)
                                    .build())
                    .build();
            return mRepositoryFascade.buyanswerRepository.save(command);
    }

    public Observable<Long> set(String commandUuid, String buyanswerId, VoGeofence voGeofence) {
            BuyanswerCommand command = BuyanswerCommand.builder()
                    .setUuid(commandUuid)
                    .setBuyanswerUuid(buyanswerId)
                    .setContent(
                            BuyanswerCommand.Content
                                    .builder()
                                    .setSetGeofence(BuyanswerCommand.SetGeofence
                                            .builder()
                                            .setVoGeofence(voGeofence)
                                            .build())
                                    .build())
                    .build();
            return mRepositoryFascade.buyanswerRepository.save(command);
    }

    public Observable<Long> set(String commandUuid, String buyanswerId, VoGift voGift) {
            BuyanswerCommand command = BuyanswerCommand.builder()
                    .setUuid(commandUuid)
                    .setBuyanswerUuid(buyanswerId)
                    .setContent(BuyanswerCommand.Content
                            .builder()
                            .setSetGift(BuyanswerCommand.SetGift.builder().setVoGift(voGift).build())
                            .build())
                    .build();
            return mRepositoryFascade.buyanswerRepository.save(command);
    }

    public List<Long> getInitTime2finishList() {
        return time2finishList;
    }

    public List<VoGift> getInitGiftList() {
        return voGifts;
    }
}
