package com.example.lostpeoplefinder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ReportsAdapter(private val listener: OnItemClickListener, var context: Context, var list: ArrayList<Person>) :
RecyclerView.Adapter<ReportsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportsAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.report_item,parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context)
            .load(list[position].image_url)
            .into(holder.img)
        holder.name.text ="Name: " +list[position].person_name
        holder.date.text ="Date: "+ list[position].date_of_lost
        holder.age.text="Age: " + list[position].age
    }
    override fun getItemCount(): Int {
        return list.size
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) , View.OnClickListener {
        var age: TextView
        var img: ImageView
        var name: TextView
        var date: TextView

        init {
            name = itemView.findViewById(R.id.name)
            img = itemView.findViewById(R.id.report_image)
            age = itemView.findViewById(R.id.age)
            date = itemView.findViewById(R.id.date)
            itemView.setOnClickListener(this)

        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(list[position])
            }
        }


    }



}