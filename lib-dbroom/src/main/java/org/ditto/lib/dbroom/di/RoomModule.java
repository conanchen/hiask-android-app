package org.ditto.lib.dbroom.di;

import android.arch.persistence.room.Room;
import android.content.Context;

import org.ditto.lib.dbroom.buyanswer.DaoBuyanswer;
import org.ditto.lib.dbroom.buyanswer.DaoIndexBuyanswer;
import org.ditto.lib.dbroom.index.DaoIndexMessage;
import org.ditto.lib.dbroom.MyRoomDatabase;
import org.ditto.lib.dbroom.RoomFascade;
import org.ditto.lib.dbroom.index.DaoIndexParty;
import org.ditto.lib.dbroom.misc.DaoGift;
import org.ditto.lib.dbroom.misc.DaoProfession;
import org.ditto.lib.dbroom.user.DaoUser;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by amirziarati on 10/4/16.
 */
@Singleton
@Module
public class RoomModule {

    @Singleton
    @Provides
    public String provideAmir() {
        return "Amir Was Injected";
    }

    @Singleton
    @Provides
    MyRoomDatabase provideRoomDatabase(Context context) {
        return Room.databaseBuilder(context, MyRoomDatabase.class, "room.db").build();
    }

    @Singleton
    @Provides
    DaoUser provideUserDao(MyRoomDatabase db) {
        return db.daoUser();
    }

    @Singleton
    @Provides
    DaoBuyanswer provideBuyanswerDao(MyRoomDatabase db) {
        return db.daoBuyanswer();
    }

    @Singleton
    @Provides
    DaoIndexMessage provideMessageDao(MyRoomDatabase db) {
        return db.daoMessageIndex();
    }

    @Singleton
    @Provides
    DaoIndexParty provideDaoPartyIndex(MyRoomDatabase db) {
        return db.daoPartyIndex();
    }


    @Singleton
    @Provides
    DaoIndexBuyanswer provideDaoIndexBuyanswer(MyRoomDatabase db) {
        return db.daoIndexBuyanswer();
    }

    @Singleton
    @Provides
    DaoGift provideDaoGift(MyRoomDatabase db) {
        return db.daoGift();
    }

    @Singleton
    @Provides
    DaoProfession provideDaoProfession(MyRoomDatabase db) {
        return db.daoProfession();
    }

    @Singleton
    @Provides
    public RoomFascade provideRoomFascade(DaoUser daoUser,
                                          DaoBuyanswer daoBuyanswer,
                                          DaoIndexMessage daoIndexMessage,
                                          DaoIndexParty daoIndexParty,
                                          DaoIndexBuyanswer daoIndexBuyanswer,
                                          DaoGift daoGift,
                                          DaoProfession daoProfession
    ) {
        return new RoomFascade(daoUser, daoBuyanswer, daoIndexMessage, daoIndexParty,
                daoIndexBuyanswer, daoGift, daoProfession);
    }
}