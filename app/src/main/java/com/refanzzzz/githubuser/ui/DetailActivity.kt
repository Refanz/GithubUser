package com.refanzzzz.githubuser.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.refanzzzz.githubuser.R
import com.refanzzzz.githubuser.data.local.entity.FavoriteGithubUser
import com.refanzzzz.githubuser.data.remote.response.GithubUserResponseDetail
import com.refanzzzz.githubuser.databinding.ActivityDetailBinding
import com.refanzzzz.githubuser.ui.adapter.GithubPagerAdapter
import com.refanzzzz.githubuser.ui.viewmodel.DetailViewModel
import com.refanzzzz.githubuser.ui.viewmodel.FavoriteGithubUserViewModel
import com.refanzzzz.githubuser.ui.viewmodel.ViewModelFactory

class DetailActivity : AppCompatActivity() {

    private lateinit var favoriteGithubUserViewModel: FavoriteGithubUserViewModel

    private var _activityDetailBinding: ActivityDetailBinding? = null
    private val binding get() = _activityDetailBinding

    private var isFavorite: Boolean? = false
    private var favoriteUser: FavoriteGithubUser? = null

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

        favoriteGithubUserViewModel = obtainViewModel(this@DetailActivity)

        val detailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            DetailViewModel::class.java
        )

        // TabLayout and ViewPager
        val githubPagerAdapter = GithubPagerAdapter(this)
        binding?.vpFoll?.adapter = githubPagerAdapter

        TabLayoutMediator(binding!!.tabFoll, binding!!.vpFoll) { tab, position ->
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

        favoriteGithubUserViewModel.getFavoriteGithubUserByUsername(githubName.toString())
            .observe(this) { favoriteUser ->
                if (favoriteUser != null) {
                    Glide.with(this).load(R.drawable.baseline_favorite_24)
                        .into(binding!!.fabFavoriteUser)
                    isFavorite = true
                    this.favoriteUser = favoriteUser
                }
            }

        binding?.fabFavoriteUser?.setOnClickListener {
            detailViewModel.detailGithubUser.observe(this) {
                if (isFavorite!!) {
                    removeFromFavorite(favoriteUser!!)
                    Toast.makeText(this, "User has been removed to favorites!", Toast.LENGTH_SHORT)
                        .show()
                    Glide.with(this).load(R.drawable.baseline_favorite_border_24).into(binding!!.fabFavoriteUser)
                    isFavorite = false
                } else {
                    addToFavorite(it)
                    isFavorite = true
                    Glide.with(this).load(R.drawable.baseline_favorite_24).into(binding!!.fabFavoriteUser)
                    Toast.makeText(this, "User added to favorites", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun init() {
        _activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }

    private fun setGithubUserDetail(data: GithubUserResponseDetail) {
        binding?.tvDetailName?.text = data.name
        binding?.tvDetailUsername?.text = data.login
        Glide.with(this).load(data.avatarUrl).into(binding!!.ivDetailUser)
        binding?.tvDetailFollowers?.text = "${data.followers} Followers"
        binding?.tvDetailFollowing?.text = "${data.following} Following"
    }

    private fun showLoading(isLoading: Boolean) {
        binding?.progressGithubUserDetail?.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun addToFavorite(data: GithubUserResponseDetail) {
        val user = FavoriteGithubUser()

        user.let { user ->
            user.avatarUrl = data.avatarUrl
            user.username = data.login
            user.isFavorite = true
            user.userType = data.type
        }

        favoriteGithubUserViewModel.insertFavoriteGithubUser(user)
    }

    private fun removeFromFavorite(user: FavoriteGithubUser) {
        favoriteGithubUserViewModel.deleteFavoriteGithubUser(user)
    }

    private fun checkUser(user: FavoriteGithubUser) {

    }

    private fun obtainViewModel(activity: AppCompatActivity): FavoriteGithubUserViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(FavoriteGithubUserViewModel::class.java)
    }


    override fun onDestroy() {
        super.onDestroy()
        _activityDetailBinding = null
    }
}