package com.bironader.musicsearch.framework.presentation.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.bironader.musicsearch.framework.presentation.MainActivity
import com.bironader.musicsearch.framework.utils.EspressoIdlingResource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EspressoIdlingResource.increment()
        Handler(Looper.getMainLooper()).postDelayed({
            EspressoIdlingResource.decrement()
            startActivity(Intent(this, MainActivity::class.java))
        }, 500)

    }
}