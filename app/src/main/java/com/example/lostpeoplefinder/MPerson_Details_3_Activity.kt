package com.example.lostpeoplefinder

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

import com.google.android.gms.cast.framework.media.ImagePicker

class MPerson_Details_3_Activity : AppCompatActivity() {
    private val PICK_IMAGE = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mperson_details3)
        val pickImageButton = findViewById<TextView>(R.id.tv_browsePhoto)
        pickImageButton.setOnClickListener {
            val pickIntent =
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(pickIntent, PICK_IMAGE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            val selectedImageUri: Uri? = data.data
            if (selectedImageUri != null) {
                // Load the selected image into the ImageView using Glide
                val imageView = findViewById<ImageView>(R.id.img)
                imageView.visibility=View.VISIBLE
                Glide.with(this)
                    .load(selectedImageUri)
                    .into(imageView)
            }
        }
    }
}