package com.lynhill.wingallery.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ShowVideoFolders implements Parcelable {
    private String path;
    private String folderName;
    private int numberOfVideo = 0;
    private String firstVideo;

    public ShowVideoFolders(String path, String folderName, int numberOfVideo, String firstVideo) {
        this.path = path;
        this.folderName = folderName;
        this.numberOfVideo = numberOfVideo;
        this.firstVideo = firstVideo;
    }

    public ShowVideoFolders() {
    }

    protected ShowVideoFolders(Parcel in) {
        path = in.readString();
        folderName = in.readString();
        numberOfVideo = in.readInt();
        firstVideo = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(path);
        dest.writeString(folderName);
        dest.writeInt(numberOfVideo);
        dest.writeString(firstVideo);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ShowVideoFolders> CREATOR = new Creator<ShowVideoFolders>() {
        @Override
        public ShowVideoFolders createFromParcel(Parcel in) {
            return new ShowVideoFolders(in);
        }

        @Override
        public ShowVideoFolders[] newArray(int size) {
            return new ShowVideoFolders[size];
        }
    };

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public int getNumberOfVideo() {
        return numberOfVideo;
    }

    public void setNumberOfVideo(int numberOfVideo) {
        this.numberOfVideo = numberOfVideo;
    }

    public String getFirstVideo() {
        return firstVideo;
    }

    public void setFirstVideo(String firstVideo) {
        this.firstVideo = firstVideo;
    }
    /*TODO add pics*/
    public void addvideos(){this.numberOfVideo++;}

}
