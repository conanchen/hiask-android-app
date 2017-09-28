package org.ditto.lib.repository;

/**
 * Created by admin on 2017/7/28.
 */


import android.arch.lifecycle.LiveData;

import org.ditto.lib.apirest.ApirestFascade;
import org.ditto.lib.dbroom.RoomFascade;
import org.ditto.lib.dbroom.misc.Gift;
import org.ditto.lib.dbroom.misc.Profession;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Repository that handles Gift objects.
 */
@Singleton
public class ProfessionRepository {

    private ApirestFascade apirestFascade;
    private RoomFascade roomFascade;

    @Inject
    public ProfessionRepository(ApirestFascade apirestFascade, RoomFascade roomFascade) {
        this.roomFascade = roomFascade;
        this.apirestFascade = apirestFascade;
    }

    public LiveData<List<Profession>> listProfessionsBy(int size) {
        return roomFascade.daoProfession.listProfessionsBy(size);
    }
}
