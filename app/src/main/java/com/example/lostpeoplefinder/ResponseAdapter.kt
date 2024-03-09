package com.example.lostpeoplefinder

import android.content.Context
import android.location.Location
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.Date

class ResponseAdapter (private val listener: OnItemClickListener,var context: Context, var list: ArrayList<OutputModel>) :
    RecyclerView.Adapter<ResponseAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResponseAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.output_rv_item, null, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.img.setImageResource(list[position].img)
        holder.nameText.text = list[position].personName.toString()
        holder.ageText.text=list[position].personAge.toString()
        holder.genderText.text=list[position].personGender.toString()
        holder.lastdate.text=list[position].last_date.toString()
        holder.lastLocation.text=list[position].personLastLocation.toString()


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