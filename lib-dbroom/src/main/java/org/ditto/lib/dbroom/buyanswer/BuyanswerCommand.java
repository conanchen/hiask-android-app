package org.ditto.lib.dbroom.buyanswer;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.common.base.Strings;
import com.google.common.primitives.Longs;

import org.ditto.lib.dbroom.misc.CommandStatus;
import org.ditto.lib.dbroom.vo.Vo;
import org.ditto.lib.dbroom.vo.VoGeofence;
import org.ditto.lib.dbroom.vo.VoGift;
import org.ditto.lib.dbroom.vo.VoText;
import org.ditto.lib.dbroom.vo.VoUser;


import java.util.UUID;

/**
 * Created by admin on 2017/6/30.
 * Only Content will be generated locally,
 * all other entities like <code>Buyanswer</code>,<code>Buyread</code>
 * will be generated at cloud server side and downloaded to save locally
 */

@Entity
public class BuyanswerCommand implements Parcelable {
    @PrimaryKey
    public String uuid;

    public String buyanswerUuid;
    public String type;
    public Content content;
    public CommandStatus commandStatus;
    public long created;


    public BuyanswerCommand() {
    }

    private BuyanswerCommand(String uuid, String buyanswerUuid, String type, Content content, CommandStatus commandStatus, long created) {
        this.uuid = uuid;
        this.buyanswerUuid = buyanswerUuid;
        this.type = type;
        this.content = content;
        this.commandStatus = commandStatus;
        this.created = created;
    }

    public static class Content {
        public Create create;
        public UpdateTitle updateTitle;
        public SetGeofence setGeofence;
        public SetGift setGift;
        public SetTime2finish setTime2finish;
        public AddUser addUser;
        public UpsertContent upsertContent;
        public Publish publish;
        public UpsertMessage upsertMessage;

        public Content() {
        }

        private Content(Create create, UpdateTitle updateTitle, SetGeofence setGeofence, SetGift setGift, SetTime2finish setTime2finish, AddUser addUser, UpsertContent upsertContent, Publish publish, UpsertMessage upsertMessage) {
            this.create = create;
            this.updateTitle = updateTitle;
            this.setGeofence = setGeofence;
            this.setGift = setGift;
            this.setTime2finish = setTime2finish;
            this.addUser = addUser;
            this.upsertContent = upsertContent;
            this.publish = publish;
            this.upsertMessage = upsertMessage;
        }

        public static Builder builder() {
            return new Builder();
        }

        public static final class Builder {
            private Create create;
            private UpdateTitle updateTitle;
            private SetGeofence setGeofence;
            private SetGift setGift;
            private SetTime2finish setTime2finish;
            private AddUser addUser;
            private UpsertContent upsertContent;
            private Publish publish;
            private UpsertMessage upsertMessage;

            public Content build() {
                return new Content(create, updateTitle,setGeofence, setGift, setTime2finish, addUser, upsertContent, publish, upsertMessage);
            }

            public Builder setCreate(Create create) {
                this.create = create;
                return this;
            }

            public Builder setUpdateTitle(UpdateTitle updateTitle) {
                this.updateTitle = updateTitle;
                return this;
            }

            public Builder setSetGeofence(SetGeofence setGeofence) {
                this.setGeofence = setGeofence;
                return this;
            }

            public Builder setSetGift(SetGift setGift) {
                this.setGift = setGift;
                return this;
            }

            public Builder setSetTime2finish(SetTime2finish setTime2finish) {
                this.setTime2finish = setTime2finish;
                return this;
            }

            public Builder setAddUser(AddUser addUser) {
                this.addUser = addUser;
                return this;
            }

            public Builder setUpsertContent(UpsertContent upsertContent) {
                this.upsertContent = upsertContent;
                return this;
            }

            public Builder setPublish(Publish publish) {
                this.publish = publish;
                return this;
            }

            public Builder setUpsertMessage(UpsertMessage upsertMessage) {
                this.upsertMessage = upsertMessage;
                return this;
            }
        }


    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String uuid;

        private String buyanswerUuid;
        private String type;
        private Content content;
        private CommandStatus commandStatus;
        private long created;


        Builder() {
        }

        public Builder setUuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder setBuyanswerUuid(String buyanswerUuid) {
            this.buyanswerUuid = buyanswerUuid;
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

        public BuyanswerCommand build() {
            String missing = "";

            if (Strings.isNullOrEmpty(uuid)) {
                uuid = UUID.randomUUID().toString();
            }


            if (Strings.isNullOrEmpty(buyanswerUuid)) {
                missing += " buyanswerUuid";
            }

            if (Longs.compare(created, 0) < 1) {
                created = System.currentTimeMillis();
            }
            if (content == null) {
                missing += " content";
            } else {
                if (content.addUser != null) {
                    this.type = AddUser.class.getSimpleName();
                } else if (content.create != null) {
                    this.type = Create.class.getSimpleName();
                } else if (content.updateTitle != null) {
                    this.type = UpdateTitle.class.getSimpleName();
                } else if (content.publish != null) {
                    this.type = Publish.class.getSimpleName();
                } else if (content.setGeofence != null) {
                    this.type = SetGeofence.class.getSimpleName();
                } else if (content.setGift != null) {
                    this.type = SetGift.class.getSimpleName();
                } else if (content.setTime2finish != null) {
                    this.type = SetTime2finish.class.getSimpleName();
                } else if (content.upsertContent != null) {
                    this.type = UpsertContent.class.getSimpleName();
                } else if (content.upsertMessage != null) {
                    this.type = UpsertMessage.class.getSimpleName();
                }
            }
            if (Strings.isNullOrEmpty(type)) {
                missing += " [type from content]";
            }

            if (!missing.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + missing);
            }
            BuyanswerCommand command = new BuyanswerCommand(uuid, buyanswerUuid, type, content, commandStatus, created);
            return command;
        }
    }


    /**
     * Created by admin on 2017/6/25.
     */

    public static class Create {

        public String title;


        private Create(String title) {
            this.title = title;
        }

        public static Builder builder() {
            return new Builder();
        }

        public static final class Builder {
            private String title;

            Builder() {
            }

            public Create build() {
                String missing = "";

                if (Strings.isNullOrEmpty(title)) {
                    missing += " title";
                }

                if (!missing.isEmpty()) {
                    throw new IllegalStateException("Missing required properties:" + missing);
                }

                return new Create(title);
            }

            public Builder setTitle(String title) {
                this.title = title;
                return this;
            }
        }
    }

    public static class UpdateTitle {

        public String title;

        private UpdateTitle(String title) {
            this.title = title;
        }

        public static Builder builder() {
            return new Builder();
        }

        public static final class Builder {
            private String title;

            Builder() {
            }

            public UpdateTitle build() {
                String missing = "";

                if (Strings.isNullOrEmpty(title)) {
                    missing += " title";
                }

                if (!missing.isEmpty()) {
                    throw new IllegalStateException("Missing required properties:" + missing);
                }

                return new UpdateTitle(title);
            }

            public Builder setTitle(String title) {
                this.title = title;
                return this;
            }
        }

    }


    public static class SetGeofence {
        public VoGeofence voGeofence;

        private SetGeofence(VoGeofence voGeofence) {
            this.voGeofence = voGeofence;
        }

        public static Builder builder() {
            return new Builder();
        }

        public static final class Builder {
            private VoGeofence voGeofence;

            Builder() {
            }

            public SetGeofence build() {
                String missing = "";

                if (voGeofence == null) {
                    missing += " voText";
                }

                if (!missing.isEmpty()) {
                    throw new IllegalStateException("Missing required properties:" + missing);
                }

                return new SetGeofence(voGeofence);
            }

            public Builder setVoGeofence(VoGeofence voGeofence) {
                this.voGeofence = voGeofence;
                return this;
            }
        }

    }

    public static class SetGift {
        public VoGift voGift;


        private SetGift(VoGift voGift) {
            this.voGift = voGift;
        }


        public static Builder builder() {
            return new Builder();
        }

        public static final class Builder {
            private VoGift voGift;

            Builder() {
            }

            public SetGift build() {
                String missing = "";

                if (voGift == null) {
                    missing += " voGift";
                }

                if (!missing.isEmpty()) {
                    throw new IllegalStateException("Missing required properties:" + missing);
                }

                return new SetGift(voGift);
            }

            public Builder setVoGift(VoGift voGift) {
                this.voGift = voGift;
                return this;
            }
        }

    }

    public static class SetTime2finish {
        public long time2finish;

        private SetTime2finish(long time2finish) {
            this.time2finish = time2finish;
        }


        public static Builder builder() {
            return new Builder();
        }

        public static final class Builder {
            private long time2finish;

            Builder() {
            }

            public SetTime2finish build() {
                String missing = "";

                if (time2finish < 1) {
                    missing += " time2finish";
                }

                if (!missing.isEmpty()) {
                    throw new IllegalStateException("Missing required properties:" + missing);
                }

                return new SetTime2finish(time2finish);
            }

            public Builder setTime2finish(long time2finish) {
                this.time2finish = time2finish;
                return this;
            }
        }


    }


    public static class AddUser {
        public VoUser voUser;

        private AddUser(VoUser voUser) {
            this.voUser = voUser;
        }


        public static Builder builder() {
            return new Builder();
        }

        public static final class Builder {
            private VoUser voUser;

            Builder() {
            }

            public AddUser build() {
                String missing = "";

                if (voUser == null) {
                    missing += " voUser";
                }

                if (!missing.isEmpty()) {
                    throw new IllegalStateException("Missing required properties:" + missing);
                }

                return new AddUser(voUser);
            }

            public Builder setVoUser(VoUser voUser) {
                this.voUser = voUser;
                return this;
            }
        }

    }

    public static class UpsertContent {
        public String contentUuid;
        public Vo vo;

        public UpsertContent() {
        }

        private UpsertContent(String contentUuid, Vo vo) {
            this.contentUuid = contentUuid;
            this.vo = vo;
        }


        public static Builder builder() {
            return new Builder();
        }

        public static final class Builder {
            private String contentUuid;

            private Vo vo;

            Builder() {
            }

            public UpsertContent build() {
                String missing = "";

                if (vo == null) {
                    missing += " vo";
                }

                if (!missing.isEmpty()) {
                    throw new IllegalStateException("Missing required properties:" + missing);
                }

                return new UpsertContent(contentUuid, vo);
            }

            public Builder setContentUuid(String contentUuid) {
                this.contentUuid = contentUuid;
                return this;
            }

            public Builder setVo(Vo vo) {
                this.vo = vo;
                return this;
            }
        }

    }

    public static class Publish {
        public boolean publish = false;

        public Publish() {
        }

        private Publish(boolean publish) {
            this.publish = publish;
        }

        public static Builder builder() {
            return new Builder();
        }

        public static final class Builder {
            private boolean publish = false;

            Builder() {
            }

            public Publish build() {
                String missing = "";


                if (!missing.isEmpty()) {
                    throw new IllegalStateException("Missing required properties:" + missing);
                }

                return new Publish(publish);
            }

            public Builder setPublish(boolean publish) {
                this.publish = publish;
                return this;
            }
        }


    }

    public static class UpsertMessage {
        public String messageUuid;
        public String toUserUuid;

        public Vo vo;

        public UpsertMessage() {
        }

        private UpsertMessage(String messageUuid, String toUserUuid, Vo vo) {
            this.messageUuid = messageUuid;
            this.toUserUuid = toUserUuid;
            this.vo = vo;
        }

        public static Builder builder() {
            return new Builder();
        }

        public static final class Builder {
            private String messageUuid;
            private String toUserUuid;

            private Vo vo;

            Builder() {
            }

            public UpsertMessage build() {
                String missing = "";

                if (vo == null) {
                    missing += " vo";
                }

                if (!missing.isEmpty()) {
                    throw new IllegalStateException("Missing required properties:" + missing);
                }

                return new UpsertMessage(messageUuid, toUserUuid, vo);
            }

            public Builder setMessageUuid(String messageUuid) {
                this.messageUuid = messageUuid;
                return this;
            }

            public Builder setToUserUuid(String toUserUuid) {
                this.toUserUuid = toUserUuid;
                return this;
            }

            public Builder setVo(Vo vo) {
                this.vo = vo;
                return this;
            }
        }
    }

    @Ignore
    public BuyanswerCommand(Parcel in) {

    }

    public static final Creator<BuyanswerCommand> CREATOR = new Creator<BuyanswerCommand>() {
        @Override
        public BuyanswerCommand createFromParcel(Parcel in) {
            return new BuyanswerCommand(in);
        }

        @Override
        public BuyanswerCommand[] newArray(int size) {
            return new BuyanswerCommand[size];
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
