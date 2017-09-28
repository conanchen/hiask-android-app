package org.ditto.lib.dbroom;

import android.arch.persistence.room.TypeConverter;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.ditto.lib.Constants;
import org.ditto.lib.dbroom.buyanswer.BuyanswerCommand;
import org.ditto.lib.dbroom.misc.CommandStatus;
import org.ditto.lib.dbroom.user.UserCommand;
import org.ditto.lib.dbroom.vo.Vo;

public class Converters {
    private static final Gson gson = new GsonBuilder().create();

    //---------------
    @TypeConverter
    public static String CommandStatusToName(CommandStatus commandStatus) {

        if (commandStatus == null)
            return null;


        return commandStatus.name();
    }

    @TypeConverter
    public static CommandStatus NameToCommandStatus(String name) {

        if (Strings.isNullOrEmpty(name))
            return null;


        return CommandStatus.valueOf(name);
    }

    //---------------
    @TypeConverter
    public static Vo gsonString2Vo(String gsonString) {

        if (Strings.isNullOrEmpty(gsonString))
            return null;


        return gson.fromJson(gsonString, Vo.class);
    }

    @TypeConverter
    public static String vo2GsonString(Vo vo) {

        if (vo == null)
            return null;

        return gson.toJson(vo, Vo.class);
    }

}