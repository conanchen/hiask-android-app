package org.ditto.lib.dbroom.index;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface DaoIndexParty {

    @Insert(onConflict = REPLACE)
    Long save(IndexParty user);


    @Insert(onConflict = REPLACE)
    Long[] saveAll(IndexParty... partyIndices);


    @Insert(onConflict = REPLACE)
    List<Long> saveAll(List<IndexParty> partyIndices);


    @Query("SELECT * FROM IndexParty ORDER by lastUpdated DESC LIMIT :size")
    LiveData<List<IndexParty>> listPartyIndicesBy(int size);

}