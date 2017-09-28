package org.ditto.lib.dbroom.ugroup;

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

@Entity
public class Ugroup implements Parcelable {
    @PrimaryKey
    public String uuid;
    public long created;
    public String title;
    public String creatorId;
    public String latestMessage;
    public String location;

    public Ugroup() {
    }

    @Ignore
    public Ugroup(String uuid, long created, String title, String creatorId, String latestMessage, String location) {
        this.uuid = uuid;
        this.created = created;
        this.title = title;
        this.creatorId = creatorId;
        this.latestMessage = latestMessage;
        this.location = location;
    }

    public static final Creator<Ugroup> CREATOR = new Creator<Ugroup>() {
        @Override
        public Ugroup createFromParcel(Parcel in) {
            return new Ugroup(in);
        }

        @Override
        public Ugroup[] newArray(int size) {
            return new Ugroup[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    protected Ugroup(Parcel in) {

    }


}
