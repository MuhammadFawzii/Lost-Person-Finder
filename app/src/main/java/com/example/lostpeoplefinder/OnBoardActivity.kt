package com.example.lostpeoplefinder

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.viewpager.widget.ViewPager
class OnBoardActivity : AppCompatActivity() {
    private lateinit var onBoardViewPager: ViewPager
    private lateinit var sliderAdapter: SliderAdapter
    private lateinit var onBoardButton: AppCompatButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_board)
        setAdapter()
    }


    private fun setAdapter() {
        onBoardViewPager = findViewById(R.id.onBoardViewPager)
        sliderAdapter = SliderAdapter(this)
        onBoardViewPager.adapter = sliderAdapter
        onBoardButton = findViewById(R.id.onBoardButton)

        if (onBoardViewPager.currentItem == 0) {
            onBoardButton.text = "Next"
        }

        onBoardButton.setOnClickListener {
            if (onBoardViewPager.currentItem != 2) {
                onBoardViewPager.setCurrentItem(onBoardViewPager.currentItem + 1, true)
            }
        }

        onBoardViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                if (onBoardViewPager.currentItem == 2) {
                    onBoardButton.setOnClickListener {
                        val intent = Intent(this@OnBoardActivity, LoginPageActivity::class.java)
                        startActivity(intent)
                    }
                }
                onBoardViewPager.setCurrentItem(onBoardViewPager.currentItem, true)
                when (onBoardViewPager.currentItem) {
                    1, 0 -> onBoardButton.text = "Next"
                    2 -> onBoardButton.text = "Finish"
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        })
    }
}
