package org.ditto.lib.dbroom.sellread;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.common.base.Strings;
import com.google.common.primitives.Longs;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.ditto.lib.dbroom.vo.VoAudio;
import org.ditto.lib.dbroom.vo.VoEmail;
import org.ditto.lib.dbroom.vo.VoGeofence;
import org.ditto.lib.dbroom.vo.VoGift;
import org.ditto.lib.dbroom.vo.VoImage;
import org.ditto.lib.dbroom.vo.VoNamecard;
import org.ditto.lib.dbroom.vo.VoPhone;
import org.ditto.lib.dbroom.vo.VoText;
import org.ditto.lib.dbroom.vo.VoUrl;
import org.ditto.lib.dbroom.vo.VoUser;
import org.ditto.lib.dbroom.vo.VoVideo;

import java.util.UUID;

/**
 * Created by admin on 2017/6/30.
 * Only Content will be generated locally,
 * all other entities like <code>Buyanswer</code>,<code>Buyread</code>
 * will be generated at cloud server side and downloaded to save locally
 */

@Entity
public class SellreadCommand implements Parcelable {
    private final static Gson gson = new GsonBuilder().create();

    @PrimaryKey
    String uuid;

    String type;
    String content;
    String buyanswerUuid;
    long created;

    //-------------Content holder-----------
    @Ignore
    private Create create;
    @Ignore
    private SetTime2finish setTime2finish;
    @Ignore
    private SetGeofence setGeofence;
    @Ignore
    private SetGift setGift;
    @Ignore
    private UpsertContent upsertContent;
    @Ignore
    private Publish publish;

    @Ignore
    private UpsertMessage upsertMessage;

    public SellreadCommand() {
    }

    @Ignore
    public SellreadCommand(String uuid, String buyanswerUuid, String type, String content, long created) {
        this.uuid = uuid;
        this.buyanswerUuid = buyanswerUuid;
        this.type = type;
        this.content = content;
        this.created = created;
    }

    public Create create() {
        if (create == null
                && Create.class.getSimpleName().equals(type)
                && !Strings.isNullOrEmpty(content)) {
            create = gson.fromJson(content, Create.class);
        }
        return create;
    }


    public SetTime2finish setTime2finish() {
        if (setTime2finish == null
                && SetTime2finish.class.getSimpleName().equals(type)
                && !Strings.isNullOrEmpty(content)) {
            setTime2finish = gson.fromJson(content, SetTime2finish.class);
        }
        return setTime2finish;
    }


    public SetGeofence setGeofence() {
        if (setGeofence == null
                && SetGeofence.class.getSimpleName().equals(type)
                && !Strings.isNullOrEmpty(content)) {
            setGeofence = gson.fromJson(content, SetGeofence.class);
        }

        return setGeofence;
    }


    public SetGift setGift() {
        if (setGift == null
                && SetGift.class.getSimpleName().equals(type)
                && !Strings.isNullOrEmpty(content)) {
            setGift = gson.fromJson(content, SetGift.class);
        }
        return setGift;
    }


    public UpsertContent upsertContent() {
        if (upsertContent == null
                && UpsertContent.class.getSimpleName().equals(type)
                && !Strings.isNullOrEmpty(content)) {
            upsertContent = gson.fromJson(content, UpsertContent.class);
        }
        return upsertContent;
    }


    public Publish publish() {
        if (publish == null
                && Publish.class.getSimpleName().equals(type)
                && !Strings.isNullOrEmpty(content)) {
            publish = gson.fromJson(content, Publish.class);
        }
        return publish;
    }


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getBuyanswerUuid() {
        return buyanswerUuid;
    }

    public void setBuyanswerUuid(String buyanswerUuid) {
        this.buyanswerUuid = buyanswerUuid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    /**
     * Created by admin on 2017/6/25.
     */


    public static class AddUser {
        @Embedded(prefix = "usr_")
        VoUser voUser;

        public AddUser(VoUser voUser) {
            this.voUser = voUser;
        }

        public VoUser getVoUser() {
            return voUser;
        }

        public void setVoUser(VoUser voUser) {
            this.voUser = voUser;
        }
    }

    public static class Create {
        String title;


        public Create(String title) {
            this.title = title;
        }


        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class SetGeofence {
        @Embedded(prefix = "geof_")
        VoGeofence voGeofence;

        public SetGeofence(VoGeofence voGeofence) {
            this.voGeofence = voGeofence;
        }


        public VoGeofence getVoGeofence() {
            return voGeofence;
        }

        public void setVoGeofence(VoGeofence voGeofence) {
            this.voGeofence = voGeofence;
        }
    }

    public static class SetGift {
        @Embedded(prefix = "gif_")
        VoGift voGift;


        public SetGift(VoGift voGift) {
            this.voGift = voGift;
        }

        public VoGift getVoGift() {
            return voGift;
        }

        public void setVoGift(VoGift voGift) {
            this.voGift = voGift;
        }
    }

    public static class SetTime2finish {
        long time2finish;

        public SetTime2finish(long time2finish) {
            this.time2finish = time2finish;
        }


        public long getTime2finish() {
            return time2finish;
        }

        public void setTime2finish(long time2finish) {
            this.time2finish = time2finish;
        }
    }

    public static class UpsertContent {
        VoAudio voAudio;
        VoEmail voEmail;
        VoGeofence voGeofence;
        VoGift voGift;
        VoImage voImage;
        VoNamecard voNamecard;
        VoPhone voPhone;
        VoText voText;
        VoUrl voUrl;
        VoVideo voVideo;

        public UpsertContent(VoAudio voAudio) {
            this.voAudio = voAudio;
        }

        public UpsertContent(VoEmail voEmail) {
            this.voEmail = voEmail;
        }

        public UpsertContent(VoGeofence voGeofence) {
            this.voGeofence = voGeofence;
        }

        public UpsertContent(VoGift voGift) {
            this.voGift = voGift;
        }

        public UpsertContent(VoImage voImage) {
            this.voImage = voImage;
        }

        public UpsertContent(VoNamecard voNamecard) {
            this.voNamecard = voNamecard;
        }

        public UpsertContent(VoPhone voPhone) {
            this.voPhone = voPhone;
        }

        public UpsertContent(VoText voText) {
            this.voText = voText;
        }

        public UpsertContent(VoUrl voUrl) {
            this.voUrl = voUrl;
        }

        public UpsertContent(VoVideo voVideo) {
            this.voVideo = voVideo;
        }

        public VoAudio getVoAudio() {
            return voAudio;
        }

        public void setVoAudio(VoAudio voAudio) {
            this.voAudio = voAudio;
        }

        public VoEmail getVoEmail() {
            return voEmail;
        }

        public void setVoEmail(VoEmail voEmail) {
            this.voEmail = voEmail;
        }

        public VoGeofence getVoGeofence() {
            return voGeofence;
        }

        public void setVoGeofence(VoGeofence voGeofence) {
            this.voGeofence = voGeofence;
        }

        public VoGift getVoGift() {
            return voGift;
        }

        public void setVoGift(VoGift voGift) {
            this.voGift = voGift;
        }

        public VoImage getVoImage() {
            return voImage;
        }

        public void setVoImage(VoImage voImage) {
            this.voImage = voImage;
        }

        public VoNamecard getVoNamecard() {
            return voNamecard;
        }

        public void setVoNamecard(VoNamecard voNamecard) {
            this.voNamecard = voNamecard;
        }

        public VoPhone getVoPhone() {
            return voPhone;
        }

        public void setVoPhone(VoPhone voPhone) {
            this.voPhone = voPhone;
        }

        public VoText getVoText() {
            return voText;
        }

        public void setVoText(VoText voText) {
            this.voText = voText;
        }

        public VoUrl getVoUrl() {
            return voUrl;
        }

        public void setVoUrl(VoUrl voUrl) {
            this.voUrl = voUrl;
        }

        public VoVideo getVoVideo() {
            return voVideo;
        }

        public void setVoVideo(VoVideo voVideo) {
            this.voVideo = voVideo;
        }
    }


    public static class Publish {
        boolean publish = false;

        public Publish() {
        }

        @Ignore
        public Publish(boolean publish) {
            this.publish = publish;
        }

        public boolean isPublish() {
            return publish;
        }

        public void setPublish(boolean publish) {
            this.publish = publish;
        }
    }

    public static class UpsertMessage {
        String messageUuid;
        String toUserUuid;

        VoAudio voAudio;
        VoEmail voEmail;
        VoGift voGift;
        VoImage voImage;
        VoNamecard voNamecard;
        VoPhone voPhone;
        VoText voText;
        VoUrl voUrl;
        VoVideo voVideo;

        public UpsertMessage(String messageUuid, String toUserUuid, VoAudio voAudio) {
            this.messageUuid = messageUuid;
            this.toUserUuid = toUserUuid;
            this.voAudio = voAudio;
        }

        public UpsertMessage(String messageUuid, String toUserUuid, VoEmail voEmail) {
            this.messageUuid = messageUuid;
            this.toUserUuid = toUserUuid;
            this.voEmail = voEmail;
        }

        public UpsertMessage(String messageUuid, String toUserUuid, VoGift voGift) {
            this.messageUuid = messageUuid;
            this.toUserUuid = toUserUuid;
            this.voGift = voGift;
        }

        public UpsertMessage(String messageUuid, String toUserUuid, VoImage voImage) {
            this.messageUuid = messageUuid;
            this.toUserUuid = toUserUuid;
            this.voImage = voImage;
        }

        public UpsertMessage(String messageUuid, String toUserUuid, VoNamecard voNamecard) {
            this.messageUuid = messageUuid;
            this.toUserUuid = toUserUuid;
            this.voNamecard = voNamecard;
        }

        public UpsertMessage(String messageUuid, String toUserUuid, VoPhone voPhone) {
            this.messageUuid = messageUuid;
            this.toUserUuid = toUserUuid;
            this.voPhone = voPhone;
        }

        public UpsertMessage(String messageUuid, String toUserUuid, VoText voText) {
            this.messageUuid = messageUuid;
            this.toUserUuid = toUserUuid;
            this.voText = voText;
        }

        public UpsertMessage(String messageUuid, String toUserUuid, VoUrl voUrl) {
            this.messageUuid = messageUuid;
            this.toUserUuid = toUserUuid;
            this.voUrl = voUrl;
        }

        public UpsertMessage(String messageUuid, String toUserUuid, VoVideo voVideo) {
            this.messageUuid = messageUuid;
            this.toUserUuid = toUserUuid;
            this.voVideo = voVideo;
        }
    }

        @Ignore
    public SellreadCommand(Parcel in) {

    }

    public static final Creator<SellreadCommand> CREATOR = new Creator<SellreadCommand>() {
        @Override
        public SellreadCommand createFromParcel(Parcel in) {
            return new SellreadCommand(in);
        }

        @Override
        public SellreadCommand[] newArray(int size) {
            return new SellreadCommand[size];
        }
    };


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
        private long created;

        private String buyanswerUuid;
        private String type;
        private String content;

        //-------------Commands-----------
        private Create create;
        private SetTime2finish setTime2finish;
        private SetGeofence setGeofence;
        private SetGift setGift;
        private UpsertContent upsertContent;
        private Publish publish;

        //----------Gson-------
        Gson gson;

        Builder() {
        }

        public Builder setUuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder setCreated(long created) {
            this.created = created;
            return this;
        }

        public Builder setBuyanswerUuid(String buyanswerUuid) {
            this.buyanswerUuid = buyanswerUuid;
            return this;
        }

        public Builder setType(String type) {
            this.type = type;
            return this;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setCreate(Create create) {
            this.create = create;
            return this;
        }

        public Builder setSetTime2finish(SetTime2finish setTime2finish) {
            this.setTime2finish = setTime2finish;
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

        public Builder setUpsertContent(UpsertContent upsertContent) {
            this.upsertContent = upsertContent;
            return this;
        }

        public Builder setPublish(Publish publish) {
            this.publish = publish;
            return this;
        }

        public Builder setGson(Gson gson) {
            this.gson = gson;
            return this;
        }

        public SellreadCommand build() {
            String missing = "";

            if (gson == null) {
                missing += " toolkit gson";
            }
            if (Strings.isNullOrEmpty(uuid)) {
                uuid = UUID.randomUUID().toString();
            }
            if (Longs.compare(created, 0) < 1) {
                created = System.currentTimeMillis();
            }
            if (Strings.isNullOrEmpty(buyanswerUuid)) {
                missing += " sellreadUuid";
            }
            int commandCount = 0;
            if (create != null) {
                commandCount++;
                this.type = Create.class.getSimpleName();
                this.content = gson.toJson(create);
            }
            if (setTime2finish != null) {
                commandCount++;
                type = SetTime2finish.class.getSimpleName();
                this.content = gson.toJson(setTime2finish);
            }
            if (setGeofence != null) {
                commandCount++;
                type = SetGeofence.class.getSimpleName();
                this.content = gson.toJson(setGeofence);
            }
            if (setGift != null) {
                commandCount++;
                type = SetGift.class.getSimpleName();
                this.content = gson.toJson(setGift);
            }
            if (upsertContent != null) {
                commandCount++;
                type = UpsertContent.class.getSimpleName();
                this.content = gson.toJson(upsertContent);
            }
            if (publish != null) {
                commandCount++;
                type = Publish.class.getSimpleName();
                this.content = gson.toJson(publish);
            }
            if (commandCount != 1) {
                missing += " only 1 allowed, commandCount is " + commandCount;
            }

            if (!missing.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + missing);
            }
            SellreadCommand command = new SellreadCommand(uuid, buyanswerUuid,
                    type, content, created);
            return command;
        }
    }

}
