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

public class ShopUser implements Parcelable {
    String ugroupUuid;
    String userUuid;

    String inviterUuid;

    String approverUuid;
    long created;

    public ShopUser() {
    }

    @Ignore
    public ShopUser(String ugroupUuid, String userUuid, String inviterUuid, String approverUuid, long created) {
        this.ugroupUuid = ugroupUuid;
        this.userUuid = userUuid;
        this.inviterUuid = inviterUuid;
        this.approverUuid = approverUuid;
        this.created = created;
    }

    public String getUgroupUuid() {
        return ugroupUuid;
    }

    public void setUgroupUuid(String ugroupUuid) {
        this.ugroupUuid = ugroupUuid;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public String getInviterUuid() {
        return inviterUuid;
    }

    public void setInviterUuid(String inviterUuid) {
        this.inviterUuid = inviterUuid;
    }

    public String getApproverUuid() {
        return approverUuid;
    }

    public void setApproverUuid(String approverUuid) {
        this.approverUuid = approverUuid;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public static final Creator<ShopUser> CREATOR = new Creator<ShopUser>() {
        @Override
        public ShopUser createFromParcel(Parcel in) {
            return new ShopUser(in);
        }

        @Override
        public ShopUser[] newArray(int size) {
            return new ShopUser[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    protected ShopUser(Parcel in) {

    }


}
