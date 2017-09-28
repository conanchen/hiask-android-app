package org.ditto.lib.dbroom.buyanswer;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import org.ditto.lib.dbroom.buyanswer.IndexBuyanswer;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface DaoIndexBuyanswer {

    @Insert(onConflict = REPLACE)
    Long save(IndexBuyanswer user);

    @Insert(onConflict = REPLACE)
    Long[] saveAll(IndexBuyanswer... partyIndices);


    @Insert(onConflict = REPLACE)
    List<Long> saveAll(List<IndexBuyanswer> partyIndices);



    @Query("SELECT * FROM IndexBuyanswer ORDER by lastUpdated DESC LIMIT :size")
    LiveData<List<IndexBuyanswer>> listBuyanswerIndicesBy(int size);

}