package org.ditto.lib.dbroom.vo;

import android.arch.persistence.room.Ignore;
import android.os.Parcel;
import android.os.Parcelable;

public class VoAudio implements Parcelable {
    private String title;
    private String detail;

    public VoAudio() {
    }

    @Ignore
    public VoAudio(String title, String detail) {
        this.title = title;
        this.detail = detail;
    }


    public static final Creator<VoAudio> CREATOR = new Creator<VoAudio>() {
        @Override
        public VoAudio createFromParcel(Parcel in) {
            return new VoAudio(in);
        }

        @Override
        public VoAudio[] newArray(int size) {
            return new VoAudio[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.detail);
    }

    @Ignore
    protected VoAudio(Parcel in) {
        this.title = in.readString();
        this.detail = in.readString();
    }

}
