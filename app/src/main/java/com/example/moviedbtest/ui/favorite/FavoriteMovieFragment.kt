package com.example.moviedbtest.ui.favorite

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.moviedbtest.R
import com.example.moviedbtest.data.model.local.Movie
import com.example.moviedbtest.databinding.FavoriteMovieFragmentBinding
import com.example.moviedbtest.listener.ItemMovieListener
import com.example.moviedbtest.ui.detail.FavoriteMovieListAdapter
import com.example.moviedbtest.util.Constants
import com.example.moviedbtest.util.NavigationUtil
import com.example.moviedbtest.util.WidgetUtil
import com.example.moviedbtest.viewmodels.FavoriteMovieViewModel

class FavoriteMovieFragment : Fragment(), ItemMovieListener<Movie> {

    companion object {
        fun newInstance() = FavoriteMovieFragment()
    }

    private lateinit var favMovieAdapter: FavoriteMovieListAdapter
    private val viewModel: FavoriteMovieViewModel by activityViewModels()
    private lateinit var binding: FavoriteMovieFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.apply {
            favMovieResult.observe(viewLifecycleOwner){
                if (it != null) {
                    favMovieAdapter.replaceData(it)
                }
            }
            start()
        }
        binding = FavoriteMovieFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onItemClick(obj: Movie) {
        val bundle = Bundle().apply {
            putInt(Constants.ARG_MOVIE_ID, obj.id ?: 0)
        }
        NavigationUtil.navigateFragment(
            binding.root,
            R.id.action_favorite_movie_fragment_to_movie_detail_fragment,
            WidgetUtil.getNavOptions(
                R.anim.enter_from_right,
                R.anim.exit_to_right,
                R.anim.enter_from_left,
                R.anim.exit_to_right
            ), bundle
        )
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        val item = menu.findItem(R.id.menu_fav)
        item.isVisible = false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        binding.apply {
            favMovieAdapter = FavoriteMovieListAdapter(arrayListOf(), this@FavoriteMovieFragment)
            rvFavorite.adapter = favMovieAdapter
        }
    }

}