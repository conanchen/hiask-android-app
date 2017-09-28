package org.ditto.lib.dbroom.favorite;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import org.ditto.lib.dbroom.misc.CommandStatus;
import org.ditto.lib.dbroom.vo.VoBuyanswer;
import org.ditto.lib.dbroom.vo.VoShop;

/**
 * Created by admin on 2017/6/30.
 */

@Entity
public class FavoriteCommand implements Parcelable {
    @PrimaryKey
    public String uuid;

    public String type;
    public Content content;
    public CommandStatus commandStatus;
    public long created;

    public FavoriteCommand() {
    }

    private FavoriteCommand(String uuid, String type, Content content, CommandStatus commandStatus, long created) {
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

        public FavoriteCommand build() {
            return new FavoriteCommand(uuid, type, content, commandStatus, created);
        }

        public Builder setUuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder setType(String type) {
            this.type = type;
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
        public AddFavoriteBuyanswer addFavoriteBuyanswer;
        public DelFavoriteBuyanswer delFavoriteBuyanswer;
        public AddFavoriteShop addFavoriteShop;
        public DelFavoriteShop delFavoriteShop;

        public Content() {
        }

        private Content(AddFavoriteBuyanswer addFavoriteBuyanswer, DelFavoriteBuyanswer delFavoriteBuyanswer,
                        AddFavoriteShop addFavoriteShop, DelFavoriteShop delFavoriteShop) {
            this.addFavoriteBuyanswer = addFavoriteBuyanswer;
            this.delFavoriteBuyanswer = delFavoriteBuyanswer;
            this.addFavoriteShop = addFavoriteShop;
            this.delFavoriteShop = delFavoriteShop;
        }

        public static Builder builder() {
            return new Builder();
        }

        public static final class Builder {
            private AddFavoriteBuyanswer addFavoriteBuyanswer;
            private DelFavoriteBuyanswer delFavoriteBuyanswer;
            private AddFavoriteShop addFavoriteShop;
            private DelFavoriteShop delFavoriteShop;

            Builder() {
            }

            public Content build() {
                return new Content(addFavoriteBuyanswer, delFavoriteBuyanswer, addFavoriteShop, delFavoriteShop);
            }

            public Builder setAddFavoriteBuyanswer(AddFavoriteBuyanswer addFavoriteBuyanswer) {
                this.addFavoriteBuyanswer = addFavoriteBuyanswer;
                return this;
            }

            public Builder setDelFavoriteBuyanswer(DelFavoriteBuyanswer delFavoriteBuyanswer) {
                this.delFavoriteBuyanswer = delFavoriteBuyanswer;
                return this;
            }

            public Builder setAddFavoriteShop(AddFavoriteShop addFavoriteShop) {
                this.addFavoriteShop = addFavoriteShop;
                return this;
            }

            public Builder setDelFavoriteShop(DelFavoriteShop delFavoriteShop) {
                this.delFavoriteShop = delFavoriteShop;
                return this;
            }
        }
    }


    public static class AddFavoriteBuyanswer {
        public VoBuyanswer voBuyanswer;

        public AddFavoriteBuyanswer() {
        }

        private AddFavoriteBuyanswer(VoBuyanswer voBuyanswer) {
            this.voBuyanswer = voBuyanswer;
        }


        public static Builder builder() {
            return new Builder();
        }

        public static final class Builder {
            private VoBuyanswer voBuyanswer;

            Builder() {
            }

            public AddFavoriteBuyanswer build() {
                return new AddFavoriteBuyanswer(voBuyanswer);
            }

            public Builder setVoBuyanswer(VoBuyanswer voBuyanswer) {
                this.voBuyanswer = voBuyanswer;
                return this;
            }
        }

    }


    public static class DelFavoriteBuyanswer {
        public VoBuyanswer voBuyanswer;

        public DelFavoriteBuyanswer() {
        }

        private DelFavoriteBuyanswer(VoBuyanswer voBuyanswer) {
            this.voBuyanswer = voBuyanswer;
        }


        public static Builder builder() {
            return new Builder();
        }

        public static final class Builder {
            private VoBuyanswer voBuyanswer;

            Builder() {
            }

            public DelFavoriteBuyanswer build() {
                return new DelFavoriteBuyanswer(voBuyanswer);
            }

            public Builder setVoBuyanswer(VoBuyanswer voBuyanswer) {
                this.voBuyanswer = voBuyanswer;
                return this;
            }
        }

    }


    public static class AddFavoriteShop {
        public VoShop voShop;

        public AddFavoriteShop() {
        }

        private AddFavoriteShop(VoShop voShop) {
            this.voShop = voShop;
        }


        public static Builder builder() {
            return new Builder();
        }

        public static final class Builder {
            private VoShop voShop;

            Builder() {
            }

            public AddFavoriteShop build() {
                return new AddFavoriteShop(voShop);
            }

            public Builder setVoShop(VoShop voShop) {
                this.voShop = voShop;
                return this;
            }
        }

    }


    public static class DelFavoriteShop {
        public VoShop voShop;

        public DelFavoriteShop() {
        }

        private DelFavoriteShop(VoShop voShop) {
            this.voShop = voShop;
        }


        public static Builder builder() {
            return new Builder();
        }

        public static final class Builder {
            private VoShop voShop;

            Builder() {
            }

            public DelFavoriteShop build() {
                return new DelFavoriteShop(voShop);
            }

            public Builder setVoShop(VoShop voShop) {
                this.voShop = voShop;
                return this;
            }
        }

    }


    @Ignore
    protected FavoriteCommand(Parcel in) {

    }

    public static final Creator<FavoriteCommand> CREATOR = new Creator<FavoriteCommand>() {
        @Override
        public FavoriteCommand createFromParcel(Parcel in) {
            return new FavoriteCommand(in);
        }

        @Override
        public FavoriteCommand[] newArray(int size) {
            return new FavoriteCommand[size];
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
