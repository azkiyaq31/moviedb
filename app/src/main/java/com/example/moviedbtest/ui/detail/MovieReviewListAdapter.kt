package com.example.moviedbtest.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedbtest.data.model.remote.response.MovieResponse
import com.example.moviedbtest.data.model.remote.response.MovieReviewResponse
import com.example.moviedbtest.databinding.ItemMovieReviewBinding
import com.example.moviedbtest.listener.ItemMovieListener

class MovieReviewListAdapter(
    val mData: ArrayList<MovieReviewResponse>
) :
    RecyclerView.Adapter<MovieReviewListAdapter.ItemMovieReviewVH>() {

    fun replaceData(new: List<MovieReviewResponse>) {
        mData.clear()
        mData.addAll(new)
        notifyDataSetChanged()
    }

    inner class ItemMovieReviewVH(
        val viewBind: ItemMovieReviewBinding
    ) :
        RecyclerView.ViewHolder(viewBind.root) {
        fun bind(obj: MovieReviewResponse) {
            viewBind.apply {
                tvReview.text = "${obj.author}: ${obj.content ?: ""}"
            }
        }
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: ItemMovieReviewVH, position: Int) {
        holder.bind(mData[position])
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemMovieReviewVH {
        return ItemMovieReviewVH(
            ItemMovieReviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ).apply {
            }
        )
    }
}