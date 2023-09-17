package com.refanzzzz.githubuser.ui.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.refanzzzz.githubuser.ui.DetailFragment

class GithubPagerAdapter(activity:AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        val fragment = DetailFragment()

        fragment.arguments = Bundle().apply {
            putInt(DetailFragment.ARG_SECTION_NUMBER, position)
        }

        return fragment
    }

    override fun getItemCount(): Int {
        return 2
    }



}