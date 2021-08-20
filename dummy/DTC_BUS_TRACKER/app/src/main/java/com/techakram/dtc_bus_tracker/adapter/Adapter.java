package com.techakram.dtc_bus_tracker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.techakram.dtc_bus_tracker.R;
import com.techakram.dtc_bus_tracker.model.ModelClass;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.viewholder> {
    private List<ModelClass> modelClass;
    Context mContext;
    public Adapter(Context c, List<ModelClass> modelClass) {
        this.mContext=c;
        this.modelClass = modelClass;
    }
        @Override
        public viewholder onCreateViewHolder (@NonNull ViewGroup viewGroup,int i){
            View view = LayoutInflater.from(mContext).inflate(R.layout.cardview, viewGroup, false);
            return new viewholder(view);
        }

        @Override
        public void onBindViewHolder (@NonNull viewholder holder,int position){
            String Name = modelClass.get(position).getName();
            int Image=modelClass.get(position).getImageResource();
            String Context = modelClass.get(position).getContext();

//          //  holder.setData(Name,Roll,Address);

            holder.textView1.setText(Name);
            holder.imageView.setImageResource(Image);
            holder.textView2.setText(Context);
        }

        @Override
        public int getItemCount () {
            return modelClass.size();
        }

        class viewholder extends RecyclerView.ViewHolder {
          TextView textView1,textView2;
          ImageView imageView;
            public viewholder(@NonNull View itemView) {
                super(itemView);
                textView1= itemView.findViewById(R.id.tv1);
                imageView=itemView.findViewById(R.id.image);
                textView2=itemView.findViewById(R.id.tvContext);

            }
//           /* private void  setData(String Name,String Roll,String Address){
//             textView1.setText(Name);
//             textView2.setText(Roll);
//             textView3.setText(Address);
//            }*/
        }

    }






