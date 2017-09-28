package org.ditto.lib.dbroom.vo;

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

public class VoLocation implements Parcelable{
    String uuid;
    long created;

    String type;

    private double lat;
    private double lon;
    //-------------------------
    private String creatorId;

    public VoLocation() {
    }

    public VoLocation(double lat, double lon, long created, String creatorId) {
        this.lat = lat;
        this.lon = lon;
        this.creatorId = creatorId;
    }

    public String getUuid() {
        return uuid;
    }

    public VoLocation setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public long getCreated() {
        return created;
    }

    public VoLocation setCreated(long created) {
        this.created = created;
        return this;
    }

    public String getType() {
        return type;
    }

    public VoLocation setType(String type) {
        this.type = type;
        return this;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }



    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }




    public static final Creator<VoLocation> CREATOR = new Creator<VoLocation>() {
        @Override
        public VoLocation createFromParcel(Parcel in) {
            return new VoLocation(in);
        }

        @Override
        public VoLocation[] newArray(int size) {
            return new VoLocation[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    protected VoLocation(Parcel in) {

    }


}
