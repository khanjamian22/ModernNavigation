package com.techakram.Online_Shop;
public class CartItemModel {
    public static final int Cart_Item=0;
    public static final int Total_Amount=1;
     ////cart items..............
      private String productTitle,product_price,cutedPrice;
      private int productImage,freeCoupens,appliedCoupens,offerApplied,
              productQuantity;
         private int type;

    public CartItemModel(int type,int productImage,String productTitle,int freeCoupens, String product_price, String cutedPrice,int productQuantity, int offerAppliedint,  int appliedCoupens) {
        this.type = type;
        this.productTitle = productTitle;
        this.product_price = product_price;
        this.cutedPrice = cutedPrice;
        this.productImage = productImage;
        this.freeCoupens = freeCoupens;
        this.appliedCoupens = appliedCoupens;
        this.offerApplied = offerApplied;
        this.productQuantity = productQuantity;
    }
    ////catr items...............

    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public String getProductTitle() {
        return productTitle;
    }
    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }
    public String getProduct_price() {
        return product_price;
    }
    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }
    public String getCutedPrice() {
        return cutedPrice;
    }
    public void setCutedPrice(String cutedPrice) {
        this.cutedPrice = cutedPrice;
    }
    public int getProductImage() {
        return productImage;
    }
    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }
    public int getFreeCoupens() {
        return freeCoupens;
    }
    public void setFreeCoupens(int freeCoupens) {
        this.freeCoupens = freeCoupens;
    }
    public int getAppliedCoupens() {
        return appliedCoupens;
    }
    public void setAppliedCoupens(int appliedCoupens) {
        this.appliedCoupens = appliedCoupens;
    }
    public int getOfferApplied() {
        return offerApplied;
    }
    public void setOfferApplied(int offerApplied) {
        this.offerApplied = offerApplied;
    }
    public int getProductQuantity() {
        return productQuantity;
    }
    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    /////////////////cart total..................
    private  String totalItem;
    private String totalItemPrice,deliveryPrice,savedAmount,totalAmount;

    public CartItemModel(int type,String totalItem, String totalItemPrice, String deliveryPrice, String totalAmount,String savedAmount) {
         this.type=type;
        this.totalAmount=totalAmount;
        this.totalItem = totalItem;
        this.totalItemPrice = totalItemPrice;
        this.deliveryPrice = deliveryPrice;
        this.savedAmount = savedAmount;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(String totalItem) {
        this.totalItem = totalItem;
    }

    public String getTotalItemPrice() {
        return totalItemPrice;
    }

    public void setTotalItemPrice(String totalItemPrice) {
        this.totalItemPrice = totalItemPrice;
    }

    public String getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(String deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public String getSavedAmount() {
        return savedAmount;
    }

    public void setSavedAmount(String savedAmount) {
        this.savedAmount = savedAmount;
    }
    ////////////////cart total
}
