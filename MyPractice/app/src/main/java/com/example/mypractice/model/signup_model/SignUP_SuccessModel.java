package com.example.mypractice.model.signup_model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUP_SuccessModel implements Parcelable {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("role")
    @Expose
    private Object role;
    @SerializedName("isVerified")
    @Expose
    private String isVerified;
    @SerializedName("avatar")
    @Expose
    private Object avatar;

    protected SignUP_SuccessModel(Parcel in) {
        token = in.readString();
        id = in.readInt();
        firstName = in.readString();
        lastName = in.readString();
        email = in.readString();
        isVerified = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(token);
        dest.writeInt(id);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(email);
        dest.writeString(isVerified);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SignUP_SuccessModel> CREATOR = new Creator<SignUP_SuccessModel>() {
        @Override
        public SignUP_SuccessModel createFromParcel(Parcel in) {
            return new SignUP_SuccessModel(in);
        }

        @Override
        public SignUP_SuccessModel[] newArray(int size) {
            return new SignUP_SuccessModel[size];
        }
    };

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getRole() {
        return role;
    }

    public void setRole(Object role) {
        this.role = role;
    }

    public String getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(String isVerified) {
        this.isVerified = isVerified;
    }

    public Object getAvatar() {
        return avatar;
    }

    public void setAvatar(Object avatar) {
        this.avatar = avatar;
    }

}

