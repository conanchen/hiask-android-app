package org.ditto.lib.dbroom.user;

/*
 * Copyright (c) 2016 Razeware LLC
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.common.base.Strings;

@Entity
public class UserMessage implements Parcelable {
    @PrimaryKey
    public String uuid;

    public String creatorUuid;
    public String toUserUuid;
    public String title;
    public long created;

    public UserMessage() {
    }

    @Ignore
    public UserMessage(String uuid, String creatorUuid, String toUserUuid, String title, long created) {
        this.uuid = uuid;
        this.creatorUuid = creatorUuid;
        this.toUserUuid = toUserUuid;
        this.title = title;
        this.created = created;
    }

    public static final Creator<UserMessage> CREATOR = new Creator<UserMessage>() {
        @Override
        public UserMessage createFromParcel(Parcel in) {
            return new UserMessage(in);
        }

        @Override
        public UserMessage[] newArray(int size) {
            return new UserMessage[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    protected UserMessage(Parcel in) {
    }


    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String uuid;

        private String creatorUuid;
        private String toUserUuid;
        private String title;
        private long created;

        Builder() {
        }

        public UserMessage build() {
            String missing = "";

            if (Strings.isNullOrEmpty(uuid)) {
                missing += " uuid";
            }

            if (Strings.isNullOrEmpty(creatorUuid)) {
                missing += " creatorUuid";
            }
            if (!missing.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + missing);
            }

            return new UserMessage(uuid, creatorUuid, toUserUuid, title, created);
        }

        public Builder setUuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder setCreatorUuid(String creatorUuid) {
            this.creatorUuid = creatorUuid;
            return this;
        }

        public Builder setToUserUuid(String toUserUuid) {
            this.toUserUuid = toUserUuid;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setCreated(long created) {
            this.created = created;
            return this;
        }
    }

}
