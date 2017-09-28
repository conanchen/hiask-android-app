package org.ditto.hiask.todo.model;

import com.google.common.base.Strings;

/**
 * Created by admin on 2017/6/10.
 */

public class ArticleTypeData {
    public enum TYPE {
        BUYANSWER, BUYREAD, SELLREAD
    }

    private String uuid;
    private TYPE type;
    private String iconUrl;
    private String title;
    private String detail;

    public ArticleTypeData(String uuid, TYPE type, String iconUrl, String title, String detail) {
        this.uuid = uuid;
        this.type = type;
        this.iconUrl = iconUrl;
        this.title = title;
        this.detail = detail;
    }

    public String getUuid() {
        return uuid;
    }

    public TYPE getType() {
        return type;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String uuid;
        private TYPE type;
        private String iconUrl;
        private String title;
        private String detail;

        Builder() {
        }

        public Builder uuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder type(TYPE value) {
            this.type = value;
            return this;
        }

        public Builder title(String value) {
            this.title = value;
            return this;
        }


        public Builder iconUrl(String value) {
            this.iconUrl = value;
            return this;
        }

        public Builder detail(String value) {
            this.detail = value;
            return this;
        }


        public ArticleTypeData build() {
            String missing = "";
            if (Strings.isNullOrEmpty(uuid)) {
                missing += " uuid";
            }
            if (type == null) {
                missing += " type";
            }
            if (Strings.isNullOrEmpty(title)) {
                missing += " title";
            }
            if (!missing.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + missing);
            }
            ArticleTypeData fragment = new ArticleTypeData(uuid, type, iconUrl, title, detail);
            return fragment;
        }
    }
}
