package com.lynhill.kioskxyz.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UrlSuccessResponse implements Parcelable {

    @SerializedName("URL")
    @Expose
    private String url;

    protected UrlSuccessResponse(Parcel in) {
        url = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UrlSuccessResponse> CREATOR = new Creator<UrlSuccessResponse>() {
        @Override
        public UrlSuccessResponse createFromParcel(Parcel in) {
            return new UrlSuccessResponse(in);
        }

        @Override
        public UrlSuccessResponse[] newArray(int size) {
            return new UrlSuccessResponse[size];
        }
    };

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
