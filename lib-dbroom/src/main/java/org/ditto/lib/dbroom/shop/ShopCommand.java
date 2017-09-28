package org.ditto.lib.dbroom.shop;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

/**
 * Created by admin on 2017/6/30.
 */

@Entity
public class ShopCommand implements Parcelable {
    @PrimaryKey
    String uuid;

    long created;
    String type;

    @Embedded(prefix = "updlloc_")
    UpdateLatestLocation updateLatestLocation;

    @Embedded(prefix = "updnkname_")
    UpdateNickname updateNickname;

    ShopCommand() {
    }


    @Ignore
    public ShopCommand(String uuid, long created) {
        this.uuid = uuid;
        this.created = created;
    }

    @Ignore
    public ShopCommand(UpdateLatestLocation updateLatestLocation) {
        this(UUID.randomUUID().toString(), System.currentTimeMillis());
        this.updateLatestLocation = updateLatestLocation;
    }


    @Ignore
    public ShopCommand(UpdateNickname updateNickname) {
        this(UUID.randomUUID().toString(), System.currentTimeMillis());
        this.updateNickname = updateNickname;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UpdateLatestLocation getUpdateLatestLocation() {
        return updateLatestLocation;
    }

    public void setUpdateLatestLocation(UpdateLatestLocation updateLatestLocation) {
        this.updateLatestLocation = updateLatestLocation;
    }

    public UpdateNickname getUpdateNickname() {
        return updateNickname;
    }

    public void setUpdateNickname(UpdateNickname updateNickname) {
        this.updateNickname = updateNickname;
    }

    public static class UpdateLatestLocation {
        private double lat;
        private double lon;

        public UpdateLatestLocation(double lat, double lon) {
            this.lat = lat;
            this.lon = lon;
        }

        public double getLat() {
            return lat;
        }

        public double getLon() {
            return lon;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public void setLon(double lon) {
            this.lon = lon;
        }
    }

    public static class UpdateNickname {
        private String nickname;

        public UpdateNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }


    @Ignore
    protected ShopCommand(Parcel in) {

    }

    public static final Creator<ShopCommand> CREATOR = new Creator<ShopCommand>() {
        @Override
        public ShopCommand createFromParcel(Parcel in) {
            return new ShopCommand(in);
        }

        @Override
        public ShopCommand[] newArray(int size) {
            return new ShopCommand[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }

}
