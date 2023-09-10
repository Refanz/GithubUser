package com.refanzzzz.githubuser.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.refanzzzz.githubuser.data.response.GithubUserResponseItem
import com.refanzzzz.githubuser.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)

        with(binding) {
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
        binding.rvGithubUser.layoutManager = layoutManager
    }

    private fun setGithubUserData(githubUser: List<GithubUserResponseItem>) {
        val adapter = GithubUserAdapter(githubUser)
        binding.rvGithubUser.adapter = adapter

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
        binding.progressGithubUserList.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showDetailGithubuser(data: GithubUserResponseItem) {
        //Toast.makeText(this@MainActivity, data.login, Toast.LENGTH_SHORT).show()
        val detailIntent = Intent(this@MainActivity, DetailActivity::class.java)
        detailIntent.putExtra(DetailViewModel.EXTRA_NAME, data.login)
        startActivity(detailIntent)
    }
}