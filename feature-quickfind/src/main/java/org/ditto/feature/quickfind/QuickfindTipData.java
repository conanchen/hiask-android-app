package org.ditto.feature.quickfind;

import android.os.Parcel;
import android.os.Parcelable;

public class QuickfindTipData implements Parcelable {
    private final long id;
    private String title;
    private String detail;
    private String require;
    private TYPE type;

    public enum TYPE {
        TEXT, AUDIO, IMAGE, VIDEO, POSITION, POSITIONSHARE, COLLECTION, MOBILENUMBER
    }

    public QuickfindTipData(long id, String title, String detail, String require, TYPE type) {
        this.id = id;
        this.title = title;
        this.detail = detail;
        this.require = require;
        this.type = type;
    }


    public static final Creator<QuickfindTipData> CREATOR = new Creator<QuickfindTipData>() {
        @Override
        public QuickfindTipData createFromParcel(Parcel in) {
            return new QuickfindTipData(in);
        }

        @Override
        public QuickfindTipData[] newArray(int size) {
            return new QuickfindTipData[size];
        }
    };

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }

    public String getRequire() {
        return require;
    }

    public TYPE getType() {
        return type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.title);
        dest.writeString(this.detail);
        dest.writeString(this.require);
        dest.writeString(type.name());
    }

    protected QuickfindTipData(Parcel in) {
        this.id = in.readLong();

        this.type = TYPE.valueOf(in.readString());
    }

}
