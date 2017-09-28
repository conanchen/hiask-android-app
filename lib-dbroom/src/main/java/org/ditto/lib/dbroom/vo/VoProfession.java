package org.ditto.lib.dbroom.vo;

import com.google.common.base.Strings;

/**
 * Created by admin on 2017/7/27.
 */

public class VoProfession {
    public String name;

    public VoProfession() {
    }

    private VoProfession(String name) {
        this.name = name;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String name;

        Builder() {
        }

        public VoProfession build() {
            String missing = "";

            if (Strings.isNullOrEmpty(name)) {
                missing += " [name]";
            }

            if (!missing.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + missing);
            }

            return new VoProfession(name);
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }
    }
}
