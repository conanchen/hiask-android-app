package org.ditto.lib.dbroom.favorite;

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

import org.ditto.lib.dbroom.vo.VoBuyanswer;
import org.ditto.lib.dbroom.vo.VoShop;

import dagger.Provides;

@Entity
public class Favorite implements Parcelable {
    @PrimaryKey
    public String uuid;
    public String title;
    public String icon;
    public String type;
    public Content content;
    public long created;

    public Favorite() {
    }

    private Favorite(String uuid, String title, String icon, String type, Content content, long created) {
        this.uuid = uuid;
        this.title = title;
        this.icon = icon;
        this.type = type;
        this.content = content;
        this.created = created;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String uuid;
        private String title;
        private String icon;
        private String type;
        private Content content;
        private long created;

        public Builder() {
        }

        public Favorite build() {
            String missing = "";
            if (Strings.isNullOrEmpty(uuid)) {
                missing += " uuid";
            }
            if (content == null) {
                missing += " content";
            }
            if (content != null) {
                if (content.voBuyanswer != null) {
                    this.type = VoBuyanswer.class.getSimpleName();
                } else if (content.voShop != null) {
                    this.type = VoShop.class.getSimpleName();
                }
            }

            if (Strings.isNullOrEmpty(type)) {
                missing += " type";
            }

            if (!missing.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + missing);
            }


            return new Favorite(uuid, title, icon, type, content, created);
        }

        public Builder setUuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setIcon(String icon) {
            this.icon = icon;
            return this;
        }

        public Builder setContent(Content content) {
            this.content = content;
            return this;
        }

        public Builder setCreated(long created) {
            this.created = created;
            return this;
        }
    }

    public static class Content {
        public VoBuyanswer voBuyanswer;
        public VoShop voShop;

        public Content() {
        }

        private Content(VoBuyanswer voBuyanswer, VoShop voShop) {
            this.voBuyanswer = voBuyanswer;
            this.voShop = voShop;
        }

        public static Builder builder() {
            return new Builder();
        }

        public static final class Builder {
            private VoBuyanswer voBuyanswer;
            private VoShop voShop;

            Builder() {
            }

            public Content build() {
                String missing = "";
                int contentNumber = 0;
                if (voBuyanswer != null) {
                    contentNumber++;
                }
                if (voShop != null) {
                    contentNumber++;
                }
                if (contentNumber != 1) {
                    missing = " only 1 allowed";
                }

                if (!missing.isEmpty()) {
                    throw new IllegalStateException("Missing required properties:" + missing);
                }

                return new Content(voBuyanswer, voShop);
            }

            public Builder setVoBuyanswer(VoBuyanswer voBuyanswer) {
                this.voBuyanswer = voBuyanswer;
                return this;
            }

            public Builder setVoShop(VoShop voShop) {
                this.voShop = voShop;
                return this;
            }
        }

    }

    public static final Creator<Favorite> CREATOR = new Creator<Favorite>() {
        @Override
        public Favorite createFromParcel(Parcel in) {
            return new Favorite(in);
        }

        @Override
        public Favorite[] newArray(int size) {
            return new Favorite[size];
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
    protected Favorite(Parcel in) {

    }


}
