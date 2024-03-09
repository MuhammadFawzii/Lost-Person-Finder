package com.example.lostpeoplefinder

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.github.dhaval2404.imagepicker.ImagePicker
import java.io.File


class DetailsActivity3 : AppCompatActivity() {
    lateinit var constraintLayout7: ConstraintLayout
    lateinit var textView11: TextView
    lateinit var img:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_template)
        textView11 = findViewById<TextView>(R.id.textView11)
        val pickImageButton = findViewById<TextView>(R.id.tv_browsePhoto)
        constraintLayout7 = findViewById(R.id.constraintLayout7)
        img=findViewById(R.id.img)
        pickImageButton.setOnClickListener {
            ImagePicker.with(this)
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }
        val btn_next3 = findViewById<Button>(R.id.btn_next3)
        val btn_previous3 = findViewById<Button>(R.id.btn_previous3)
        btn_next3.setOnClickListener {
            startActivity(Intent(this, DetailsActivity4::class.java))
        }
        btn_previous3.setOnClickListener {
            startActivity(Intent(this, DetailsActivity1::class.java))
        }

        }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            val uri: Uri = data?.data!!
            img.visibility=View.VISIBLE
            constraintLayout7.visibility = View.VISIBLE
            textView11.visibility = View.VISIBLE
            // Use Uri object instead of File to avoid storage permissions
            img.setImageURI(uri)
            val file: File = File(uri.path)
            //send it to next page

        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }
    }