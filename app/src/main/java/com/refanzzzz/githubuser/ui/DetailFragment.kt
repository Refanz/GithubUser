package com.refanzzzz.githubuser.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.refanzzzz.githubuser.R
import com.refanzzzz.githubuser.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    companion object {
        const val ARG_SECTION_NUMBER = "section_number"
    }

    private var _binding:FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val index = arguments?.getInt(ARG_SECTION_NUMBER, 0)
        //binding.tvSectionLabel.text = getString(R.string.content_tab_text, index)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}