package com.refanzzzz.githubuser.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.refanzzzz.githubuser.R
import org.w3c.dom.Text

class GithubUserAdapter(private val listGithubUser:ArrayList<String>) : RecyclerView.Adapter<GithubUserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user_github, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listGithubUser.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    inner class ViewHolder(view:View) :RecyclerView.ViewHolder(view) {
        val ivGithubUser:ImageView = view.findViewById(R.id.img_user_list)
        val tvUserName:TextView = view.findViewById(R.id.tv_user_name)
        val tvUserType:TextView = view.findViewById(R.id.tv_user_type)
    }
}