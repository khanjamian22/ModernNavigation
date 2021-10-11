package com.example.mypractice.model.signup_model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUpModel implements Parcelable {

    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("success")
    @Expose
    private SignUP_SuccessModel success;
    @SerializedName("status")
    @Expose
    private int status;

    protected SignUpModel(Parcel in) {
        error = in.readByte() != 0;
        message = in.readString();
        success = in.readParcelable(SignUP_SuccessModel.class.getClassLoader());
        status = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (error ? 1 : 0));
        dest.writeString(message);
        dest.writeParcelable(success, flags);
        dest.writeInt(status);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SignUpModel> CREATOR = new Creator<SignUpModel>() {
        @Override
        public SignUpModel createFromParcel(Parcel in) {
            return new SignUpModel(in);
        }

        @Override
        public SignUpModel[] newArray(int size) {
            return new SignUpModel[size];
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

    public SignUP_SuccessModel getSuccess() {
        return success;
    }

    public void setSuccess(SignUP_SuccessModel success) {
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}



