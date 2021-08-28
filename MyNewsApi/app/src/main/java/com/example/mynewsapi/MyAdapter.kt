package com.example.mynewsapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
class MyAdapter(val song :List<Song>): RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
      val inflater :LayoutInflater= LayoutInflater.from(parent.context)
        val view:View=inflater.inflate(R.layout.item_view,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
      holder.textTitle.text=song[position].title
      holder.textDisc.text=song[position].disc
    }

    override fun getItemCount(): Int {
        return song.size
    }
}
class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    var textTitle=itemView.findViewById<TextView>(R.id.tvTitle);
    var textDisc=itemView.findViewById<TextView>(R.id.tvDiscription);

}