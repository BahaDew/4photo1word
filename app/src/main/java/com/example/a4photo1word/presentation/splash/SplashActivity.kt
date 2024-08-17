package com.example.a4photo1word.presentation.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Chronometer
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.WindowInsetsControllerCompat
import com.example.a4photo1word.R
import com.example.a4photo1word.presentation.game.GameActivity
import com.example.a4photo1word.presentation.main.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private val time = System.currentTimeMillis()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val chronometer = findViewById<Chronometer>(R.id.chronometer)
        chronometer.start()
        val cardLogo = findViewById<CardView>(R.id.card_logo)
        cardLogo
            .animate()
            .setDuration(600)
            .alpha(1f)
            .start()
        chronometer.setOnChronometerTickListener {
            if (System.currentTimeMillis() - time >= 1500) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        val wic = WindowInsetsControllerCompat(window, window.decorView)
        wic.isAppearanceLightStatusBars = true
    }
}