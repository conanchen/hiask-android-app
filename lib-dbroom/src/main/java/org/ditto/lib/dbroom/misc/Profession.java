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
public class Profession implements Parcelable {
    @PrimaryKey
    public String uuid;
    public int sequence;
    public String icon;
    public String name;
    public String detail;
    public long created;

    public Profession() {
    }

    private Profession(String uuid, int sequence, String icon, String name, String detail, long created) {
        this.uuid = uuid;
        this.sequence = sequence;
        this.icon = icon;
        this.name = name;
        this.detail = detail;
        this.created = created;
    }


    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String uuid;
        private int sequence;
        private String icon;
        private String name;
        private String detail;
        private long created;

        Builder() {
        }

        public Profession build() {
            String missing = "";

            if (Strings.isNullOrEmpty(uuid)) {
                missing += " uuid";
            }

            if (Strings.isNullOrEmpty(name)) {
                missing += " name";
            }

            if (!missing.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + missing);
            }

            return new Profession(uuid, sequence, icon, name, detail, created);
        }

        public Builder setUuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder setSequence(int sequence) {
            this.sequence = sequence;
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
    }

    public static final Creator<Profession> CREATOR = new Creator<Profession>() {
        @Override
        public Profession createFromParcel(Parcel in) {
            return new Profession(in);
        }

        @Override
        public Profession[] newArray(int size) {
            return new Profession[size];
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
    protected Profession(Parcel in) {

    }


}
