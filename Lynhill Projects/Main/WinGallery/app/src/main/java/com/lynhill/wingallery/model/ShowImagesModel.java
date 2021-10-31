package com.lynhill.wingallery.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author CodeBoy722
 *
 * Custom class for holding data of images on the device external storage
 */
public class ShowImagesModel implements Parcelable {

    private String picturName;
    private String picturePath;
    private String pictureSize;
    private String imageUri;
    private  String imageDate;
    private String picturTime;
    private String pictureWidth;
    private String pictureHight;
    private String imageLongitude;
    private  String imageLatitude;
    private  String imageIso;
    private Boolean selected = false;

    public ShowImagesModel(){

    }

    protected ShowImagesModel(Parcel in) {
        picturName = in.readString();
        picturePath = in.readString();
        pictureSize = in.readString();
        imageUri = in.readString();
        imageDate = in.readString();
        picturTime = in.readString();
        pictureWidth = in.readString();
        pictureHight = in.readString();
        imageLongitude = in.readString();
        imageLatitude = in.readString();
        imageIso = in.readString();
        byte tmpSelected = in.readByte();
        selected = tmpSelected == 0 ? null : tmpSelected == 1;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(picturName);
        dest.writeString(picturePath);
        dest.writeString(pictureSize);
        dest.writeString(imageUri);
        dest.writeString(imageDate);
        dest.writeString(picturTime);
        dest.writeString(pictureWidth);
        dest.writeString(pictureHight);
        dest.writeString(imageLongitude);
        dest.writeString(imageLatitude);
        dest.writeString(imageIso);
        dest.writeByte((byte) (selected == null ? 0 : selected ? 1 : 2));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ShowImagesModel> CREATOR = new Creator<ShowImagesModel>() {
        @Override
        public ShowImagesModel createFromParcel(Parcel in) {
            return new ShowImagesModel(in);
        }

        @Override
        public ShowImagesModel[] newArray(int size) {
            return new ShowImagesModel[size];
        }
    };

    public String getPicturName() {
        return picturName;
    }

    public void setPicturName(String picturName) {
        this.picturName = picturName;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getPictureSize() {
        return pictureSize;
    }

    public void setPictureSize(String pictureSize) {
        this.pictureSize = pictureSize;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getImageDate() {
        return imageDate;
    }

    public void setImageDate(String imageDate) {
        this.imageDate = imageDate;
    }

    public String getPicturTime() {
        return picturTime;
    }

    public void setPicturTime(String picturTime) {
        this.picturTime = picturTime;
    }

    public String getPictureWidth() {
        return pictureWidth;
    }

    public void setPictureWidth(String pictureWidth) {
        this.pictureWidth = pictureWidth;
    }

    public String getPictureHight() {
        return pictureHight;
    }

    public void setPictureHight(String pictureHight) {
        this.pictureHight = pictureHight;
    }

    public String getImageLongitude() {
        return imageLongitude;
    }

    public void setImageLongitude(String imageLongitude) {
        this.imageLongitude = imageLongitude;
    }

    public String getImageLatitude() {
        return imageLatitude;
    }

    public void setImageLatitude(String imageLatitude) {
        this.imageLatitude = imageLatitude;
    }

    public String getImageIso() {
        return imageIso;
    }

    public void setImageIso(String imageIso) {
        this.imageIso = imageIso;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public ShowImagesModel(String picturName, String picturePath, String pictureSize, String imageUri, String imageDate) {
        this.picturName = picturName;
        this.picturePath = picturePath;
        this.pictureSize = pictureSize;
        this.imageUri = imageUri;
        this.imageDate=imageDate;
    }



}
