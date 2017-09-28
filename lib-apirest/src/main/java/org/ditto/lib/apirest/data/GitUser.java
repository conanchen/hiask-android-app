package org.ditto.lib.apirest.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class GitUser implements Parcelable {
//    {
//            "login": "conanchen",
//            "id": 1233799,
//            "avatar_url": "https://avatars0.githubusercontent.com/u/1233799?v=3",
//            "gravatar_id": "",
//            "url": "https://api.github.com/users/conanchen",
//            "html_url": "https://github.com/conanchen",
//            "followers_url": "https://api.github.com/users/conanchen/followers",
//            "following_url": "https://api.github.com/users/conanchen/following{/other_user}",
//            "gists_url": "https://api.github.com/users/conanchen/gists{/gist_id}",
//            "starred_url": "https://api.github.com/users/conanchen/starred{/owner}{/repo}",
//            "subscriptions_url": "https://api.github.com/users/conanchen/subscriptions",
//            "organizations_url": "https://api.github.com/users/conanchen/orgs",
//            "repos_url": "https://api.github.com/users/conanchen/repos",
//            "events_url": "https://api.github.com/users/conanchen/events{/privacy}",
//            "received_events_url": "https://api.github.com/users/conanchen/received_events",
//            "type": "UserVo",
//            "site_admin": false,
//            "name": "Conan Chen",
//            "company": null,
//            "blog": "",
//            "location": null,
//            "email": null,
//            "hireable": null,
//            "bio": null,
//            "public_repos": 12,
//            "public_gists": 0,
//            "followers": 0,
//            "following": 0,
//            "created_at": "2011-12-01T15:37:37Z",
//            "updated_at": "2017-05-28T23:43:42Z"
//    }

    String uuid;
    long created;

    String type;

    private String login;
    @SerializedName("avatar_url")
    private String avatarUrl;
    private String name;
    private String url;
    private String company;
    @SerializedName("repos_url")
    private String reposUrl;
    private String blog;
    private boolean visible;



    public GitUser() {
    }

    public GitUser(String login, String avatarUrl, String name, String url, String company, String reposUrl, String blog) {
        this();
        this.login = login;
        this.avatarUrl = avatarUrl;
        this.name = name;
        this.url = url;
        this.company = company;
        this.reposUrl = reposUrl;
        this.blog = blog;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public static final Parcelable.Creator<GitUser> CREATOR = new Parcelable.Creator<GitUser>() {
        @Override
        public GitUser createFromParcel(Parcel in) {
            return new GitUser(in);
        }

        @Override
        public GitUser[] newArray(int size) {
            return new GitUser[size];
        }
    };


    protected GitUser(Parcel in) {

    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}