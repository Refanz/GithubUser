package com.refanzzzz.githubuser.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.refanzzzz.githubuser.R
import com.refanzzzz.githubuser.databinding.ActivitySplashscreenBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashscreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashscreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()

        val text = getString(R.string.splashscreen_text)

        lifecycleScope.launch(Dispatchers.Default) {
            for (i in 0..text.length) {
                delay(75)

                withContext(Dispatchers.Main) {
                    if (i == text.length) {
                        val intent = Intent(this@SplashscreenActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        binding.tvSplachscreen.append(text[i].toString())
                    }
                }

            }
        }
    }

    private fun init() {
        binding = ActivitySplashscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}