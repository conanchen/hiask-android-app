package org.ditto.lib.dbroom.buyread;

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
import org.ditto.lib.dbroom.vo.VoVideo;

@Entity()
public class BuyreadMessage implements Parcelable {
    private final static Gson gson = new GsonBuilder().create();

    @PrimaryKey
    public String uuid;

    public String buyreadUuid;
    public int floor;

    public String contentType;
    public String content;
    public String creatorUuid;
    public String toUserUuid;
    public long created;

    //Content Holder
    @Ignore
    VoAudio voAudio;
    @Ignore
    VoEmail voEmail;
    @Ignore
    VoGift voGift;
    @Ignore
    VoImage voImage;
    @Ignore
    VoNamecard voNamecard;
    @Ignore
    VoPhone voPhone;
    @Ignore
    VoText voText;
    @Ignore
    VoUrl voUrl;
    @Ignore
    VoVideo voVideo;

    public BuyreadMessage() {
    }

    @Ignore
    public BuyreadMessage(String uuid, String buyreadUuid, int floor, String contentType, String content, String creatorUuid, String toUserUuid, long created) {
        this.uuid = uuid;
        this.buyreadUuid = buyreadUuid;
        this.floor = floor;
        this.contentType = contentType;
        this.content = content;
        this.creatorUuid = creatorUuid;
        this.toUserUuid = toUserUuid;
        this.created = created;
    }

    //---------Content holder from field "detail"---------
    public VoAudio audio() {
        if (voAudio == null && VoAudio.class.getSimpleName().equals(contentType) && !Strings.isNullOrEmpty(content)) {
            voAudio = gson.fromJson(content, VoAudio.class);
        }
        return voAudio;
    }

    public VoEmail email() {
        if (voEmail == null && VoEmail.class.getSimpleName().equals(contentType) && !Strings.isNullOrEmpty(content)) {
            voEmail = gson.fromJson(content, VoEmail.class);
        }
        return voEmail;
    }

    public VoText text() {
        if (voText == null && VoText.class.getSimpleName().equals(contentType) && !Strings.isNullOrEmpty(content)) {
            voText = gson.fromJson(content, VoText.class);
        }
        return voText;
    }

    public static final Creator<BuyreadMessage> CREATOR = new Creator<BuyreadMessage>() {
        @Override
        public BuyreadMessage createFromParcel(Parcel in) {
            return new BuyreadMessage(in);
        }

        @Override
        public BuyreadMessage[] newArray(int size) {
            return new BuyreadMessage[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(voText, flags);
    }

    protected BuyreadMessage(Parcel in) {
        this.voText = in.readParcelable(VoText.class.getClassLoader());
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String uuid;

        private String buyreadUuid;
        private int floor;
        private String contentType;
        private String content;
        private String creatorUuid;
        private String toUserUuid;
        private long created;

        //------Content holder------
        private VoAudio voAudio;
        private VoEmail voEmail;
        private VoGeofence voGeofence;
        private VoGift voGift;
        private VoImage voImage;
        private VoNamecard voNamecard;
        private VoPhone voPhone;
        private VoText voText;
        private VoUrl voUrl;
        private VoVideo voVideo;


        Builder() {
        }

        public Builder setUuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder setBuyreadUuid(String buyreadUuid) {
            this.buyreadUuid = buyreadUuid;
            return this;
        }

        public Builder setFloor(int floor) {
            this.floor = floor;
            return this;
        }

        public Builder setContentType(String contentType) {
            this.contentType = contentType;
            return this;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setCreatorUuid(String creatorUuid) {
            this.creatorUuid = creatorUuid;
            return this;
        }

        public Builder setToUserUuid(String toUserUuid) {
            this.toUserUuid = toUserUuid;
            return this;
        }

        public Builder setCreated(long created) {
            this.created = created;
            return this;
        }

        public Builder setVoAudio(VoAudio voAudio) {
            this.voAudio = voAudio;
            return this;
        }

        public Builder setVoEmail(VoEmail voEmail) {
            this.voEmail = voEmail;
            return this;
        }

        public Builder setVoGeofence(VoGeofence voGeofence) {
            this.voGeofence = voGeofence;
            return this;
        }

        public Builder setVoGift(VoGift voGift) {
            this.voGift = voGift;
            return this;
        }

        public Builder setVoImage(VoImage voImage) {
            this.voImage = voImage;
            return this;
        }

        public Builder setVoNamecard(VoNamecard voNamecard) {
            this.voNamecard = voNamecard;
            return this;
        }

        public Builder setVoPhone(VoPhone voPhone) {
            this.voPhone = voPhone;
            return this;
        }

        public Builder setVoText(VoText voText) {
            this.voText = voText;
            return this;
        }

        public Builder setVoUrl(VoUrl voUrl) {
            this.voUrl = voUrl;
            return this;
        }

        public Builder setVoVideo(VoVideo voVideo) {
            this.voVideo = voVideo;
            return this;
        }

        public BuyreadMessage build() {
            String missing = "";

            if (Strings.isNullOrEmpty(uuid)) {
                missing += " uuid";
            }

            if (Strings.isNullOrEmpty(buyreadUuid)) {
                missing += " buyreadUuid";
            }
            if (Longs.compare(created, 0) < 1) {
                created = System.currentTimeMillis();
            }
            int contentCount = 0;
            if (voAudio != null) {
                contentCount++;
                this.contentType = VoAudio.class.getSimpleName();
                this.content = gson.toJson(voAudio);
            }

            if (voEmail != null) {
                contentCount++;
                this.contentType = VoEmail.class.getSimpleName();
                this.content = gson.toJson(voEmail);
            }
            if (voGeofence != null) {
                contentCount++;
                this.contentType = VoGeofence.class.getSimpleName();
                this.content = gson.toJson(voGeofence);
            }
            if (voGift != null) {
                contentCount++;
                this.contentType = VoGift.class.getSimpleName();
                this.content = gson.toJson(voGift);
            }
            if (voImage != null) {
                contentCount++;
                this.contentType = VoImage.class.getSimpleName();
                this.content = gson.toJson(voImage);
            }
            if (voNamecard != null) {
                contentCount++;
                this.contentType = VoNamecard.class.getSimpleName();
                this.content = gson.toJson(voNamecard);
            }
            if (voPhone != null) {
                contentCount++;
                this.contentType = VoPhone.class.getSimpleName();
                this.content = gson.toJson(voPhone);
            }
            if (voText != null) {
                contentCount++;
                this.contentType = VoText.class.getSimpleName();
                this.content = gson.toJson(voText);
            }
            if (voUrl != null) {
                contentCount++;
                this.contentType = VoUrl.class.getSimpleName();
                this.content = gson.toJson(voUrl);
            }
            if (voVideo != null) {
                contentCount++;
                this.contentType = VoVideo.class.getSimpleName();
                this.content = gson.toJson(voVideo);
            }
            if (contentCount != 1) {
                missing += " only 1 allowed, contentCount is " + contentCount;
            }

            if (!missing.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + missing);
            }
            BuyreadMessage buyanswerContent = new
                    BuyreadMessage(  uuid,   buyreadUuid,
                    floor,   contentType,   content,   creatorUuid,   toUserUuid,   created) ;
            return buyanswerContent;
        }

    }

}
