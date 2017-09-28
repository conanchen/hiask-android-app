package org.ditto.lib.dbroom.user;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;


import com.google.common.base.Strings;

import org.ditto.lib.dbroom.misc.CommandStatus;
import org.ditto.lib.dbroom.misc.Profession;

import java.util.List;

/**
 * Created by admin on 2017/6/30.
 */

@Entity
public class UserCommand implements Parcelable {

    @PrimaryKey
    public String uuid;

    public String type;
    public Content content;
    public CommandStatus commandStatus;
    public long created;


    UserCommand() {
    }

    private UserCommand(String uuid, String type, Content content, CommandStatus commandStatus, long created) {
        this.uuid = uuid;
        this.type = type;
        this.content = content;
        this.commandStatus = commandStatus;
        this.created = created;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String uuid;

        private String type;
        private Content content;
        private CommandStatus commandStatus;
        private long created;

        Builder() {
        }

        public UserCommand build() {
            String missing = "";
            if (content == null) {
                missing += " content";
            } else {
                if (content.updateConsultingprice != null) {
                    this.type = UserCommand.UpdateConsultingprice.class.getSimpleName();
                } else if (content.updateConsultingdesc != null) {
                    this.type = UserCommand.UpdateConsultingdesc.class.getSimpleName();
                } else if (content.updateLatestLocation != null) {
                    this.type = UserCommand.UpdateLatestLocation.class.getSimpleName();
                } else if (content.updateProfessions != null ) {
                    this.type = UserCommand.UpdateProfessions.class.getSimpleName();
                } else if (content.updateNickname != null) {
                    this.type = UserCommand.UpdateNickname.class.getSimpleName();
                }
            }

            if (Strings.isNullOrEmpty(type)) {
                missing += " [type from content]";
            }

            if (!missing.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + missing);
            }

            return new UserCommand(uuid, type, content, commandStatus, created);
        }

        public Builder setUuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder setContent(Content content) {
            this.content = content;
            return this;
        }

        public Builder setCommandStatus(CommandStatus commandStatus) {
            this.commandStatus = commandStatus;
            return this;
        }

        public Builder setCreated(long created) {
            this.created = created;
            return this;
        }
    }

    public static class Content {
        public UpdateConsultingprice updateConsultingprice;
        public UpdateConsultingdesc updateConsultingdesc;
        public UpdateLatestLocation updateLatestLocation;
        public UpdateNickname updateNickname;
        public UpdateProfessions updateProfessions;

        public Content() {
        }

        private Content(UpdateConsultingprice updateConsultingprice,
                        UpdateConsultingdesc updateConsultingdesc,
                        UpdateLatestLocation updateLatestLocation,
                        UpdateProfessions updateProfessions,
                        UpdateNickname updateNickname) {
            this.updateConsultingprice = updateConsultingprice;
            this.updateConsultingdesc = updateConsultingdesc;
            this.updateLatestLocation = updateLatestLocation;
            this.updateProfessions = updateProfessions;
            this.updateNickname = updateNickname;
        }


        public static Builder builder() {
            return new Builder();
        }

        public static final class Builder {
            private UpdateConsultingprice updateConsultingprice;
            private UpdateConsultingdesc updateConsultingdesc;
            private UpdateLatestLocation updateLatestLocation;
            private UpdateNickname updateNickname;
            private UpdateProfessions updateProfessions;

            Builder() {
            }

            public Content build() {
                String missing = "";
                int contentCount = 0;
                if (updateConsultingprice != null) {
                    contentCount++;
                }
                if (updateConsultingdesc != null) {
                    contentCount++;
                }
                if (updateLatestLocation != null) {
                    contentCount++;
                }
                if (updateProfessions != null) {
                    contentCount++;
                }
                if (updateNickname != null) {
                    contentCount++;
                }
                if (contentCount != 1) {
                    missing += " only 1 allowed";
                }
                if (!Strings.isNullOrEmpty(missing)) {
                    throw new IllegalStateException("Missing required properties:" + missing);
                }
                return new Content(updateConsultingprice, updateConsultingdesc, updateLatestLocation,updateProfessions, updateNickname);
            }

            public Builder setUpdateConsultingprice(UpdateConsultingprice updateConsultingprice) {
                this.updateConsultingprice = updateConsultingprice;
                return this;
            }

            public Builder setUpdateConsultingdesc(UpdateConsultingdesc updateConsultingdesc) {
                this.updateConsultingdesc = updateConsultingdesc;
                return this;
            }

            public Builder setUpdateLatestLocation(UpdateLatestLocation updateLatestLocation) {
                this.updateLatestLocation = updateLatestLocation;
                return this;
            }

            public Builder setUpdateProfessions(UpdateProfessions updateProfessions) {
                this.updateProfessions = updateProfessions;
                return this;
            }

            public Builder setUpdateNickname(UpdateNickname updateNickname) {
                this.updateNickname = updateNickname;
                return this;
            }
        }
    }

    public static class UpdateLatestLocation {
        public double lat;
        public double lon;

        public UpdateLatestLocation() {
        }

        private UpdateLatestLocation(double lat, double lon) {
            this.lat = lat;
            this.lon = lon;
        }

        public static Builder builder() {
            return new Builder();
        }

        public static final class Builder {
            private double lat;
            private double lon;

            Builder() {
            }

            public UpdateLatestLocation build() {
                String missing = "";

                if (!Strings.isNullOrEmpty(missing)) {
                    throw new IllegalStateException("Missing required properties:" + missing);
                }
                return new UpdateLatestLocation(lat, lon);
            }

            public Builder setLat(double lat) {
                this.lat = lat;
                return this;
            }

            public Builder setLon(double lon) {
                this.lon = lon;
                return this;
            }
        }
    }

    public static class UpdateNickname {
        public String nickname;

        public UpdateNickname() {
        }

        private UpdateNickname(String nickname) {
            this.nickname = nickname;
        }


        public static Builder builder() {
            return new Builder();
        }

        public static final class Builder {
            private String nickname;

            Builder() {
            }

            public UpdateNickname build() {
                String missing = "";

                if (!Strings.isNullOrEmpty(missing)) {
                    throw new IllegalStateException("Missing required properties:" + missing);
                }
                return new UpdateNickname(nickname);
            }

            public Builder setNickname(String nickname) {
                this.nickname = nickname;
                return this;
            }
        }

    }

    public static class UpdateProfessions {
        public List<Profession> professions;

        public UpdateProfessions() {
        }

        private UpdateProfessions(List<Profession> professions) {
            this.professions = professions;
        }

        public static Builder builder() {
            return new Builder();
        }

        public static final class Builder {
            private List<Profession> professions;

            Builder() {
            }

            public UpdateProfessions build() {
                String missing = "";

                if (professions == null || professions.size() == 0) {
                    missing += " professions";
                }

                if (!Strings.isNullOrEmpty(missing)) {
                    throw new IllegalStateException("Missing required properties:" + missing);
                }
                return new UpdateProfessions(professions);
            }

            public Builder setProfessions(List<Profession> professions) {
                this.professions = professions;
                return this;
            }
        }

    }


    public static class UpdateConsultingprice {
        public int price;

        public UpdateConsultingprice() {
        }

        private UpdateConsultingprice(int price) {
            this.price = price;
        }


        public static Builder builder() {
            return new Builder();
        }

        public static final class Builder {
            private int price;

            Builder() {
            }

            public UpdateConsultingprice build() {
                String missing = "";
                if (price < 1) {
                    missing += " radius";
                }
                if (!Strings.isNullOrEmpty(missing)) {
                    throw new IllegalStateException("Missing required properties:" + missing);
                }
                return new UpdateConsultingprice(price);
            }

            public Builder setPrice(int price) {
                this.price = price;
                return this;
            }
        }

    }


    public static class UpdateConsultingdesc {
        public String detail;

        public UpdateConsultingdesc() {
        }

        private UpdateConsultingdesc(String detail) {
            this.detail = detail;
        }


        public static Builder builder() {
            return new Builder();
        }

        public static final class Builder {
            private String detail;

            Builder() {
            }

            public UpdateConsultingdesc build() {
                String missing = "";
                if (Strings.isNullOrEmpty(detail)) {
                    missing += " detail";
                }
                if (!Strings.isNullOrEmpty(missing)) {
                    throw new IllegalStateException("Missing required properties:" + missing);
                }
                return new UpdateConsultingdesc(detail);
            }

            public Builder setDetail(String detail) {
                this.detail = detail;
                return this;
            }
        }

    }


    @Ignore
    protected UserCommand(Parcel in) {

    }

    public static final Creator<UserCommand> CREATOR = new Creator<UserCommand>() {
        @Override
        public UserCommand createFromParcel(Parcel in) {
            return new UserCommand(in);
        }

        @Override
        public UserCommand[] newArray(int size) {
            return new UserCommand[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }

}
