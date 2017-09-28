package org.ditto.lib.dbroom.shop;

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


import android.arch.persistence.room.Ignore;
import android.os.Parcel;
import android.os.Parcelable;

public class ShopMessage implements Parcelable {
    public String uuid;


    public String title;
    public String shopUuid;
    public String creatorUuid;
    public long created;

    public ShopMessage() {
    }

    public ShopMessage(String uuid, String title, String shopUuid, String creatorUuid, long created) {
        this.uuid = uuid;
        this.title = title;
        this.shopUuid = shopUuid;
        this.creatorUuid = creatorUuid;
        this.created = created;
    }

    @Ignore



    public static final Creator<ShopMessage> CREATOR = new Creator<ShopMessage>() {
        @Override
        public ShopMessage createFromParcel(Parcel in) {
            return new ShopMessage(in);
        }

        @Override
        public ShopMessage[] newArray(int size) {
            return new ShopMessage[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    protected ShopMessage(Parcel in) {
    }


}
