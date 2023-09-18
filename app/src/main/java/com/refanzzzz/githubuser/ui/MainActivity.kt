package com.refanzzzz.githubuser.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.refanzzzz.githubuser.R
import com.refanzzzz.githubuser.data.local.datastore.SettingPreferences
import com.refanzzzz.githubuser.data.local.datastore.dataStore
import com.refanzzzz.githubuser.data.remote.response.GithubUserResponseItem
import com.refanzzzz.githubuser.databinding.ActivityMainBinding
import com.refanzzzz.githubuser.ui.adapter.GithubUserAdapter
import com.refanzzzz.githubuser.ui.viewmodel.DetailViewModel
import com.refanzzzz.githubuser.ui.viewmodel.MainViewModel
import com.refanzzzz.githubuser.ui.viewmodel.SettingViewModel
import com.refanzzzz.githubuser.ui.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private var _activityMainBinding: ActivityMainBinding? = null
    private val binding get() = _activityMainBinding

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            MainViewModel::class.java)

        val pref = SettingPreferences.getInstance(application.dataStore)
        val settingsViewModel = ViewModelProvider(this, ViewModelFactory(pref)).get(
            SettingViewModel::class.java
        )

        settingsViewModel.getThemeSettings().observe(this@MainActivity) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                binding?.sbGithubUser?.menu?.findItem(R.id.menu_favorite)?.icon = getDrawable(R.drawable.baseline_favorite_24_light)
            } else {
                binding?.sbGithubUser?.menu?.findItem(R.id.menu_favorite)?.icon = getDrawable(R.drawable.baseline_favorite_24)
            }
        }



        with(binding!!) {
            sbGithubUser.inflateMenu(R.menu.option_menu)
            sbGithubUser.setOnMenuItemClickListener {
               when (it.itemId) {
                   R.id.menu_favorite -> {
                       val intent = Intent(this@MainActivity, FavoriteActivity::class.java)
                       startActivity(intent)
                       true
                   }

                   R.id.menu_setting -> {
                       val intent = Intent(this@MainActivity, SettingActivity::class.java)
                       startActivity(intent)
                       true
                   }

                   else -> {
                       false
                   }
               }
            }

            svGithubUser.setupWithSearchBar(sbGithubUser)
            svGithubUser
                .editText
                .setOnEditorActionListener { textView, actionId, event ->
                    sbGithubUser.text = svGithubUser.text
                    svGithubUser.hide()

                    if (sbGithubUser.text.isNullOrBlank()) {
                        mainViewModel.getAllGithubUser()
                        mainViewModel.userGithub.observe(this@MainActivity) {
                            setGithubUserData(it)
                        }
                    } else {
                        mainViewModel.getUserGithubByName(svGithubUser.text.toString())
                        mainViewModel.userGithubSearch.observe(this@MainActivity) {
                            setGithubUserData(it)
                        }
                    }

                    false
                }
        }


        mainViewModel.getAllGithubUser()

        initLayoutRecyclerView()

        mainViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        mainViewModel.userGithub.observe(this) {
            setGithubUserData(it)
        }
    }

    private fun initLayoutRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        binding?.rvGithubUser?.layoutManager = layoutManager
    }

    private fun setGithubUserData(githubUser: List<GithubUserResponseItem>) {
        val adapter = GithubUserAdapter(githubUser)
        binding?.rvGithubUser?.adapter = adapter

        adapter.setOnItemClickCallback(object: GithubUserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: GithubUserResponseItem) {
                showDetailGithubuser(data)
            }
        })

        if (adapter.itemCount < 1) {
            Toast.makeText(this@MainActivity, "Not Found!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding?.progressGithubUserList?.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showDetailGithubuser(data: GithubUserResponseItem) {
        //Toast.makeText(this@MainActivity, data.login, Toast.LENGTH_SHORT).show()
        val detailIntent = Intent(this@MainActivity, DetailActivity::class.java)
        detailIntent.putExtra(DetailViewModel.EXTRA_NAME, data.login)
        startActivity(detailIntent)
    }

    override fun onDestroy() {
        super.onDestroy()
        _activityMainBinding = null
    }


}