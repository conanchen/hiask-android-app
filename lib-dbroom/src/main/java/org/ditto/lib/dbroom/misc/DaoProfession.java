package org.ditto.lib.dbroom.misc;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by admin on 2017/7/28.
 */
@Dao
public interface DaoProfession {

    @Insert(onConflict = REPLACE)
    Long save(Profession profession);


    @Insert(onConflict = REPLACE)
    List<Long> saveAll(List<Profession> professions);

    @Query("SELECT * FROM Profession order by sequence ASC  LIMIT :size")
    LiveData<List<Profession>> listProfessionsBy(int size);

}
