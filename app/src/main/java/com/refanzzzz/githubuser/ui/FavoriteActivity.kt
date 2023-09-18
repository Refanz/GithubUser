package com.refanzzzz.githubuser.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.refanzzzz.githubuser.data.local.entity.FavoriteGithubUser
import com.refanzzzz.githubuser.databinding.ActivityFavoriteBinding
import com.refanzzzz.githubuser.ui.adapter.FavoriteUserAdapter
import com.refanzzzz.githubuser.ui.viewmodel.DetailViewModel
import com.refanzzzz.githubuser.ui.viewmodel.FavoriteGithubUserViewModel
import com.refanzzzz.githubuser.ui.viewmodel.ViewModelFactory

class FavoriteActivity : AppCompatActivity() {

    private var _activityFavoriteBinding:ActivityFavoriteBinding? = null
    private val binding get() = _activityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()

        initRecyclerView()

        val favoriteGithubUserViewModel = obtainViewModel(this@FavoriteActivity)

        favoriteGithubUserViewModel.getAllFavoriteGithubUser().observe(this) {favoriteUserList ->
            if (favoriteUserList != null) {
                setFavoriteGithubUser(favoriteUserList)
            }
        }

        binding?.appBarFavorite?.topBarApp?.title = "Favorite User"

        binding?.appBarFavorite?.topBarApp?.setOnClickListener {
            val mainIntent = Intent(this@FavoriteActivity, MainActivity::class.java)
            startActivity(mainIntent)
            finish()
        }
    }

    private fun init() {
        _activityFavoriteBinding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }

    private fun initRecyclerView() {
        binding?.rvFavoriteUser?.layoutManager = LinearLayoutManager(this)
        binding?.rvFavoriteUser?.setHasFixedSize(true)
    }

    private fun setFavoriteGithubUser(favoriteUserList: List<FavoriteGithubUser>) {
        val adapter = FavoriteUserAdapter()
        adapter.setListFavoriteGithubUser(favoriteUserList)
        binding?.rvFavoriteUser?.adapter = adapter
        adapter.setOnItemClickCallback(object : FavoriteUserAdapter.OnItemClickCallback {
            override fun onItemClicked(favoriteUser: FavoriteGithubUser) {
                showDetailGithubuser(favoriteUser)
            }

        })

        if (adapter.itemCount < 1) {
            Toast.makeText(this@FavoriteActivity, "User Favorites List is Empty!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): FavoriteGithubUserViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(FavoriteGithubUserViewModel::class.java)
    }

    private fun showDetailGithubuser(favoriteUser: FavoriteGithubUser) {
        val detailIntent = Intent(this@FavoriteActivity, DetailActivity::class.java)
        detailIntent.putExtra(DetailViewModel.EXTRA_NAME, favoriteUser.username)
        startActivity(detailIntent)
    }

    override fun onDestroy() {
        super.onDestroy()
        _activityFavoriteBinding = null
    }


}