package com.dicoding.picodiploma.movieapplication.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.dicoding.picodiploma.movieapplication.R
import com.dicoding.picodiploma.movieapplication.ui.home.HomeActivity

class SplashScreenActivity : AppCompatActivity() {

    companion object{
        private const val DELAY_MILLIS: Long = 3000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler(mainLooper).postDelayed({
            // Move activity after delay
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }, DELAY_MILLIS)
    }
}