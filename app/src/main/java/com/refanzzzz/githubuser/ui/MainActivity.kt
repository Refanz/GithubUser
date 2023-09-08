package com.refanzzzz.githubuser.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.refanzzzz.githubuser.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            svGithubUser.setupWithSearchBar(sbGithubUser)
            svGithubUser
                .editText
                .setOnEditorActionListener { textView, actionId, event ->
                    sbGithubUser.text = svGithubUser.text
                    svGithubUser.hide()
                    Toast.makeText(this@MainActivity, svGithubUser.text, Toast.LENGTH_SHORT).show()
                    false
                }
        }
    }
}