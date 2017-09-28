package org.ditto.lib.dbroom;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.TypeConverters;

import org.ditto.lib.dbroom.buyanswer.Buyanswer;
import org.ditto.lib.dbroom.buyanswer.BuyanswerCommand;
import org.ditto.lib.dbroom.buyanswer.BuyanswerContent;
import org.ditto.lib.dbroom.buyanswer.ConverterBuyanswer;
import org.ditto.lib.dbroom.buyanswer.DaoBuyanswer;
import org.ditto.lib.dbroom.buyanswer.BuyanswerMessage;
import org.ditto.lib.dbroom.buyanswer.DaoIndexBuyanswer;
import org.ditto.lib.dbroom.index.DaoIndexParty;
import org.ditto.lib.dbroom.buyanswer.IndexBuyanswer;
import org.ditto.lib.dbroom.index.IndexMessage;
import org.ditto.lib.dbroom.index.DaoIndexMessage;
import org.ditto.lib.dbroom.index.IndexParty;
import org.ditto.lib.dbroom.misc.DaoGift;
import org.ditto.lib.dbroom.misc.DaoProfession;
import org.ditto.lib.dbroom.misc.Gift;
import org.ditto.lib.dbroom.misc.Profession;
import org.ditto.lib.dbroom.user.ConverterUser;
import org.ditto.lib.dbroom.user.DaoUser;
import org.ditto.lib.dbroom.user.Myprofile;
import org.ditto.lib.dbroom.user.User;
import org.ditto.lib.dbroom.user.UserCommand;


@Database(entities =
        {
                Myprofile.class,
                User.class,
                UserCommand.class,
                Buyanswer.class,
                BuyanswerCommand.class,
                BuyanswerContent.class,
                BuyanswerMessage.class,
                IndexMessage.class,
                IndexParty.class,
                IndexBuyanswer.class,
                Gift.class,
                Profession.class
        }, version = 2)
@TypeConverters({Converters.class, ConverterBuyanswer.class, ConverterUser.class})
public abstract class MyRoomDatabase extends android.arch.persistence.room.RoomDatabase {
    public abstract DaoUser daoUser();

    public abstract DaoBuyanswer daoBuyanswer();

    public abstract DaoIndexMessage daoMessageIndex();

    public abstract DaoIndexParty daoPartyIndex();

    public abstract DaoIndexBuyanswer daoIndexBuyanswer();
    public abstract DaoGift daoGift();
    public abstract DaoProfession daoProfession();
}