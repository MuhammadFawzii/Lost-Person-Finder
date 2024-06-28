package com.example.lostpeoplefinder

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope.coroutineContext
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        val appName:TextView=findViewById(R.id.appName)
        val text:TextView=findViewById(R.id.searchforhope)

        val animationTopToCenter = TranslateAnimation(
            Animation.RELATIVE_TO_SELF, 0f,
            Animation.RELATIVE_TO_SELF, 0f,
            Animation.RELATIVE_TO_SELF, -3f,
            Animation.RELATIVE_TO_SELF, 0f
        )
        animationTopToCenter.duration = 1500 // Animation duration in milliseconds
        appName.startAnimation(animationTopToCenter)

        // Animation for the second word (bottom to center)
        val animationBottomToCenter = TranslateAnimation(
            Animation.RELATIVE_TO_SELF, 0f,
            Animation.RELATIVE_TO_SELF, 0f,
            Animation.RELATIVE_TO_SELF, 3f,
            Animation.RELATIVE_TO_SELF, 0f
        )
        animationBottomToCenter.duration = 1500 // Animation duration in milliseconds
        text.startAnimation(animationBottomToCenter)


       // appName.animate().translationY(1500f).setDuration(1000).setStartDelay(5000)
        CoroutineScope(Dispatchers.Main).launch {
            delay(4000)
            startActivity(Intent(this@IntroActivity, LoginPageActivity::class.java))
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Cancel any ongoing coroutines when the activity is destroyed
        coroutineContext.cancel()
    }

    }
