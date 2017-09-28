package org.ditto.lib.dbroom.user;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;


import org.ditto.lib.dbroom.user.User;
import org.ditto.lib.dbroom.user.UserCommand;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface DaoUser {

    @Insert(onConflict = REPLACE)
    Long save(User user);

    @Insert(onConflict = REPLACE)
    Long save(UserCommand command);

    @Insert(onConflict = REPLACE)
    List<Long>  saveAll(List<Myprofile> myprofiles);

    @Query("SELECT * FROM user WHERE login = :login")
    LiveData<User> load(String login);

    @Query("SELECT * FROM Myprofile order by sequence ASC")
    LiveData<List<Myprofile>> loadAllProfiles();

    @Query("SELECT * FROM Myprofile WHERE type = :type order by sequence ASC LIMIT :size")
    LiveData<Myprofile> loadProfile(String type,int size);

}