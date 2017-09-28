package org.ditto.lib.dbroom.sellread;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
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

import java.util.UUID;

@Entity(primaryKeys = {"sellreadUuid", "uuid"})
public class SellreadContent implements Parcelable {
    private final static Gson gson = new GsonBuilder().create();

    String buyanswerUuid;
    String contentUuid;

    String contentType;
    String content;
    long created;

    //Content Holder
    @Ignore
    VoAudio voAudio;
    @Ignore
    VoEmail voEmail;
    @Ignore
    VoGeofence voGeofence;
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

    public SellreadContent() {
    }

    @Ignore
    public SellreadContent(String buyanswerUuid, String contentUuid, String contentType, String content, long created) {
        this.buyanswerUuid = buyanswerUuid;
        this.contentUuid = contentUuid;
        this.contentType = contentType;
        this.content = content;
        this.created = created;
    }

    public String getBuyanswerUuid() {
        return buyanswerUuid;
    }

    public void setBuyanswerUuid(String buyanswerUuid) {
        this.buyanswerUuid = buyanswerUuid;
    }

    public String getContentUuid() {
        return contentUuid;
    }

    public void setContentUuid(String contentUuid) {
        this.contentUuid = contentUuid;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
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

    public static final Creator<SellreadContent> CREATOR = new Creator<SellreadContent>() {
        @Override
        public SellreadContent createFromParcel(Parcel in) {
            return new SellreadContent(in);
        }

        @Override
        public SellreadContent[] newArray(int size) {
            return new SellreadContent[size];
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

    protected SellreadContent(Parcel in) {
        this.voText = in.readParcelable(VoText.class.getClassLoader());
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String buyanswerUuid;
        private String contentUuid;
        private String contentType;
        private String content;
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
        //----------Gson-------
        Gson gson;


        Builder() {
        }

        public Builder setBuyanswerUuid(String buyanswerUuid) {
            this.buyanswerUuid = buyanswerUuid;
            return this;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setCreated(long created) {
            this.created = created;
            return this;
        }

        public Builder setGson(Gson gson) {
            this.gson = gson;
            return this;
        }

        public Builder setContentUuid(String contentUuid) {
            this.contentUuid = contentUuid;
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

        public SellreadContent build() {
            String missing = "";

            if (gson == null) {
                missing += " toolkit gson";
            }
            if (Strings.isNullOrEmpty(buyanswerUuid)) {
                missing += " sellreadUuid";
            }
            if (Strings.isNullOrEmpty(contentUuid)) {
                contentUuid = UUID.randomUUID().toString();
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
            SellreadContent buyanswerContent = new SellreadContent(buyanswerUuid,
                    contentUuid, contentType, content, created);

            return buyanswerContent;
        }

    }

}
