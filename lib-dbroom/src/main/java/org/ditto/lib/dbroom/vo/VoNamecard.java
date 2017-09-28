package org.ditto.lib.dbroom.vo;

import android.arch.persistence.room.Ignore;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.common.base.Strings;

public class VoNamecard {
    public String title;
    public String detail;

    public VoNamecard() {
    }

    private VoNamecard(String title, String detail) {
        this.title = title;
        this.detail = detail;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String title;
        private String detail;

        Builder() {
        }

        public VoNamecard build() {
            String missing = "";

            if (Strings.isNullOrEmpty(title)) {
                missing += " [name]";
            }

            if (!missing.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + missing);
            }

            return new VoNamecard(title, detail);
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setDetail(String detail) {
            this.detail = detail;
            return this;
        }
    }
}

