package com.example.lostpeoplefinder

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.facebook.shimmer.ShimmerFrameLayout


class DetailsActivity2 : AppCompatActivity() {
    // Variables created for buttons and Shimmer
    lateinit var button1: Button
    lateinit var button2: Button
    var container: ShimmerFrameLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gender_item)
        button1 = findViewById(R.id.button)
        //button2 = findViewById(R.id.button2)

        // Button 1 to start Shimmer Effect
        button1.setOnClickListener(View.OnClickListener { // If auto-start is set to false
            container!!.startShimmer()
        })

        // Button 2 to stop Shimmer Effect
        button2.setOnClickListener(View.OnClickListener { // If auto-start is set to false
            container!!.stopShimmer()
        })

        // Shimmer effect
        container = findViewById<View>(R.id.shimmer_view_container) as ShimmerFrameLayout
    }
}
