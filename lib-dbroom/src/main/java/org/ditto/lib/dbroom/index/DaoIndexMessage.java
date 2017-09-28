package org.ditto.lib.dbroom.index;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;


import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface DaoIndexMessage {

    @Insert(onConflict = REPLACE)
    Long save(IndexMessage indexMessage);

    @Insert(onConflict = REPLACE)
    Long[] saveAll(IndexMessage... messageIndices);

    @Insert(onConflict = REPLACE)
    List<Long> saveAll(List<IndexMessage> messageIndices);


    @Query("SELECT * FROM IndexMessage ORDER by lastUpdated DESC LIMIT :size")
    LiveData<List<IndexMessage>> listMessageIndicesBy(int size);
}