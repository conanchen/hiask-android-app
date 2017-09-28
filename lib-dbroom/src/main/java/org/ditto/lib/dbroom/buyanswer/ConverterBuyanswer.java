package org.ditto.lib.dbroom.buyanswer;

import android.arch.persistence.room.TypeConverter;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.ditto.lib.dbroom.buyanswer.BuyanswerCommand;
import org.ditto.lib.dbroom.misc.CommandStatus;
import org.ditto.lib.dbroom.vo.Vo;

public class ConverterBuyanswer {
    private static final Gson gson = new GsonBuilder().create();


    //---------------
    @TypeConverter
    public static String buyanswerCommandContent2GsonString(BuyanswerCommand.Content content) {

        if (content == null)
            return null;

        return gson.toJson(content, BuyanswerCommand.Content.class);
    }

    @TypeConverter
    public static BuyanswerCommand.Content gsonString2BuyanswerCommandContent(String gsonString) {

        if (Strings.isNullOrEmpty(gsonString))
            return null;


        return gson.fromJson(gsonString, BuyanswerCommand.Content.class);
    }


}