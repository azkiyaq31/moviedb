package com.example.moviedbtest.util

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedbtest.databinding.ItemDummyListBinding

class DummyListAdapter(
    val itemDummyWidth: Int,
    val itemDummyHeight: Int,
    val usingCaption: Boolean = false
) :
    RecyclerView.Adapter<DummyListAdapter.ItemDummyVH>() {

    inner class ItemDummyVH(
        val viewBind: ItemDummyListBinding
    ) :
        RecyclerView.ViewHolder(viewBind.root) {
        fun bind() {
            viewBind.apply {
            }
        }
    }

    override fun getItemCount(): Int = 5

    override fun onBindViewHolder(holder: ItemDummyVH, position: Int) {
        holder.bind()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemDummyVH {
        return ItemDummyVH(
            ItemDummyListBinding.inflate(
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
                viewDummyTitle.visibility = if (usingCaption) View.VISIBLE else View.GONE
                viewDummyDescr.visibility = if (usingCaption) View.VISIBLE else View.GONE
            }
        )
    }
}