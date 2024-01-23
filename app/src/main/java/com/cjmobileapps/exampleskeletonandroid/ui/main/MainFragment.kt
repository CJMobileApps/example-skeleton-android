package com.cjmobileapps.exampleskeletonandroid.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.cjmobileapps.exampleskeletonandroid.R
import com.cjmobileapps.exampleskeletonandroid.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mainPostList.layoutManager =
            LinearLayoutManager(requireContext())
        val adapter = MainAdapter(onPostClicked = { post ->
            val bundle = bundleOf("post" to post)
            findNavController().navigate(R.id.detailFragment, bundle)
        })
        binding.mainPostList.adapter = adapter

        viewModel.stateLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is MainViewModel.MainState.MainLoadedState -> {
                    val posts = state.posts

                    if (adapter.posts != posts) {
                        adapter.posts = posts
                        adapter.notifyItemRangeChanged(0, posts.size)
                    }
                }
            }
        }
    }
}
