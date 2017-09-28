package org.ditto.feature.base.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Answer  implements Parcelable{
    String uuid;
    long created;

    String type;


    private String questionId;
    private String contentText;
    private String contentVideoId;
    private String contentAudioId;
    private String contentImageId;
    private String status = STATUS.CREATED.name();
    private double lat;
    private double lon;
    private String creatorId;

    public Answer(String question_id, String contentText, double lat, double lon, String creatorId) {
        this.questionId = question_id;
        this.contentText = contentText;
        this.lat = lat;
        this.lon = lon;
        this.creatorId = creatorId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }


    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public String getContentVideoId() {
        return contentVideoId;
    }

    public void setContentVideoId(String contentVideoId) {
        this.contentVideoId = contentVideoId;
    }

    public String getContentAudioId() {
        return contentAudioId;
    }

    public void setContentAudioId(String contentAudioId) {
        this.contentAudioId = contentAudioId;
    }

    public String getContentImageId() {
        return contentImageId;
    }

    public void setContentImageId(String contentImageId) {
        this.contentImageId = contentImageId;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }


    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public static final Creator<Answer> CREATOR = new Creator<Answer>() {
        @Override
        public Answer createFromParcel(Parcel in) {
            return new Answer(in);
        }

        @Override
        public Answer[] newArray(int size) {
            return new Answer[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    protected Answer(Parcel in) {
    }

}
