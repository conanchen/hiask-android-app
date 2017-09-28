package org.ditto.lib.dbroom.misc;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.common.base.Strings;

import org.ditto.lib.dbroom.vo.Vo;

/**
 * Created by admin on 2017/6/2.
 */
@Entity
public class Notification implements Parcelable {
    @PrimaryKey
    public String uuid;

    public String icon;
    public String name;
    public String detail;
    public long created;
    public Vo vo;

    public Notification() {
    }

    private Notification(String uuid, String icon, String name, String detail, long created, Vo vo) {
        this.uuid = uuid;
        this.icon = icon;
        this.name = name;
        this.detail = detail;
        this.created = created;
        this.vo = vo;
    }

    public static final Creator<Notification> CREATOR = new Creator<Notification>() {
        @Override
        public Notification createFromParcel(Parcel in) {
            return new Notification(in);
        }

        @Override
        public Notification[] newArray(int size) {
            return new Notification[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    protected Notification(Parcel in) {

    }


    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String uuid;

        private  String icon;
        private  String name;
        private  String detail;
        private  long created;
        private  Vo vo;


        public Notification build() {
            String missing = "";

            if (Strings.isNullOrEmpty(uuid)) {
                missing += " uuid";
            }


            if (!missing.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + missing);
            }
            return new Notification(  uuid,   icon,  name,   detail,   created,   vo) ;
        }

        public Builder setUuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder setIcon(String icon) {
            this.icon = icon;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDetail(String detail) {
            this.detail = detail;
            return this;
        }

        public Builder setCreated(long created) {
            this.created = created;
            return this;
        }

        public Builder setVo(Vo vo) {
            this.vo = vo;
            return this;
        }
    }
}
