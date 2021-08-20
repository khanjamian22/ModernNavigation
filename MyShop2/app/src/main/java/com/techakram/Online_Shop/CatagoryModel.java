package com.techakram.Online_Shop;

public class CatagoryModel
{


    private String catagoryIconLink;
    private String catagoryName;

    public CatagoryModel(String catagoryIconLink, String catagoryName) {
        this.catagoryIconLink = catagoryIconLink;
        this.catagoryName = catagoryName;
    }

    public String getCatagoryIconLink() {
        return catagoryIconLink;
    }

    public void setCatagoryIconLink(String catagoryIconLink) {
        this.catagoryIconLink = catagoryIconLink;
    }

    public String getCatagoryName() {
        return catagoryName;
    }

    public void setCatagoryName(String catagoryName) {
        this.catagoryName = catagoryName;
    }

}
