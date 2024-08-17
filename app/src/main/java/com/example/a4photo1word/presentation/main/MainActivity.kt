package com.example.a4photo1word.presentation.main

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.RippleDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.WindowInsetsControllerCompat
import com.example.a4photo1word.R
import com.example.a4photo1word.domain.repository.AppRepository
import com.example.a4photo1word.presentation.dialog.ExitDialog
import com.example.a4photo1word.presentation.game.GameActivity
import com.example.a4photo1word.presentation.info.InfoActivity


class MainActivity : AppCompatActivity() {
    private var time = 0L
    private lateinit var appRepository: AppRepository
    private lateinit var img1: ImageView
    private lateinit var img2: ImageView
    private lateinit var img3: ImageView
    private lateinit var img4: ImageView
    private lateinit var txtLevel: TextView
    private lateinit var txtCoinCnt: TextView

    @SuppressLint(
        "InternalInsetResource", "DiscouragedApi", "UseCompatLoadingForDrawables",
        "MissingInflatedId", "SetTextI18n"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            val statusBarHeight = resources.getDimensionPixelSize(resourceId)
            val sb = findViewById<View>(R.id.sb)
            sb.layoutParams =
                ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight)
        }
        val btnInfo = findViewById<ImageView>(R.id.btn_info)
        btnInfo.setOnClickListener {
            if (System.currentTimeMillis() - time >= 500) {
                time = System.currentTimeMillis()
                val intent = Intent(this, InfoActivity::class.java)
                startActivity(intent)
            }
        }
        val btnPlay = findViewById<ImageView>(R.id.btn_play)
        btnPlay.setOnClickListener {
            if (System.currentTimeMillis() - time >= 500) {
                time = System.currentTimeMillis()
                val intent = Intent(this, GameActivity::class.java)
                startActivity(intent)
            }
        }
        val wcc = WindowInsetsControllerCompat(window, window.decorView)
        wcc.isAppearanceLightStatusBars = false
        val background = getResources().getDrawable(R.drawable.play_btn, null)
        val color = Color.parseColor("#94FFFFFF")
        btnPlay.setImageDrawable(getBackgroundDrawable(color, background))
        appRepository = AppRepository.getInstance()
        txtLevel = findViewById(R.id.txt_level)
        img1 = findViewById(R.id.img1)
        img2 = findViewById(R.id.img2)
        img3 = findViewById(R.id.img3)
        img4 = findViewById(R.id.img4)
        getQuestion()
        txtCoinCnt = findViewById(R.id.txt_coin_cnt)
        txtCoinCnt.text = appRepository.getCoinCnt().toString()
        val dialog = ExitDialog()
        dialog.setOnClickYes {
            dialog.dismiss()
            this.finishAffinity()
        }
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                dialog.show(supportFragmentManager, "exit")
            }
        }
        onBackPressedDispatcher.addCallback(onBackPressedCallback)
    }

    private fun getBackgroundDrawable(
        pressedColor: Int,
        backgroundDrawable: Drawable?
    ): RippleDrawable {
        return RippleDrawable(getPressedState(pressedColor), backgroundDrawable, null)
    }

    private fun getPressedState(pressedColor: Int): ColorStateList {
        return ColorStateList(arrayOf(intArrayOf()), intArrayOf(pressedColor))
    }

    @SuppressLint("SetTextI18n")
    private fun getQuestion() {
        val data = appRepository.getDataQuestion()
        txtLevel.text = (appRepository.getLevel() + 1).toString()
        img1.setImageResource(data.getImages()[0])
        img2 = findViewById(R.id.img2)
        img2.setImageResource(data.getImages()[1])
        img3.setImageResource(data.getImages()[2])
        img4.setImageResource(data.getImages()[3])
    }

    override fun onResume() {
        super.onResume()
        getQuestion()
        txtCoinCnt.text = appRepository.getCoinCnt().toString()
    }
}