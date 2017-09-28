package org.ditto.lib.dbroom.misc;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.common.base.Strings;

/**
 * Created by admin on 2017/6/2.
 */
@Entity
public class Gift implements Parcelable {
    @PrimaryKey
    public String uuid;

    public String icon;
    public String name;
    public int price;
    public long created;

    public Gift() {
    }

    private Gift(String uuid, String icon, String name, int price, long created) {
        this.uuid = uuid;
        this.icon = icon;
        this.name = name;
        this.price = price;
        this.created = created;
    }


    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String uuid;

        private  String icon;
        private  String name;
        private  int price;
        private  long created;

        Builder() {
        }

        public Gift build() {
            String missing = "";

            if (Strings.isNullOrEmpty(uuid)) {
                missing += " uuid";
            }

            if (Strings.isNullOrEmpty(name)) {
                missing += " name";
            }

            if(price<1){
                missing += " [must be price > 0]";
            }

            if (!missing.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + missing);
            }

            return new Gift(  uuid,   icon,   name,   price,   created);
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

        public Builder setPrice(int price) {
            this.price = price;
            return this;
        }

        public Builder setCreated(long created) {
            this.created = created;
            return this;
        }
    }

    public static final Creator<Gift> CREATOR = new Creator<Gift>() {
        @Override
        public Gift createFromParcel(Parcel in) {
            return new Gift(in);
        }

        @Override
        public Gift[] newArray(int size) {
            return new Gift[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    @Ignore
    protected Gift(Parcel in) {

    }


}
