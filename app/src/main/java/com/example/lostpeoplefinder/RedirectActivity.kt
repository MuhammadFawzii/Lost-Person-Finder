package com.example.lostpeoplefinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class RedirectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Check Remember Me status
        val rememberMe = getRememberMeStatus()

        // Start the appropriate activity
        if (rememberMe) {
            startHomeActivity()
        } else {
            startOnBoardActivity()
        }

        // Finish the LauncherActivity so it won't appear in the back stack
        finish()
    }

    private fun getRememberMeStatus(): Boolean {
        // Retrieve Remember Me status from SharedPreferences
        // Return true if the user wants to be remembered, false otherwise
        var rm=RememberMeHandler.getInstance(this)
        return rm.checkRememberMeState()
    }

    private fun startHomeActivity() {
        // Launch the HomeActivity
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun startOnBoardActivity() {
        // Launch the LoginActivity
        val inte = Intent(this, OnBoardActivity::class.java)
        startActivity(inte)
    }
}