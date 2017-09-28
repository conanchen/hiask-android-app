package org.ditto.lib.dbroom.user;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.common.base.Strings;
import com.google.common.primitives.Longs;

import java.util.UUID;

@Entity
public class User implements Parcelable {
    @PrimaryKey
    String uuid;

    String login;
    String avatarUrl;
    String name;
    String url;
    String company;
    String reposUrl;
    String blog;
    boolean visible;
    long created;
    private String latestMessage;
    private String latestLocation;


    public User() {
    }

    @Ignore
    public User(String uuid, String login, String avatarUrl, String name,
                String url, String company, String reposUrl, String blog,
                boolean visible, long created,
                String latestMessage, String latestLocation) {
        this.uuid = uuid;
        this.login = login;
        this.avatarUrl = avatarUrl;
        this.name = name;
        this.url = url;
        this.company = company;
        this.reposUrl = reposUrl;
        this.blog = blog;
        this.visible = visible;
        this.created = created;
        this.latestMessage = latestMessage;
        this.latestLocation = latestLocation;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getReposUrl() {
        return reposUrl;
    }

    public void setReposUrl(String reposUrl) {
        this.reposUrl = reposUrl;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public String getLatestMessage() {
        return latestMessage;
    }

    public void setLatestMessage(String latestMessage) {
        this.latestMessage = latestMessage;
    }

    public String getLatestLocation() {
        return latestLocation;
    }

    public void setLatestLocation(String latestLocation) {
        this.latestLocation = latestLocation;
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };


    @Ignore
    protected User(Parcel in) {

    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }


    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String uuid;

        private String login;
        private String avatarUrl;
        private String name;
        private String url;
        private String company;
        private String reposUrl;
        private String blog;
        private boolean visible;
        private long created;
        private String latestMessage;
        private String latestLocation;


        Builder() {
        }

        public Builder setUuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder setLogin(String login) {
            this.login = login;
            return this;
        }

        public Builder setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setCompany(String company) {
            this.company = company;
            return this;
        }

        public Builder setReposUrl(String reposUrl) {
            this.reposUrl = reposUrl;
            return this;
        }

        public Builder setBlog(String blog) {
            this.blog = blog;
            return this;
        }

        public Builder setVisible(boolean visible) {
            this.visible = visible;
            return this;
        }

        public Builder setCreated(long created) {
            this.created = created;
            return this;
        }

        public Builder setLatestMessage(String latestMessage) {
            this.latestMessage = latestMessage;
            return this;
        }

        public Builder setLatestLocation(String currentLocation) {
            this.latestLocation = currentLocation;
            return this;
        }

        public User build() {
            String missing = "";

            if (Strings.isNullOrEmpty(uuid)) {
                uuid = UUID.randomUUID().toString();
            }

            if (Strings.isNullOrEmpty(name)) {
                missing += " name";
            }
            if (Longs.compare(created, 0) < 1) {
                created = System.currentTimeMillis();
            }

            if (!missing.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + missing);
            }

            return new User(uuid, login, avatarUrl, name, url,
                    company, reposUrl, blog, visible, created,
                    latestMessage, latestLocation);
        }
    }
}