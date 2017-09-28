package org.ditto.lib.repository;

/**
 * Created by admin on 2017/7/28.
 */


import android.arch.lifecycle.LiveData;

import org.ditto.lib.apirest.ApirestFascade;
import org.ditto.lib.dbroom.RoomFascade;
import org.ditto.lib.dbroom.misc.Gift;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Repository that handles Gift objects.
 */
@Singleton
public class GiftRepository {

    private ApirestFascade apirestFascade;
    private RoomFascade roomFascade;

    @Inject
    public GiftRepository(ApirestFascade apirestFascade, RoomFascade roomFascade) {
        this.roomFascade = roomFascade;
        this.apirestFascade = apirestFascade;
    }

    public LiveData<List<Gift>> listGiftsBy(int size) {
        return roomFascade.daoGift.listGiftsBy(size);
    }

    public Observable<List<Long>> saveAll(List<Gift> gifts) {
        return Observable
                .fromCallable(() -> roomFascade.daoGift.saveAll(gifts))
                .subscribeOn(Schedulers.io());
    }

}
