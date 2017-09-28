package org.ditto.lib.dbroom.vo;

import android.arch.persistence.room.Ignore;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.common.base.Strings;

public class VoText implements Parcelable {
    public String detail;

    public VoText() {

    }

    private VoText(String detail) {
        this();
        this.detail = detail;
    }


    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String detail;

        Builder() {
        }

        public VoText build() {
            String missing = "";
            if (Strings.isNullOrEmpty(detail)) {
                missing += " detail";
            }
            if (!missing.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + missing);
            }
            return new VoText(detail);
        }

        public Builder setDetail(String detail) {
            this.detail = detail;
            return this;
        }
    }

    public static final Creator<VoText> CREATOR = new Creator<VoText>() {
        @Override
        public VoText createFromParcel(Parcel in) {
            return new VoText(in);
        }

        @Override
        public VoText[] newArray(int size) {
            return new VoText[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.detail);
    }

    @Ignore
    protected VoText(Parcel in) {
        this.detail = in.readString();
    }

}
