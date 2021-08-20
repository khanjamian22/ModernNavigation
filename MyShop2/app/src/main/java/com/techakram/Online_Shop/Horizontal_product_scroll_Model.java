package com.techakram.Online_Shop;

public class Horizontal_product_scroll_Model
{
    private String productId;
    private String productImage;
    private String productTitle;
    private String ProductDiscription;
    private  String getProductPrice;

    public Horizontal_product_scroll_Model(String ProductId,String productImage,
                                           String productTitle, String productDiscription, String getProductPrice) {
        this.productImage = productImage;
        this.productTitle = productTitle;
        ProductDiscription = productDiscription;
        this.getProductPrice = getProductPrice;
        this.productId=ProductId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductDiscription() {
        return ProductDiscription;
    }

    public void setProductDiscription(String productDiscription) {
        ProductDiscription = productDiscription;
    }

    public String getGetProductPrice() {
        return getProductPrice;
    }

    public void setGetProductPrice(String getProductPrice) {
        this.getProductPrice = getProductPrice;
    }
}
