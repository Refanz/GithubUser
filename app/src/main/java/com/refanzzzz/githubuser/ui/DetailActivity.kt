package com.refanzzzz.githubuser.ui

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.TooltipCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.refanzzzz.githubuser.R
import com.refanzzzz.githubuser.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding:ActivityDetailBinding

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()

        val githubPagerAdapter = GithubPagerAdapter(this)
        binding.vpFoll.adapter = githubPagerAdapter

        TabLayoutMediator(binding.tabFoll, binding.vpFoll) {
            tab, position ->
            run {
                tab.text = resources.getString(TAB_TITLES[position])
            }

        }.attach()

        //supportActionBar?.elevation = 0f
    }

    private fun init() {
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}