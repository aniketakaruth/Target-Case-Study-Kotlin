package com.target.targetcasestudy


import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var targetLogo :ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        targetLogo = findViewById(R.id.target_logo)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val logoAnim = AnimationUtils.loadAnimation(this, R.anim.splash_anim)
        targetLogo.startAnimation(logoAnim)
        logoAnim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()

            }

            override fun onAnimationRepeat(animation: Animation) {}
        })


    }
}