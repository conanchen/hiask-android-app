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
public interface DaoGift {

    @Insert(onConflict = REPLACE)
    Long save(Gift gift);


    @Insert(onConflict = REPLACE)
    List<Long> saveAll(List<Gift> gifts);


    @Query("SELECT * FROM Gift order by price ASC  LIMIT :size")
    LiveData<List<Gift>> listGiftsBy(int size);

}
