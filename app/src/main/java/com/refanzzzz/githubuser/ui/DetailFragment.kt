package com.refanzzzz.githubuser.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.refanzzzz.githubuser.data.response.GithubUserResponseItem
import com.refanzzzz.githubuser.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    companion object {
        const val ARG_SECTION_NUMBER = "section_number"
    }

    private var _binding:FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var userFollViewModel:UserFollViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        userFollViewModel = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory())[UserFollViewModel::class.java]

        userFollViewModel.getUserGithubFollowers(activity?.intent?.getStringExtra(DetailViewModel.EXTRA_NAME).toString())
        userFollViewModel.getUserGithubFollowing(activity?.intent?.getStringExtra(DetailViewModel.EXTRA_NAME).toString())

        val index = arguments?.getInt(ARG_SECTION_NUMBER, 0)


        userFollViewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }

        if (index == 0) {
            userFollViewModel.listUserGithubFollowers.observe(viewLifecycleOwner) {
                setUserGithubFoll(it)
            }
        } else if (index == 1) {
            userFollViewModel.listUserGithubFollowing.observe(viewLifecycleOwner) {
                setUserGithubFoll(it)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvGithubFoll.layoutManager = layoutManager
    }

    private fun setUserGithubFoll(userFoll: List<GithubUserResponseItem>) {
        val adapter = GithubUserFollAdapter(userFoll)
        binding.rvGithubFoll.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressGithubUserFoll.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}