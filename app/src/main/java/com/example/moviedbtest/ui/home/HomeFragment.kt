package com.example.moviedbtest.ui.home

import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.moviedbtest.R
import com.example.moviedbtest.databinding.HomeFragmentBinding
import com.example.moviedbtest.util.DummyListAdapter
import com.example.moviedbtest.util.getDeviceWidth
import com.example.moviedbtest.util.px
import com.example.moviedbtest.viewmodels.HomeViewModel

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel: HomeViewModel by activityViewModels()
    private lateinit var binding: HomeFragmentBinding
    lateinit var popularMovieAdapter: HomeMovieListAdapter
    lateinit var topratedMovieAdapter: HomeMovieListAdapter
    lateinit var nowPlayingMovieAdapter: HomeMovieListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.apply {
            nowPlayMovieRequesting.observe(viewLifecycleOwner) {
                if (it) {
                    binding.rvDummyNowPlaying.visibility =
                        if (nowPlayingMovieAdapter.mData.isEmpty()) View.VISIBLE else View.INVISIBLE
                    binding.shmrNowPlaying.showShimmer(true)
                } else {
                    binding.rvDummyNowPlaying.visibility = View.INVISIBLE
                    binding.shmrNowPlaying.stopShimmer()
                    binding.shmrNowPlaying.hideShimmer()
                }
            }
            popMoviesRequesting.observe(viewLifecycleOwner) {
                if (it) {
                    binding.rvDummyPopular.visibility =
                        if (popularMovieAdapter.mData.isEmpty()) View.VISIBLE else View.INVISIBLE
                    binding.shmrPopular.showShimmer(true)
                } else {
                    binding.rvDummyPopular.visibility = View.INVISIBLE
                    binding.shmrPopular.stopShimmer()
                    binding.shmrPopular.hideShimmer()
                }
            }
            topRatedMoviesRequesting.observe(viewLifecycleOwner) {
                if (it) {
                    binding.rvDummyTopRated.visibility =
                        if (topratedMovieAdapter.mData.isEmpty()) View.VISIBLE else View.INVISIBLE
                    binding.shmrTopRated.showShimmer(true)
                } else {
                    binding.rvDummyTopRated.visibility = View.INVISIBLE
                    binding.shmrTopRated.stopShimmer()
                    binding.shmrTopRated.hideShimmer()
                }
            }
            popularMoviesResult.observe(viewLifecycleOwner) {
                if (it != null) {
                    popularMovieAdapter.replaceData(it)
                }
            }
            popularMoviesResult.observe(viewLifecycleOwner) {
                if (it != null) {
                    popularMovieAdapter.replaceData(it)
                }
            }
            nowPlayResult.observe(viewLifecycleOwner) {
                if (it != null) {
                    nowPlayingMovieAdapter.replaceData(it)
                }
            }
            topRatedMoviesResult.observe(viewLifecycleOwner) {
                if (it != null) {
                    topratedMovieAdapter.replaceData(it)
                }
            }
            start()
        }
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            popularMovieAdapter = HomeMovieListAdapter(
                (requireActivity().getDeviceWidth() - (16.px)) * 3 / 4,
                ((requireActivity().getDeviceWidth() - (16.px)) * 3 / 4) * 9 / 16,
                false,
                arrayListOf(),
            )
            topratedMovieAdapter = HomeMovieListAdapter(
                96.px,
                96.px, true,
                arrayListOf(),
            )
            nowPlayingMovieAdapter = HomeMovieListAdapter(
                96.px,
                96.px, true,
                arrayListOf(),
            )
            rvNowPlaying.adapter = nowPlayingMovieAdapter
            rvPopular.adapter = popularMovieAdapter
            rvTopRated.adapter = topratedMovieAdapter
            rvDummyPopular.adapter =
                DummyListAdapter(
                    (requireActivity().getDeviceWidth() - (16.px)) * 3 / 4,
                    ((requireActivity().getDeviceWidth() - (16.px)) * 3 / 4) * 9 / 16
                )
            rvDummyTopRated.adapter =
                DummyListAdapter(
                    96.px,
                    96.px, true
                )

            rvDummyNowPlaying.adapter =
                DummyListAdapter(
                    96.px,
                    96.px, true
                )
        }
    }

}