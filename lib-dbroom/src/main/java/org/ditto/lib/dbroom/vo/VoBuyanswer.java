package org.ditto.lib.dbroom.vo;

import android.arch.persistence.room.Ignore;
import android.os.Parcel;
import android.os.Parcelable;

public class VoBuyanswer implements Parcelable {
    private String uuid;
    private String title;

    public VoBuyanswer() {
    }

    @Ignore
    public VoBuyanswer(String uuid, String title) {
        this.uuid = uuid;
        this.title = title;
    }


    public static final Creator<VoBuyanswer> CREATOR = new Creator<VoBuyanswer>() {
        @Override
        public VoBuyanswer createFromParcel(Parcel in) {
            return new VoBuyanswer(in);
        }

        @Override
        public VoBuyanswer[] newArray(int size) {
            return new VoBuyanswer[size];
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
    protected VoBuyanswer(Parcel in) {
        this.uuid = in.readString();
        this.title = in.readString();
    }

}
