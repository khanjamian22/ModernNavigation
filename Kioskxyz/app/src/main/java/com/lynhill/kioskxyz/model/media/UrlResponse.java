package com.lynhill.kioskxyz.model.media;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UrlResponse implements Parcelable {
    @SerializedName("Success")
    @Expose
    private List<UrlSuccessResponse> success = null;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;

    protected UrlResponse(Parcel in) {
        success = in.createTypedArrayList(UrlSuccessResponse.CREATOR);
        byte tmpStatus = in.readByte();
        status = tmpStatus == 0 ? null : tmpStatus == 1;
        message = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(success);
        dest.writeByte((byte) (status == null ? 0 : status ? 1 : 2));
        dest.writeString(message);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UrlResponse> CREATOR = new Creator<UrlResponse>() {
        @Override
        public UrlResponse createFromParcel(Parcel in) {
            return new UrlResponse(in);
        }

        @Override
        public UrlResponse[] newArray(int size) {
            return new UrlResponse[size];
        }
    };

    public List<UrlSuccessResponse> getSuccess() {
        return success;
    }

    public void setSuccess(List<UrlSuccessResponse> success) {
        this.success = success;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
