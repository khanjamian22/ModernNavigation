package com.example.mypractice.model.signup_model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Sushil Chhetri on 11,September,2021
 */
public class SignUpDetailsModel implements Parcelable {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public SignUpDetailsModel() {
    }

    public SignUpDetailsModel(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    protected SignUpDetailsModel(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        email = in.readString();
        password = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(email);
        dest.writeString(password);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SignUpDetailsModel> CREATOR = new Creator<SignUpDetailsModel>() {
        @Override
        public SignUpDetailsModel createFromParcel(Parcel in) {
            return new SignUpDetailsModel(in);
        }

        @Override
        public SignUpDetailsModel[] newArray(int size) {
            return new SignUpDetailsModel[size];
        }
    };

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
