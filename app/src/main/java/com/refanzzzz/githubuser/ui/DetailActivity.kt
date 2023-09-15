package com.refanzzzz.githubuser.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.TooltipCompat
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.refanzzzz.githubuser.R
import com.refanzzzz.githubuser.data.response.GithubUserResponseDetail
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

        val detailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailViewModel::class.java)

        // TabLayout and ViewPager
        val githubPagerAdapter = GithubPagerAdapter(this)
        binding.vpFoll.adapter = githubPagerAdapter

        TabLayoutMediator(binding.tabFoll, binding.vpFoll) {
            tab, position ->
            run {
                tab.text = resources.getString(TAB_TITLES[position])
            }

        }.attach()


        val githubName = intent.getStringExtra(DetailViewModel.EXTRA_NAME)

        if (githubName != null) {
            detailViewModel.getGithubUserDetail(githubName)
        }

        detailViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        detailViewModel.detailGithubUser.observe(this) {
            setGithubUserDetail(it)
        }

        binding.fabFavoriteUser.setOnClickListener {
            addToFavorite()
        }
    }

    private fun init() {
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setGithubUserDetail(data: GithubUserResponseDetail) {
        binding.tvDetailName.text = data.name
        binding.tvDetailUsername.text = data.login
        Glide.with(this).load(data.avatarUrl).into(binding.ivDetailUser)
        binding.tvDetailFollowers.text = "${data.followers} Followers"
        binding.tvDetailFollowing.text = "${data.following} Following"
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressGithubUserDetail.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun addToFavorite() {
        Toast.makeText(this@DetailActivity, binding.tvDetailUsername.text, Toast.LENGTH_SHORT).show()
    }
}