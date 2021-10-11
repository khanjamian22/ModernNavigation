package com.example.mypractice.model.otp_model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sushil Chhetri on 11,September,2021
 */
public class SendOTPResponse implements Parcelable {


    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("success")
    @Expose
    private Object success;
    @SerializedName("status")
    @Expose
    private int status;

    protected SendOTPResponse(Parcel in) {
        error = in.readByte() != 0;
        message = in.readString();
        status = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (error ? 1 : 0));
        dest.writeString(message);
        dest.writeInt(status);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SendOTPResponse> CREATOR = new Creator<SendOTPResponse>() {
        @Override
        public SendOTPResponse createFromParcel(Parcel in) {
            return new SendOTPResponse(in);
        }

        @Override
        public SendOTPResponse[] newArray(int size) {
            return new SendOTPResponse[size];
        }
    };

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getSuccess() {
        return success;
    }

    public void setSuccess(Object success) {
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


}
