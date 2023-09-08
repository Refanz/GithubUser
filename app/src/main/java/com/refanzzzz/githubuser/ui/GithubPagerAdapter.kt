package com.refanzzzz.githubuser.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class GithubPagerAdapter(activity:AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        val fragment = DetailFragment()

        /* fragment.arguments = Bundle().apply {
            putInt(DetailFragment.ARG_SECTION_NUMBER, position + 1)
        } */

        return fragment
    }

    override fun getItemCount(): Int {
        return 2
    }



}