package org.ditto.lib.repository;

import android.arch.lifecycle.LiveData;

import org.ditto.lib.apirest.ApirestFascade;
import org.ditto.lib.apirest.util.AppExecutors;
import org.ditto.lib.apirest.GithubService;
import org.ditto.lib.dbroom.RoomFascade;
import org.ditto.lib.dbroom.buyanswer.Buyanswer;
import org.ditto.lib.dbroom.buyanswer.BuyanswerCommand;
import org.ditto.lib.dbroom.buyanswer.BuyanswerContent;
import org.ditto.lib.dbroom.buyanswer.BuyanswerMessage;
import org.ditto.lib.dbroom.vo.VoGeofence;
import org.ditto.lib.dbroom.vo.VoText;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Repository that handles VoUser objects.
 */
@Singleton
public class BuyanswerRepository {

    private ApirestFascade apirestFascade;
    private RoomFascade roomFascade;

    @Inject
    public BuyanswerRepository(ApirestFascade apirestFascade, RoomFascade roomFascade) {
        this.roomFascade = roomFascade;
        this.apirestFascade = apirestFascade;
    }

    public Observable<Long> save(Buyanswer buyanswer) {
        return Observable.fromCallable(
                () -> roomFascade.daoBuyanswer.save(buyanswer)
        ).subscribeOn(Schedulers.io());
    }


    public Observable<Long> save(BuyanswerCommand buyanswerCommand) {
        return Observable.fromCallable(
                () -> {
                    //TODO: test create buyanswer start -----
                    if (BuyanswerCommand.Create.class.getSimpleName().equals(buyanswerCommand.type)) {
                        Buyanswer buyanswer = Buyanswer.builder()
                                .setUuid(buyanswerCommand.buyanswerUuid)
                                .setVoGeofence(VoGeofence
                                        .builder()
                                        .setCenterAddress("测试地址")
                                        .setLat(0).setLon(0).setRadius(13333)
                                        .build())
                                .setTime2finish(333)
                                .setTitle("测试文章" + buyanswerCommand.buyanswerUuid)
                                .build();
                        roomFascade.daoBuyanswer.save(buyanswer);
                        for (int i = 0; i < 5; i++) {
                            BuyanswerMessage buyanswerMessage = BuyanswerMessage.builder()
                                    .setUuid(UUID.randomUUID().toString())
                                    .setBuyanswerUuid(buyanswerCommand.buyanswerUuid)
                                    .setFloor(i)
                                    .setVoText(VoText.builder().setDetail("test text detail buyanswermessage" + i).build())
                                    .build();
                            roomFascade.daoBuyanswer.save(buyanswerMessage);
                        }
                    }
                    //TODO: test create buyanswer end-----

                    return roomFascade.daoBuyanswer.save(buyanswerCommand);
                }).subscribeOn(Schedulers.io());
    }

    public Observable<Long> save(BuyanswerContent content) {
        return Observable.fromCallable(
                () -> roomFascade.daoBuyanswer.save(content)
        ).subscribeOn(Schedulers.io());

    }


    public LiveData<Buyanswer> loadLive(String buyanswerId) {
        return roomFascade.daoBuyanswer.loadLive(buyanswerId);
    }


    public LiveData<List<BuyanswerCommand>> listCommandsBy(String commandType, boolean descending, int size) {
        return roomFascade.daoBuyanswer.listCommandsBy(commandType, size);
    }

    public LiveData<List<BuyanswerCommand>> listCommandsBy(String buyanswerId, String commandType, boolean descending, int size) {
        return roomFascade.daoBuyanswer.listCommandsBy(buyanswerId, commandType, size);
    }

    public LiveData<BuyanswerCommand> findLatestCommandBy(String buyanswerId, String comandType) {
        return roomFascade.daoBuyanswer.findLatestCommandBy(buyanswerId, comandType);
    }

    public LiveData<List<Buyanswer>> listBuyanswersBy(int size) {
        return roomFascade.daoBuyanswer.listBuyanswersBy(size);
    }

    public LiveData<List<BuyanswerContent>> listContentsBy(String buyanswerId, int size) {
        return roomFascade.daoBuyanswer.listContentsBy(buyanswerId, size);
    }

    public LiveData<List<BuyanswerMessage>> ListMessagesBy(String buyanswerId, int size) {
        return roomFascade.daoBuyanswer.listMessagesBy(buyanswerId, size);
    }
}