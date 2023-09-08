package com.refanzzzz.githubuser.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.refanzzzz.githubuser.data.response.GithubUserResponse
import com.refanzzzz.githubuser.data.response.GithubUserResponseItem
import com.refanzzzz.githubuser.databinding.ItemUserGithubBinding

class GithubUserAdapter(private val listGithubUser:List<GithubUserResponseItem>) : RecyclerView.Adapter<GithubUserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserGithubBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listGithubUser.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(listGithubUser[position]) {
                binding.tvUserName.text = this.login
                binding.tvUserType.text = this.type
                binding.tvUserId.text = this.id.toString()
                Glide.with(binding.root).load(this.avatarUrl).into(binding.imgUserList)
            }
        }
    }

    inner class ViewHolder(val binding:ItemUserGithubBinding) :RecyclerView.ViewHolder(binding.root)
}