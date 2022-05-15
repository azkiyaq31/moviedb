package com.example.moviedbtest.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedbtest.BuildConfig
import com.example.moviedbtest.data.model.local.Movie
import com.example.moviedbtest.databinding.ItemFavoriteMovieListBinding
import com.example.moviedbtest.listener.ItemMovieListener
import com.example.moviedbtest.util.loadImage

class FavoriteMovieListAdapter(
    val mData: ArrayList<Movie>,
    val listener: ItemMovieListener<Movie>
) :
    RecyclerView.Adapter<FavoriteMovieListAdapter.ItemFavoriteMovieVH>() {

    fun replaceData(new: List<Movie>) {
        mData.clear()
        mData.addAll(new)
        notifyDataSetChanged()
    }

    inner class ItemFavoriteMovieVH(
        val viewBind: ItemFavoriteMovieListBinding
    ) :
        RecyclerView.ViewHolder(viewBind.root) {
        fun bind(obj: Movie) {
            viewBind.apply {
                root.setOnClickListener { listener.onItemClick(obj) }
                imgvBanner.loadImage(
                    BuildConfig.IMAGE_MOVIE_BASE_URL + obj.backdropPath,
                    pbImage
                )
                tvTitle.text = obj.title
                tvGenre.text = obj.genre
                tvDescr.text = obj.overview
            }
        }
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: ItemFavoriteMovieVH, position: Int) {
        holder.bind(mData[position])
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemFavoriteMovieVH {
        return ItemFavoriteMovieVH(
            ItemFavoriteMovieListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}