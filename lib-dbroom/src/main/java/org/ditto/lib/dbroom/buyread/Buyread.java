package org.ditto.lib.dbroom.buyread;

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


import android.os.Parcel;
import android.os.Parcelable;

import org.ditto.lib.dbroom.buyanswer.BuyanswerContent;
import org.ditto.lib.dbroom.vo.VoGeofence;
import org.ditto.lib.dbroom.vo.VoGift;

import java.util.List;

public class Buyread implements Parcelable {
    String uuid;
    long created;

    String type;


    String title;

    String creatorId;
    List<BuyanswerContent> contents;
    VoGeofence voGeofence;
    VoGift voGift;


    public Buyread() {
    }

    public Buyread(String title, String creatorId) {
        this.title = title;
        this.creatorId = creatorId;
    }

    public String getUuid() {
        return uuid;
    }

    public Buyread setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public long getCreated() {
        return created;
    }

    public Buyread setCreated(long created) {
        this.created = created;
        return this;
    }

    public String getType() {
        return type;
    }

    public Buyread setType(String type) {
        this.type = type;
        return this;
    }

    public Buyread setTitle(String title) {
        this.title = title;
        return this;
    }

    public Buyread setCreatorId(String creatorId) {
        this.creatorId = creatorId;
        return this;
    }

    public Buyread setContents(List<BuyanswerContent> contents) {
        this.contents = contents;
        return this;
    }

    public Buyread setVoGeofence(VoGeofence voGeofence) {
        this.voGeofence = voGeofence;
        return this;
    }

    public Buyread setVoGift(VoGift voGift) {
        this.voGift = voGift;
        return this;
    }


    public String getCreatorId() {
        return creatorId;
    }

    public String getTitle() {
        return title;
    }

    public List<BuyanswerContent> getContents() {
        return contents;
    }

    public VoGeofence getVoGeofence() {
        return voGeofence;
    }

    public VoGift getVoGift() {
        return voGift;
    }


    public static final Creator<Buyread> CREATOR = new Creator<Buyread>() {
        @Override
        public Buyread createFromParcel(Parcel in) {
            return new Buyread(in);
        }

        @Override
        public Buyread[] newArray(int size) {
            return new Buyread[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    protected Buyread(Parcel in) {

    }


}
