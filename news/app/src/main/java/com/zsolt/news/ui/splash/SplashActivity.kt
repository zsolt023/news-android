package com.zsolt.news.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zsolt.news.databinding.ActivitySplashBinding
import com.zsolt.news.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {

    private var _binding: ActivitySplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        navigateForward()
    }

    private fun navigateForward() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}