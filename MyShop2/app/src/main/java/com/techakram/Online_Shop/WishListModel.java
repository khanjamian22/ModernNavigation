package com.techakram.Online_Shop;

public class WishListModel {
    String product_image;long free_coupen,total_rating;
    String product_title,rating,product_price,cutted_price;
    boolean COD;

    public WishListModel(String product_image, long free_coupen, long total_rating, String product_title, String rating, String product_price, String cutted_price, boolean COD) {
        this.product_image = product_image;
        this.free_coupen = free_coupen;
        this.total_rating = total_rating;
        this.product_title = product_title;
        this.rating = rating;
        this.product_price = product_price;
        this.cutted_price = cutted_price;
        this.COD = COD;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public long getFree_coupen() {
        return free_coupen;
    }

    public void setFree_coupen(long free_coupen) {
        this.free_coupen = free_coupen;
    }

    public long getTotal_rating() {
        return total_rating;
    }

    public void setTotal_rating(long total_rating) {
        this.total_rating = total_rating;
    }

    public String getProduct_title() {
        return product_title;
    }

    public void setProduct_title(String product_title) {
        this.product_title = product_title;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getCutted_price() {
        return cutted_price;
    }

    public void setCutted_price(String cutted_price) {
        this.cutted_price = cutted_price;
    }

    public boolean isCOD() {
        return COD;
    }

    public void setCOD(boolean COD) {
        this.COD = COD;
    }
}