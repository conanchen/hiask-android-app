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
public class Advert implements Parcelable {
    @PrimaryKey
    public String uuid;

    public String icon;
    public String name;
    public long created;

    public Advert() {
    }

    @Ignore
    public Advert(String uuid, String icon, String name, long created) {
        this.uuid = uuid;
        this.icon = icon;
        this.name = name;
        this.created = created;
    }

    @Ignore

    public static final Creator<Advert> CREATOR = new Creator<Advert>() {
        @Override
        public Advert createFromParcel(Parcel in) {
            return new Advert(in);
        }

        @Override
        public Advert[] newArray(int size) {
            return new Advert[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    protected Advert(Parcel in) {

    }


    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String uuid;

        private String icon;
        private String name;
        private long created;


        public Advert build() {
            String missing = "";

            if (Strings.isNullOrEmpty(uuid)) {
                missing += " uuid";
            }


            if (!missing.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + missing);
            }
            return new Advert(uuid, icon, name, created);
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

        public Builder setCreated(long created) {
            this.created = created;
            return this;
        }
    }
}
