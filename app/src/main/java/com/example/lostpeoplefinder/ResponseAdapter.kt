package com.example.lostpeoplefinder

import android.content.Context
import android.location.Location
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.util.Date


class ResponseAdapter (private val listener: OnItemClickListener,var context: Context, var list: ArrayList<Person>) :
    RecyclerView.Adapter<ResponseAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResponseAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.output_rv_item, null, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(context)
            .load(list[position].image_url)
            .into(holder.img)
        holder.nameText.text = list[position].person_name
        holder.ageText.text=list[position].age
        val x=list[position].gender
        var gen:String = if(x=="1")
            "Male"
        else
            "female"

        holder.genderText.text=gen
        holder.lastdate.text=list[position].date_of_lost
        holder.lastLocation.text=list[position].lat


    }

    override fun getItemCount(): Int {
    return list.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener {
        var img:ImageView
        var nameText:TextView
        var ageText:TextView
        var genderText:TextView
        var lastdate:TextView
        var lastLocation:TextView

        init {
            img = itemView.findViewById(R.id.imageView)
            ageText=itemView.findViewById(R.id.tv_age)
            nameText = itemView.findViewById(R.id.tv_name)
            genderText=itemView.findViewById(R.id.tv_gender)
            lastdate = itemView.findViewById(R.id.tv_lastseenDate)
            lastLocation=itemView.findViewById(R.id.tv_lastsennLocation)
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(list[position])
            }
        }
    }

}