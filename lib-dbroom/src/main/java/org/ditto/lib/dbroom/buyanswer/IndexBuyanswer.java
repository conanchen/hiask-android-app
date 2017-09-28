package org.ditto.lib.dbroom.buyanswer;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.common.base.Strings;

/**
 * Buyanswer相关列表使用的索引存储
 */
@Entity
public class IndexBuyanswer implements Parcelable {

    //uuid is the id of type: Advert/User/Ugroup/Shop/...
    @PrimaryKey
    public String uuid;
    public String type;
    public long weight;
    public long lastUpdated;
    public boolean online;

    public String icon;
    public String title;
    public String detail;
    public int unreadNumber;

    public IndexBuyanswer() {
    }

    private IndexBuyanswer(String uuid, String type, long weight, long lastUpdated, boolean online, String icon, String title, String detail, int unreadNumber) {
        this.uuid = uuid;
        this.type = type;
        this.weight = weight;
        this.lastUpdated = lastUpdated;
        this.online = online;
        this.icon = icon;
        this.title = title;
        this.detail = detail;
        this.unreadNumber = unreadNumber;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String uuid;
        private String type;
        private long weight;
        private long lastUpdated;
        private boolean online;

        private String icon;
        private String title;
        private String detail;
        private int unreadNumber;

        Builder() {
        }


        public IndexBuyanswer build() {
            String missing = "";

            if (Strings.isNullOrEmpty(uuid)) {
                missing += " uuid";
            }

            if (Strings.isNullOrEmpty(type)) {
                missing += " type";
            }

            if (Strings.isNullOrEmpty(title)) {
                missing += " title";
            }

            if (lastUpdated < 1) {
                missing += " lastUpdated";
            }

            if (!missing.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + missing);
            }
            IndexBuyanswer messageIndex = new
                    IndexBuyanswer(uuid, type, weight, lastUpdated,
                    online, icon, title, detail, unreadNumber);
            return messageIndex;
        }

        public Builder setUuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder setType(String type) {
            this.type = type;
            return this;
        }

        public Builder setWeight(long weight) {
            this.weight = weight;
            return this;
        }

        public Builder setLastUpdated(long lastUpdated) {
            this.lastUpdated = lastUpdated;
            return this;
        }

        public Builder setOnline(boolean online) {
            this.online = online;
            return this;
        }

        public Builder setIcon(String icon) {
            this.icon = icon;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setDetail(String detail) {
            this.detail = detail;
            return this;
        }

        public Builder setUnreadNumber(int unreadNumber) {
            this.unreadNumber = unreadNumber;
            return this;
        }
    }


    public static final Creator<IndexBuyanswer> CREATOR = new Creator<IndexBuyanswer>() {
        @Override
        public IndexBuyanswer createFromParcel(Parcel in) {
            return new IndexBuyanswer(in);
        }

        @Override
        public IndexBuyanswer[] newArray(int size) {
            return new IndexBuyanswer[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flag) {
        dest.writeString(detail);
    }

    protected IndexBuyanswer(Parcel in) {
        this.detail = in.readString();
    }


}
