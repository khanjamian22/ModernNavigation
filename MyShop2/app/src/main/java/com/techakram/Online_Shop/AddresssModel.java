package com.techakram.Online_Shop;

public class AddresssModel {
    String fullName,address,pincode;
    Boolean select;

    public AddresssModel(String fullName, String address, String pincode,Boolean select) {
        this.fullName = fullName;
        this.address = address;
        this.pincode = pincode;
        this.select=select;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public Boolean getSelect() {
        return select;
    }

    public void setSelect(Boolean select) {
        this.select = select;
    }
}
