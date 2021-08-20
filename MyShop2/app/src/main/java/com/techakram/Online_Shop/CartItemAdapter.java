package com.techakram.Online_Shop;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
public class CartItemAdapter extends RecyclerView.Adapter {
    private List<CartItemModel> cartItemModelList;

    // public static final int Cart_Item=0;
    //public static final int Total_Amount=1;
    //inse direct access ker sakte hain without class attach kiye..
    public CartItemAdapter(List<CartItemModel> cartItemModelList) {
        this.cartItemModelList = cartItemModelList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType)
        {
            case CartItemModel.Cart_Item:
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout,parent,false);
                return new CartItemViewHolder(view);
            case CartItemModel.Total_Amount:
                View view1= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_total_amount_layout,parent,false);
                return new CartTotalAmountViewHolder(view1);
            default:
                return null;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
       switch (cartItemModelList.get(position).getType())
       {
           case CartItemModel.Cart_Item:
               int resource=cartItemModelList.get(position).getProductImage();
               String title=cartItemModelList.get(position).getProductTitle();
               int freecopens=cartItemModelList.get(position).getFreeCoupens();
               String prduct_price=cartItemModelList.get(position).getProduct_price();
               String cutedprice=cartItemModelList.get(position).getCutedPrice();
               int offer_applied=cartItemModelList.get(position).getOfferApplied();
               ((CartItemViewHolder)holder).setCartItemDetails(resource,title,freecopens,prduct_price,cutedprice,offer_applied);
               break;
               case CartItemModel.Total_Amount:
                   String total_item=cartItemModelList.get(position).getTotalItem();
                   String total_price=cartItemModelList.get(position).getTotalItemPrice();
                   String delivery_price=cartItemModelList.get(position).getDeliveryPrice();
                   String total_amount=cartItemModelList.get(position).getTotalAmount();
                   String saved_amount=cartItemModelList.get(position).getSavedAmount();
                   ((CartTotalAmountViewHolder)holder).setTotalAmount(total_item,total_price,delivery_price,total_amount,saved_amount);
                   break;
           default:
               return;
       }
    }

    @Override
    public int getItemViewType(int position) {
        switch (cartItemModelList.get(position).getType( )) {
            case 0:
                return CartItemModel.Cart_Item;
            case 1:
                return CartItemModel.Total_Amount;
            default:
                return -1;
        }
    }
        @Override
        public int getItemCount () {
            return cartItemModelList.size();
        }
        public class CartItemViewHolder extends RecyclerView.ViewHolder{

            private ImageView productImage,coupen_imageView;
            private TextView productTitle,freeCoupens,productPrice,cuttedPrice,coupenApplied,offerApplied,productQuantity;
            public CartItemViewHolder(@NonNull View itemView) {
                super(itemView);
                productImage=itemView.findViewById(R.id.product_imageView);
                productTitle=itemView.findViewById(R.id.product_title_textView);
                freeCoupens=itemView.findViewById(R.id.textView_freeCoupen);
                coupen_imageView=itemView.findViewById(R.id.coupen_imageView);
                productPrice=itemView.findViewById(R.id.textView_productPrice);
                cuttedPrice=itemView.findViewById(R.id.textView_cutedPrice);
                coupenApplied=itemView.findViewById(R.id.textView_coupens_applied);
                offerApplied=itemView.findViewById(R.id.textView_offers_applied);
                productQuantity=itemView.findViewById(R.id.textView_quantity_product);

            }
            private void setCartItemDetails(int resource,String title,
                                            int freecoupensNumber,String productprice,String cuttedprice,
                                            int offerappliedNo)
            {
               productImage.setImageResource(resource);
                productTitle.setText(title);

               if(freecoupensNumber>0)
               {
                   coupen_imageView.setVisibility(View.VISIBLE);
                   freeCoupens.setVisibility(View.VISIBLE);
                   if(freecoupensNumber==1) {
                       freeCoupens.setText("free"+  freecoupensNumber  + "coupen");
                   }
                   else
                   {
                       freeCoupens.setText("free"  + freecoupensNumber  + "coupens");
                   }
               }
               else {
                   coupen_imageView.setVisibility(View.INVISIBLE);
                   freeCoupens.setVisibility(View.INVISIBLE);
               }
                productPrice.setText(productprice);
               cuttedPrice.setText(cuttedprice);
               if(offerappliedNo>0)
               {
                   offerApplied.setVisibility(View.VISIBLE);
                   offerApplied.setText(offerappliedNo+  "offers applied");
               }
               else {
                   offerApplied.setVisibility(View.INVISIBLE);
               }
                productQuantity.setOnClickListener(new View.OnClickListener( ) {
                    @Override
                    public void onClick(View v) {
                        Dialog quantityDialog=new Dialog(itemView.getContext());
                        quantityDialog.setContentView(R.layout.quantity_dialog_layout);
                        //quantityDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                        quantityDialog.setCancelable(false);
                        EditText quantityEdit=quantityDialog.findViewById(R.id.quantity_editText);
                        Button quantityOkBtn=quantityDialog.findViewById(R.id.ok_button);
                        Button quantityCancelBtn=quantityDialog.findViewById(R.id.cancel_button);
                        quantityOkBtn.setOnClickListener(new View.OnClickListener( ) {
                            @Override
                            public void onClick(View v) {
                                productQuantity.setText("Qty: "+quantityEdit.getText());
                                quantityDialog.dismiss();
                            }
                        });
                        quantityCancelBtn.setOnClickListener(new View.OnClickListener( ) {
                            @Override
                            public void onClick(View v) {
                                quantityDialog.dismiss();
                            }
                        });
                        quantityDialog.show();
                    }
                });
//                if(coupenappliedNo>0)
//                {
//                    coupenApplied.setVisibility(View.VISIBLE);
//                    coupenApplied.setText(coupenappliedNo+"coupens applied");
//                }
//                else {
//                    coupenApplied.setVisibility(View.INVISIBLE);
//                }
            }
        }
        public class CartTotalAmountViewHolder extends RecyclerView.ViewHolder {
           private TextView totalItems,totalItemPrice,deliveryPrice,totalAmount,savedAmount;

            public CartTotalAmountViewHolder(@NonNull View itemView) {
                super(itemView);
                totalItems=itemView.findViewById(R.id.textView_total_items);
                totalItemPrice=itemView.findViewById(R.id.textView_total_price);
                deliveryPrice=itemView.findViewById(R.id.textView_delivery_price);
                totalAmount=itemView.findViewById(R.id.textView_total_amount);
                savedAmount=itemView.findViewById(R.id.textView_saved_amount);
            }
            private void setTotalAmount(String totalitems,String totalprice,String deliveryprice,String totalamount,String savedamount)
            {
               totalItems.setText(totalitems);
               totalItemPrice.setText(totalprice);
               deliveryPrice.setText(deliveryprice);
               totalAmount.setText(totalamount);
               savedAmount.setText(savedamount);
            }
        }
}
