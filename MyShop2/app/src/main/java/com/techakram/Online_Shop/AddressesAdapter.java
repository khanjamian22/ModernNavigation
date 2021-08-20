package com.techakram.Online_Shop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static com.techakram.Online_Shop.DeliveryActivity.SELECT_ADDRESS;
import static com.techakram.Online_Shop.MyAccount_Fragment.MANAGE_ADDRESS;
import static com.techakram.Online_Shop.MyAddressesActivity.refreshItem;

public class AddressesAdapter extends RecyclerView.Adapter<AddressesAdapter.ViewHolder> {
    private int MODE;
    private int preselected=-1;
    private List<AddresssModel> addresssModelList;

    public AddressesAdapter(List<AddresssModel> addresssModelList,int MODE) {
        this.addresssModelList = addresssModelList;
         this.MODE=MODE;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.addresses_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      String Name=addresssModelList.get(position).getFullName();
      String Address=addresssModelList.get(position).getAddress();
      String Pincode=addresssModelList.get(position).getPincode();
      Boolean selected=addresssModelList.get(position).getSelect();
      holder.setData(Name,Address,Pincode,selected,position);
    }

    @Override
    public int getItemCount() {
        return addresssModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView Fullname,Adderess,Pincode;
        private ImageView icon;
        private LinearLayout option_container;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Fullname=itemView.findViewById(R.id.tv_name);
            Adderess=itemView.findViewById(R.id.tv_address);
            Pincode=itemView.findViewById(R.id.tv_pincode);
            icon=itemView.findViewById(R.id.iconview);
            option_container=itemView.findViewById(R.id.optioncontainer);
        }
        private void setData(String name, String address, String pincode, Boolean selected,int position)
        {
            Fullname.setText(name);
            Adderess.setText(address);
            Pincode.setText(pincode);
            //for check MODE.........
            //logic for check uncheck select items....
            if(MODE==SELECT_ADDRESS)
            {
              icon.setImageResource(R.drawable.chechbutton);
              if(selected)
              {
               icon.setVisibility(View.VISIBLE);
               preselected=position;
              }
              else
              {
                  icon.setVisibility(View.INVISIBLE);
              }
              itemView.setOnClickListener(new View.OnClickListener( ) {
                  @Override
                  public void onClick(View v) {
                      if(preselected!=position) {
                          addresssModelList.get(position).setSelect(true);
                          addresssModelList.get(preselected).setSelect(false);
                          refreshItem(preselected, position);
                          preselected = position;
                      }
                  }
              });
              // for hiding open container click any where....
              itemView.setOnClickListener(new View.OnClickListener( ) {
                  @Override
                  public void onClick(View v) {
                   refreshItem(preselected,preselected);
                   preselected=-1;
                  }
              });
            }
            else if (MODE==MANAGE_ADDRESS)
            {
               option_container.setVisibility(View.GONE);
               icon.setImageResource(R.drawable.verticaldots);
               icon.setOnClickListener(new View.OnClickListener( ) {
                   @Override
                   public void onClick(View v) {
                       option_container.setVisibility(View.VISIBLE);
                       refreshItem(preselected,preselected);
                       preselected=position;
                   }
               });
            }
        }
    }
}
