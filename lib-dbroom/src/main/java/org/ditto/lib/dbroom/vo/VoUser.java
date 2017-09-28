package org.ditto.lib.dbroom.vo;

import android.arch.persistence.room.Ignore;
import android.os.Parcel;
import android.os.Parcelable;

public class VoUser implements Parcelable {
    private String uuid;
    private String title;

    public VoUser() {
    }

    @Ignore
    public VoUser(String uuid, String title) {
        this.uuid = uuid;
        this.title = title;
    }


    public static final Creator<VoUser> CREATOR = new Creator<VoUser>() {
        @Override
        public VoUser createFromParcel(Parcel in) {
            return new VoUser(in);
        }

        @Override
        public VoUser[] newArray(int size) {
            return new VoUser[size];
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
    protected VoUser(Parcel in) {
        this.uuid = in.readString();
        this.title = in.readString();
    }

}
