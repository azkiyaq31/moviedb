package com.example.moviedbtest.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedbtest.BuildConfig
import com.example.moviedbtest.R
import com.example.moviedbtest.data.model.remote.response.MovieResponse
import com.example.moviedbtest.databinding.ItemDummyListBinding
import com.example.moviedbtest.databinding.ItemHomeMovieListBinding
import com.example.moviedbtest.listener.ItemMovieListener
import com.example.moviedbtest.util.loadImage

class HomeMovieListAdapter(
    val itemDummyWidth: Int,
    val itemDummyHeight: Int,
    val usingCaption: Boolean = false,
    val mData: ArrayList<MovieResponse>,
    val listener: ItemMovieListener<MovieResponse>
) :
    RecyclerView.Adapter<HomeMovieListAdapter.ItemHomeMovieVH>() {

    fun replaceData(new: List<MovieResponse>) {
        mData.clear()
        mData.addAll(new)
        notifyDataSetChanged()
    }

    inner class ItemHomeMovieVH(
        val viewBind: ItemHomeMovieListBinding
    ) :
        RecyclerView.ViewHolder(viewBind.root) {
        fun bind(obj: MovieResponse) {
            viewBind.apply {
                root.setOnClickListener { listener.onItemClick(obj) }
                imgvBanner.loadImage(
                    BuildConfig.IMAGE_MOVIE_BASE_URL + (obj.backdropPath ?: ""),
                    pbImage
                )
                tvTitle.text = (obj.title ?: "").trim()
                tvDescr.text = root.context.getString(R.string.rating_is)
                    .replace("#", (obj.voteAvg ?: 0.0).toString())
            }
        }
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: ItemHomeMovieVH, position: Int) {
        holder.bind(mData[position])
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemHomeMovieVH {
        return ItemHomeMovieVH(
            ItemHomeMovieListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ).apply {
                root.layoutParams = root.layoutParams.apply {
                    width = itemDummyWidth
                }
                cardBanner.layoutParams = cardBanner.layoutParams.apply {
                    height = itemDummyHeight
                }
                tvTitle.visibility = if (usingCaption) View.VISIBLE else View.GONE
                tvDescr.visibility = if (usingCaption) View.VISIBLE else View.GONE
            }
        )
    }
}