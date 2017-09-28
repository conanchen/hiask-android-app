package org.ditto.lib.dbroom.vo;

import android.arch.persistence.room.Ignore;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.common.base.Strings;

public class VoGift implements Parcelable {
    public String uuid;
    public String name;
    public int price;

    public VoGift() {
    }

    private VoGift(String uuid, String name, int price) {
        this.uuid = uuid;
        this.name = name;
        this.price = price;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        public String uuid;
        public String name;
        public int price;

        Builder() {
        }

        public VoGift build() {
            String missing = "";

            if (Strings.isNullOrEmpty(uuid)) {
                missing += " [uuid]";
            }

            if (Strings.isNullOrEmpty(name)) {
                missing += " [name]";
            }

            if (!missing.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + missing);
            }

            return new VoGift(uuid,name,price);
        }

        public Builder setUuid(String uuid) {
            this.uuid = uuid;
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
    }

    public static final Creator<VoGift> CREATOR = new Creator<VoGift>() {
        @Override
        public VoGift createFromParcel(Parcel in) {
            return new VoGift(in);
        }

        @Override
        public VoGift[] newArray(int size) {
            return new VoGift[size];
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
    protected VoGift(Parcel in) {
    }

}

