package org.ditto.lib.dbroom.vo;

import android.arch.persistence.room.Ignore;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.common.base.Strings;

public class VoEmail implements Parcelable {
    public String title;
    public String email;

    public VoEmail() {
    }

    private VoEmail(String title, String email) {
        this.title = title;
        this.email = email;
    }


    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String title;
        private String email;

        Builder() {
        }

        public VoEmail build() {
            String missing = "";
            if (Strings.isNullOrEmpty(email)) {
                missing += " email";
            }
            if (!missing.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + missing);
            }
            return new VoEmail(title, email);
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }
    }


    public static final Creator<VoEmail> CREATOR = new Creator<VoEmail>() {
        @Override
        public VoEmail createFromParcel(Parcel in) {
            return new VoEmail(in);
        }

        @Override
        public VoEmail[] newArray(int size) {
            return new VoEmail[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.email);
    }

    @Ignore
    protected VoEmail(Parcel in) {
        this.title = in.readString();
        this.email = in.readString();
    }

}

