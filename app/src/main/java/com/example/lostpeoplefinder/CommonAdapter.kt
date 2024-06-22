package com.example.lostpeoplefinder

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CommonAdapter(private val listener: OnItemClickListener,var context: Context, var list: ArrayList<Person>) :
    RecyclerView.Adapter<CommonAdapter.ViewHolder>() {

//    fun getLayoutFile(context: Context): Int {
//        val activityName = (context as? Activity)?.javaClass?.simpleName
//        return when (activityName) {
//            "HomeActivity" -> R.layout.child_rv_layout
//            "ResponseActivity" -> R.layout.output_rv_item
//            else -> R.layout.child_rv_layout
//        }
//    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.child_rv_layout,parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context)
            .load(list[position].image_url)
            .into(holder.img)
        holder.headerText.text="Missing!!"
        holder.nameText.text = list[position].person_name
        holder.dataText.text = list[position].email
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) ,View.OnClickListener {
        var headerText: TextView
        var img: ImageView
        var nameText: TextView
        var dataText: TextView

        init {
            headerText = itemView.findViewById(R.id.headertext)
            img = itemView.findViewById(R.id.iv_child_item)
            nameText = itemView.findViewById(R.id.personName)
            dataText = itemView.findViewById(R.id.personData)
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
