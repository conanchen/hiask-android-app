package org.ditto.lib.dbroom.vo;

import android.support.v7.app.AlertDialog;

/**
 * Created by admin on 2017/7/20.
 */

public class Vo {
    public VoAdvert voAdvert;
    public VoAudio voAudio;
    public VoBuyanswer voBuyanswer;
    public VoBuyread voBuyread;
    public VoEmail voEmail;
    public VoGeofence voGeofence;
    public VoGift voGift;
    public VoImage voImage;
    public VoLocation voLocation;
    public VoNamecard voNamecard;
    public VoPhone voPhone;
    public VoSellread voSellread;
    public VoShop voShop;
    public VoText voText;
    public VoUgroup voUgroup;
    public VoUrl voUrl;
    public VoUser voUser;
    public VoVideo voVideo;

    public Vo() {
    }

    public Vo(VoAdvert voAdvert, VoAudio voAudio, VoBuyanswer voBuyanswer, VoBuyread voBuyread, VoEmail voEmail, VoGeofence voGeofence, VoGift voGift, VoImage voImage, VoLocation voLocation, VoNamecard voNamecard, VoPhone voPhone, VoSellread voSellread, VoShop voShop, VoText voText, VoUgroup voUgroup, VoUrl voUrl, VoUser voUser, VoVideo voVideo) {
        this.voAdvert = voAdvert;
        this.voAudio = voAudio;
        this.voBuyanswer = voBuyanswer;
        this.voBuyread = voBuyread;
        this.voEmail = voEmail;
        this.voGeofence = voGeofence;
        this.voGift = voGift;
        this.voImage = voImage;
        this.voLocation = voLocation;
        this.voNamecard = voNamecard;
        this.voPhone = voPhone;
        this.voSellread = voSellread;
        this.voShop = voShop;
        this.voText = voText;
        this.voUgroup = voUgroup;
        this.voUrl = voUrl;
        this.voUser = voUser;
        this.voVideo = voVideo;
    }


    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private VoAdvert voAdvert;
        private VoAudio voAudio;
        private VoBuyanswer voBuyanswer;
        private VoBuyread voBuyread;
        private VoEmail voEmail;
        private VoGeofence voGeofence;
        private VoGift voGift;
        private VoImage voImage;
        private VoLocation voLocation;
        private VoNamecard voNamecard;
        private VoPhone voPhone;
        private VoSellread voSellread;
        private VoShop voShop;
        private VoText voText;
        private VoUgroup voUgroup;
        private VoUrl voUrl;
        private VoUser voUser;
        private VoVideo voVideo;

        Builder() {
        }

        public Vo build() {
            return new Vo(voAdvert, voAudio, voBuyanswer, voBuyread,
                    voEmail, voGeofence, voGift, voImage, voLocation, voNamecard, voPhone,
                    voSellread, voShop, voText, voUgroup, voUrl, voUser, voVideo);
        }

        public Builder setVoAdvert(VoAdvert voAdvert) {
            this.voAdvert = voAdvert;
            return this;
        }

        public Builder setVoAudio(VoAudio voAudio) {
            this.voAudio = voAudio;
            return this;
        }

        public Builder setVoBuyanswer(VoBuyanswer voBuyanswer) {
            this.voBuyanswer = voBuyanswer;
            return this;
        }

        public Builder setVoBuyread(VoBuyread voBuyread) {
            this.voBuyread = voBuyread;
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

        public Builder setVoLocation(VoLocation voLocation) {
            this.voLocation = voLocation;
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

        public Builder setVoSellread(VoSellread voSellread) {
            this.voSellread = voSellread;
            return this;
        }

        public Builder setVoShop(VoShop voShop) {
            this.voShop = voShop;
            return this;
        }

        public Builder setVoText(VoText voText) {
            this.voText = voText;
            return this;
        }

        public Builder setVoUgroup(VoUgroup voUgroup) {
            this.voUgroup = voUgroup;
            return this;
        }

        public Builder setVoUrl(VoUrl voUrl) {
            this.voUrl = voUrl;
            return this;
        }

        public Builder setVoUser(VoUser voUser) {
            this.voUser = voUser;
            return this;
        }

        public Builder setVoVideo(VoVideo voVideo) {
            this.voVideo = voVideo;
            return this;
        }
    }
}
