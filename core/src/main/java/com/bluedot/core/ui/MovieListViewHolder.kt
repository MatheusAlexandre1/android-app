package com.bluedot.core.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bluedot.core.R
import com.bluedot.core.databinding.MovieItemListBinding
import com.bluedot.core.extensions.loadFromUrl
import com.bluedot.core.model.Movie

class MovieListViewHolder internal constructor(
    itemView: View,
    val onClick: ((Movie) -> Unit)? = null
) : RecyclerView.ViewHolder(itemView) {

    private var binding = MovieItemListBinding.bind(itemView)

    fun bind(item: Movie) {
        binding.tvTitle.text = item.title
        binding.tvLanguage.text = item.originalLanguage?.uppercase()
        binding.tvReleaseDate.text = item.releaseDate
        binding.tvVoteCount.text =
            item.voteCount?.let { count ->
                itemView.resources.getQuantityString(
                    R.plurals.votes,
                    count,
                    count
                )
            }
        binding.tvRating.text = item.voteAverage.toString()
        binding.ivPoster.loadFromUrl(item.posterPath)

        itemView.setOnClickListener {
            onClick?.invoke(item)
        }
    }
}