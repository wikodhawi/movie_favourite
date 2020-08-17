package com.stickearn.moviefavourite.view.favouritemovie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.stickearn.moviefavourite.BuildConfig
import com.stickearn.moviefavourite.R
import com.stickearn.moviefavourite.databinding.ItemFavouriteMovieBinding
import com.stickearn.moviefavourite.model.popularmovie.PopularMovieDetail
import com.stickearn.moviefavourite.utilities.extension.setOnSafeClickListener
import com.stickearn.moviefavourite.view.main.adapter.PopularMovieAdapter
import java.text.SimpleDateFormat

class FavouriteMovieAdapter (private val popularMovies: List<PopularMovieDetail>, private val onClickCallback: PopularMovieAdapter.OnClickCallback) : RecyclerView.Adapter<FavouriteMovieAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemFavouriteMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding, onClickCallback)
    }

    class ItemViewHolder(private val binding: ItemFavouriteMovieBinding, private val onClickCallback: PopularMovieAdapter.OnClickCallback) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(
            item: PopularMovieDetail
        ) {
            Glide.with(itemView).load(BuildConfig.BASE_IMAGE_URL +item.backdropPath)
                .placeholder(R.color.colorLoading)
                .into(binding.imgMovie)
            binding.lblTitleMovie.text = item.title
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            val sdf2 = SimpleDateFormat("d MMM yyyy")
            val dateFormatted = sdf.parse(item.releaseDate)
            binding.lblReleaseDate.text = sdf2.format(dateFormatted)
            binding.lblOverview.text = item.overview
            itemView.setOnSafeClickListener {
                onClickCallback.toDetailMovie(item)
            }
        }
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item: PopularMovieDetail = popularMovies[position]
        holder.bindItem(item)
    }

    override fun getItemCount(): Int {
        return popularMovies.size
    }
}