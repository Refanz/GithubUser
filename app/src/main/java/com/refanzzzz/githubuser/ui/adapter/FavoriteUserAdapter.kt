package com.refanzzzz.githubuser.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.refanzzzz.githubuser.data.local.entity.FavoriteGithubUser
import com.refanzzzz.githubuser.databinding.ItemUserGithubBinding

class FavoriteUserAdapter : RecyclerView.Adapter<FavoriteUserAdapter.ViewHolder>() {

    private val listFavoriteGithubUser = ArrayList<FavoriteGithubUser>()

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setListFavoriteGithubUser(listFavoriteGithubUser: List<FavoriteGithubUser>) {
        this.listFavoriteGithubUser.clear()
        this.listFavoriteGithubUser.addAll(listFavoriteGithubUser)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserGithubBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(listFavoriteGithubUser[position]) {
                binding.tvUserType.text = this.userType
                binding.tvUserName.text = this.username
                Glide.with(binding.root).load(this.avatarUrl).into(binding.imgUserList)
                itemView.setOnClickListener {
                    onItemClickCallback.onItemClicked(this)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return listFavoriteGithubUser.size
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ViewHolder(val binding: ItemUserGithubBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(favoriteUser: FavoriteGithubUser)
    }
}