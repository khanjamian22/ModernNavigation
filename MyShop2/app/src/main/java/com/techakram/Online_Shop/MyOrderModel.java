package com.techakram.Online_Shop;

public class MyOrderModel {
    private int productImage;
    private  int rating;
    String productTitle,productStatus;

    public MyOrderModel(int productImage,int rating, String productTitle, String productStatus) {
        this.productImage = productImage;
        this.rating=rating;
        this.productTitle = productTitle;
        this.productStatus = productStatus;
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
