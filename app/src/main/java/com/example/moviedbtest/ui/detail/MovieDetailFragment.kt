package com.example.moviedbtest.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.moviedbtest.BuildConfig
import com.example.moviedbtest.R
import com.example.moviedbtest.databinding.MovieDetailFragmentBinding
import com.example.moviedbtest.util.Constants.ARG_MOVIE_ID
import com.example.moviedbtest.util.getDeviceWidth
import com.example.moviedbtest.util.loadImage
import com.example.moviedbtest.util.shareToOthers
import com.example.moviedbtest.viewmodels.MovieDetailViewModel

class MovieDetailFragment : Fragment() {

    companion object {
        fun newInstance() = MovieDetailFragment()
    }

    private val viewModel: MovieDetailViewModel by activityViewModels()
    private lateinit var binding: MovieDetailFragmentBinding
    lateinit var reviewAdapter: MovieReviewListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.apply {
            movieId = arguments?.getInt(ARG_MOVIE_ID, 0) ?: 0
            favoriteState.observe(viewLifecycleOwner) {
                binding.imgvFav.setImageResource(if (it) R.drawable.ic_favorite_filled_red else R.drawable.ic_favorite_border)
            }
            contentResult.observe(viewLifecycleOwner) {
                if (it != null) {
                    binding.apply {
                        imgvBanner.loadImage(
                            BuildConfig.IMAGE_MOVIE_BASE_URL + (it.backdropPath ?: ""),
                            pbImage
                        )
                        tvTitle.text = it.title ?: ""
                        tvGenre.text = it.genreResponses?.firstOrNull()?.name ?: ""
                        tvDescr.text = it.overview ?: ""
                    }
                }
            }
            reviewResult.observe(viewLifecycleOwner) {
                if (it != null) {
                    reviewAdapter.replaceData(it)
                }
            }
            start()
        }
        binding = MovieDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        val item = menu.findItem(R.id.menu_fav)
        item.isVisible = false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        binding.apply {
            cardBanner.layoutParams = cardBanner.layoutParams.apply {
                height = requireActivity().getDeviceWidth() * 9 / 16
            }
            reviewAdapter = MovieReviewListAdapter(arrayListOf())
            rvReviews.adapter = reviewAdapter
            imgvShare.setOnClickListener {
                requireActivity().shareToOthers(
                    viewModel.contentResult.value?.title ?: "",
                    viewModel.contentResult.value?.overview ?: ""
                )
            }
            imgvFav.setOnClickListener {
                if (viewModel.favoriteState.value != true)
                    viewModel.addToFavorite()
                else
                    viewModel.removeFromFavorite()
            }
        }
    }


}