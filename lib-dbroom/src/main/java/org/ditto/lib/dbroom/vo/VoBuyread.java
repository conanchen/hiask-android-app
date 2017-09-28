package org.ditto.lib.dbroom.vo;

import android.arch.persistence.room.Ignore;
import android.os.Parcel;
import android.os.Parcelable;

public class VoBuyread implements Parcelable {
    private String uuid;
    private String title;

    public VoBuyread() {
    }

    @Ignore
    public VoBuyread(String uuid, String title) {
        this.uuid = uuid;
        this.title = title;
    }


    public static final Creator<VoBuyread> CREATOR = new Creator<VoBuyread>() {
        @Override
        public VoBuyread createFromParcel(Parcel in) {
            return new VoBuyread(in);
        }

        @Override
        public VoBuyread[] newArray(int size) {
            return new VoBuyread[size];
        }
    };

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.uuid);
        dest.writeString(this.title);
    }

    @Ignore
    protected VoBuyread(Parcel in) {
        this.uuid = in.readString();
        this.title = in.readString();
    }

}
