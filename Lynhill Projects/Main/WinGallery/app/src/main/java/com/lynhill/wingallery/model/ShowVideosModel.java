package com.lynhill.wingallery.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ShowVideosModel  implements Parcelable {
    private String videoName;
    private String videoPath;
    private String videoSize;
    private String videoUri;
    private  String videoDate;
    private  String videoTime;
    private  String videoHight;
    private  String videoWidth;
    private String  videoDuration;
    private Boolean selected = false;

    public ShowVideosModel(String videoName, String videoPath, String videoSize, String videoUri, String videoDate, Boolean selected) {
        this.videoName = videoName;
        this.videoPath = videoPath;
        this.videoSize = videoSize;
        this.videoUri = videoUri;
        this.videoDate = videoDate;
        this.selected = selected;
    }

    public ShowVideosModel() {
    }

    protected ShowVideosModel(Parcel in) {
        videoName = in.readString();
        videoPath = in.readString();
        videoSize = in.readString();
        videoUri = in.readString();
        videoDate = in.readString();
        videoTime = in.readString();
        videoHight = in.readString();
        videoWidth = in.readString();
        videoDuration = in.readString();
        byte tmpSelected = in.readByte();
        selected = tmpSelected == 0 ? null : tmpSelected == 1;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(videoName);
        dest.writeString(videoPath);
        dest.writeString(videoSize);
        dest.writeString(videoUri);
        dest.writeString(videoDate);
        dest.writeString(videoTime);
        dest.writeString(videoHight);
        dest.writeString(videoWidth);
        dest.writeString(videoDuration);
        dest.writeByte((byte) (selected == null ? 0 : selected ? 1 : 2));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ShowVideosModel> CREATOR = new Creator<ShowVideosModel>() {
        @Override
        public ShowVideosModel createFromParcel(Parcel in) {
            return new ShowVideosModel(in);
        }

        @Override
        public ShowVideosModel[] newArray(int size) {
            return new ShowVideosModel[size];
        }
    };

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public String getVideoSize() {
        return videoSize;
    }

    public void setVideoSize(String videoSize) {
        this.videoSize = videoSize;
    }

    public String getVideoUri() {
        return videoUri;
    }

    public void setVideoUri(String videoUri) {
        this.videoUri = videoUri;
    }

    public String getVideoDate() {
        return videoDate;
    }

    public void setVideoDate(String videoDate) {
        this.videoDate = videoDate;
    }

    public String getVideoTime() {
        return videoTime;
    }

    public void setVideoTime(String videoTime) {
        this.videoTime = videoTime;
    }

    public String getVideoHight() {
        return videoHight;
    }

    public void setVideoHight(String videoHight) {
        this.videoHight = videoHight;
    }

    public String getVideoWidth() {
        return videoWidth;
    }

    public void setVideoWidth(String videoWidth) {
        this.videoWidth = videoWidth;
    }

    public String getVideoDuration() {
        return videoDuration;
    }

    public void setVideoDuration(String videoDuration) {
        this.videoDuration = videoDuration;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

}
