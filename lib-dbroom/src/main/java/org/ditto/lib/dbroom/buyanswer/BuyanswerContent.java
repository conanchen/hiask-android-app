package org.ditto.lib.dbroom.buyanswer;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.common.base.Strings;
import com.google.common.primitives.Longs;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import org.ditto.lib.dbroom.vo.Vo;
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

@Entity()
public class BuyanswerContent implements Parcelable {

    @PrimaryKey
    public String uuid;

    public String buyanswerUuid;

    public Vo vo;

    public long created;


    public BuyanswerContent() {
    }

    private BuyanswerContent(String uuid, String buyanswerUuid, Vo vo, long created) {
        this.uuid = uuid;
        this.buyanswerUuid = buyanswerUuid;
        this.vo = vo;
        this.created = created;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String uuid;
        private String buyanswerUuid;
        private Vo vo;
        private long created;

        Builder() {
        }

        public BuyanswerContent build() {
            String missing = "";

            if (Strings.isNullOrEmpty(uuid)) {
                missing += " uuid";
            }
            if (Strings.isNullOrEmpty(buyanswerUuid)) {
                missing += " buyanswerUuid";
            }
            if (Longs.compare(created, 0) < 1) {
                created = System.currentTimeMillis();
            }

            if (vo == null) {
                missing += " vo";
            }


            if (!missing.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + missing);
            }
            BuyanswerContent buyanswerContent = new BuyanswerContent(uuid, buyanswerUuid, vo, created);

            return buyanswerContent;
        }

        public Builder setUuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder setBuyanswerUuid(String buyanswerUuid) {
            this.buyanswerUuid = buyanswerUuid;
            return this;
        }

        public Builder setVo(Vo vo) {
            this.vo = vo;
            return this;
        }

        public Builder setCreated(long created) {
            this.created = created;
            return this;
        }
    }


    public static final Creator<BuyanswerContent> CREATOR = new Creator<BuyanswerContent>() {
        @Override
        public BuyanswerContent createFromParcel(Parcel in) {
            return new BuyanswerContent(in);
        }

        @Override
        public BuyanswerContent[] newArray(int size) {
            return new BuyanswerContent[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    protected BuyanswerContent(Parcel in) {
    }


}
