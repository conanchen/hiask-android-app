package org.ditto.lib.dbroom.sellread;

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

public class Sellread  implements Parcelable{

    String uuid;
    long created;

    String type;

    String title;

    String creatorId;
    VoGeofence voGeofence;
    VoGift voGift;
    List<BuyanswerContent> contents;



    public Sellread() {
    }

    public Sellread(String title, String creatorId) {
        this.title = title;
        this.creatorId = creatorId;
    }

    public String getUuid() {
        return uuid;
    }

    public Sellread setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public long getCreated() {
        return created;
    }

    public Sellread setCreated(long created) {
        this.created = created;
        return this;
    }

    public String getType() {
        return type;
    }

    public Sellread setType(String type) {
        this.type = type;
        return this;
    }


    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<BuyanswerContent> getContents() {
        return contents;
    }

    public void setContents(List<BuyanswerContent> contents) {
        this.contents = contents;
    }

    public VoGeofence getVoGeofence() {
        return voGeofence;
    }

    public void setVoGeofence(VoGeofence voGeofence) {
        this.voGeofence = voGeofence;
    }

    public VoGift getVoGift() {
        return voGift;
    }

    public void setVoGift(VoGift voGift) {
        this.voGift = voGift;
    }

    public static final Creator<Sellread> CREATOR = new Creator<Sellread>() {
        @Override
        public Sellread createFromParcel(Parcel in) {
            return new Sellread(in);
        }

        @Override
        public Sellread[] newArray(int size) {
            return new Sellread[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    protected Sellread(Parcel in) {

    }


}
