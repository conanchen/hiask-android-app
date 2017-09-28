package org.ditto.lib.dbroom.vo;

import android.arch.persistence.room.Ignore;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.common.base.Strings;

/**
 * Created by admin on 2017/6/2.
 */

public class VoGeofence implements Parcelable {
    public String centerAddress;
    public double lat;
    public double lon;
    public int radius;


    public VoGeofence() {
    }

    private VoGeofence(String centerAddress, double lat, double lon, int radius) {
        this.centerAddress =centerAddress;
        this.lat = lat;
        this.lon = lon;
        this.radius = radius;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String centerAddress;
        private double lat;
        private double lon;
        private int radius;

        Builder() {
        }

        public VoGeofence build() {
            String missing = "";

            if (Strings.isNullOrEmpty(centerAddress)) {
                missing += " centerAddress";
            }

            if (!missing.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + missing);
            }

            return new VoGeofence(centerAddress,lat,lon,radius);
        }

        public Builder setCenterAddress(String centerAddress) {
            this.centerAddress = centerAddress;
            return this;
        }

        public Builder setLat(double lat) {
            this.lat = lat;
            return this;
        }

        public Builder setLon(double lon) {
            this.lon = lon;
            return this;
        }

        public Builder setRadius(int radius) {
            this.radius = radius;
            return this;
        }
    }


    public static final Creator<VoGeofence> CREATOR = new Creator<VoGeofence>() {
        @Override
        public VoGeofence createFromParcel(Parcel in) {
            return new VoGeofence(in);
        }

        @Override
        public VoGeofence[] newArray(int size) {
            return new VoGeofence[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(lat);
        dest.writeDouble(lon);
        dest.writeInt(radius);
    }

    @Ignore
    protected VoGeofence(Parcel in) {
        this.lat = in.readDouble();
        this.lon = in.readDouble();
        this.radius = in.readInt();
    }

}
