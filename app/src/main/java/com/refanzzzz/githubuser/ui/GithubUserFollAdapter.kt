package com.refanzzzz.githubuser.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.refanzzzz.githubuser.data.response.GithubUserResponseItem
import com.refanzzzz.githubuser.databinding.ItemUserFollBinding

class GithubUserFollAdapter(private val listUserGithubFoll: List<GithubUserResponseItem>) : RecyclerView.Adapter<GithubUserFollAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserFollBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(listUserGithubFoll[position]) {
                binding.tvFollName.text = this.login
                Glide.with(binding.root).load(this.avatarUrl).into(binding.imgFollList)
            }
        }
    }

    override fun getItemCount(): Int {
        return listUserGithubFoll.size
    }

    inner class ViewHolder(val binding: ItemUserFollBinding): RecyclerView.ViewHolder(binding.root)

}