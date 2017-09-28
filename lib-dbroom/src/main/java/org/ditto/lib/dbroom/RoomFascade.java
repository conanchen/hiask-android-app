package org.ditto.lib.dbroom;

import org.ditto.lib.dbroom.buyanswer.DaoBuyanswer;
import org.ditto.lib.dbroom.buyanswer.DaoIndexBuyanswer;
import org.ditto.lib.dbroom.index.DaoIndexMessage;
import org.ditto.lib.dbroom.index.DaoIndexParty;
import org.ditto.lib.dbroom.misc.DaoGift;
import org.ditto.lib.dbroom.misc.DaoProfession;
import org.ditto.lib.dbroom.user.DaoUser;

import javax.inject.Inject;

/**
 * Created by amirziarati on 10/4/16.
 */
public class RoomFascade {

    public final DaoUser daoUser;
    public final DaoIndexMessage daoIndexMessage;
    public final DaoIndexParty daoIndexParty;
    public final DaoIndexBuyanswer daoIndexBuyanswer;
    public final DaoBuyanswer daoBuyanswer;
    public final DaoGift daoGift;
    public final DaoProfession daoProfession;
    @Inject
    String strAmir;


    @Inject
    public RoomFascade(DaoUser daoUser,
                       DaoBuyanswer daoBuyanswer,
                       DaoIndexMessage daoIndexMessage,
                       DaoIndexParty daoIndexParty,
                       DaoIndexBuyanswer daoIndexBuyanswer,
                       DaoGift daoGift,
                       DaoProfession daoProfession
                       ) {
        this.daoUser = daoUser;
        this.daoBuyanswer = daoBuyanswer;
        this.daoIndexMessage = daoIndexMessage;
        this.daoIndexParty = daoIndexParty;
        this.daoIndexBuyanswer = daoIndexBuyanswer;
        this.daoGift = daoGift;
        this.daoProfession = daoProfession;
        System.out.println(strAmir);

    }

    public String getConvertedStrAmir() {
        return "Convert " + strAmir;
    }


}