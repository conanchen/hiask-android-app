package org.ditto.lib.dbroom.user;

import android.arch.persistence.room.TypeConverter;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.ditto.lib.dbroom.buyanswer.BuyanswerCommand;
import org.ditto.lib.dbroom.misc.CommandStatus;
import org.ditto.lib.dbroom.user.UserCommand;
import org.ditto.lib.dbroom.vo.Vo;

public class ConverterUser {
    private static final Gson gson = new GsonBuilder().create();


    //---------------
    @TypeConverter
    public static String userCommandContent2GsonString(UserCommand.Content content) {

        if (content == null)
            return null;

        return gson.toJson(content, UserCommand.Content.class);
    }

    @TypeConverter
    public static UserCommand.Content gsonString2UserCommandContent(String gsonString) {

        if (Strings.isNullOrEmpty(gsonString))
            return null;


        return gson.fromJson(gsonString, UserCommand.Content.class);
    }
    //---------------


    //---------------
    @TypeConverter
    public static String myprofileContent2GsonString(Myprofile.Content content) {

        if (content == null)
            return null;

        return gson.toJson(content, Myprofile.Content.class);
    }

    @TypeConverter
    public static Myprofile.Content gsonString2MyprofileContent(String gsonString) {

        if (Strings.isNullOrEmpty(gsonString))
            return null;


        return gson.fromJson(gsonString, Myprofile.Content.class);
    }
    //---------------

}