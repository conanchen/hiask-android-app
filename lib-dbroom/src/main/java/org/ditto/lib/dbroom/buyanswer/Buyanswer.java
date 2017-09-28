package org.ditto.lib.dbroom.buyanswer;

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


import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;


import com.google.common.base.Strings;
import com.google.common.primitives.Longs;

import org.ditto.lib.dbroom.vo.VoGeofence;
import org.ditto.lib.dbroom.vo.VoGift;

@Entity
public class Buyanswer implements Parcelable {
    @PrimaryKey
   public String uuid;

    public  String title;
    public String creatorId;
    public long time2finish;
    public long created;

    @Embedded(prefix = "geof_")
    public VoGeofence voGeofence;
    @Embedded(prefix = "gift_")
    public VoGift voGift;


    public Buyanswer() {
    }

    @Ignore
    public Buyanswer(String uuid, String title, String creatorId, long time2finish, long created, VoGeofence voGeofence, VoGift voGift) {
        this.uuid = uuid;
        this.title = title;
        this.creatorId = creatorId;
        this.time2finish = time2finish;
        this.created = created;
        this.voGeofence = voGeofence;
        this.voGift = voGift;
    }

    public static final Creator<Buyanswer> CREATOR = new Creator<Buyanswer>() {
        @Override
        public Buyanswer createFromParcel(Parcel in) {
            return new Buyanswer(in);
        }

        @Override
        public Buyanswer[] newArray(int size) {
            return new Buyanswer[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }

    @Ignore
    protected Buyanswer(Parcel in) {

    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        String uuid;

        String title;
        String creatorId;
        long time2finish;
        long created;

        @Embedded(prefix = "geof_")
        VoGeofence voGeofence;
        @Embedded(prefix = "gift_")
        VoGift voGift;

        Builder() {
        }


        public Builder setUuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setCreatorId(String creatorId) {
            this.creatorId = creatorId;
            return this;
        }

        public Builder setTime2finish(long time2finish) {
            this.time2finish = time2finish;
            return this;
        }

        public Builder setCreated(long created) {
            this.created = created;
            return this;
        }

        public Builder setVoGeofence(VoGeofence voGeofence) {
            this.voGeofence = voGeofence;
            return this;
        }

        public Builder setVoGift(VoGift voGift) {
            this.voGift = voGift;
            return this;
        }

        public Buyanswer build() {
            String missing = "";
            if (Strings.isNullOrEmpty(uuid)) {
                missing += " uuid";
            }

            if (Strings.isNullOrEmpty(title)) {
                missing += " title";
            }
            if (Longs.compare(created, 0) < 1) {
                created = System.currentTimeMillis();
            }

            if (!missing.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + missing);
            }
            Buyanswer buyanswer = new Buyanswer(  uuid,   title,   creatorId,   time2finish,   created, voGeofence, voGift) ;
            return buyanswer;
        }

    }

}
