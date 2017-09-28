package org.ditto.lib.dbroom.vo;

import android.arch.persistence.room.Ignore;
import android.os.Parcel;
import android.os.Parcelable;

public class VoUgroup implements Parcelable {
    private String uuid;
    private String title;

    public VoUgroup() {
    }

    @Ignore
    public VoUgroup(String uuid, String title) {
        this.uuid = uuid;
        this.title = title;
    }


    public static final Creator<VoUgroup> CREATOR = new Creator<VoUgroup>() {
        @Override
        public VoUgroup createFromParcel(Parcel in) {
            return new VoUgroup(in);
        }

        @Override
        public VoUgroup[] newArray(int size) {
            return new VoUgroup[size];
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
    protected VoUgroup(Parcel in) {
        this.uuid = in.readString();
        this.title = in.readString();
    }

}
