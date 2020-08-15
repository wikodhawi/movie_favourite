package com.stickearn.moviefavourite.view.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.stickearn.moviefavourite.BuildConfig
import com.stickearn.moviefavourite.R
import com.stickearn.moviefavourite.databinding.ItemPopularMovieBinding
import com.stickearn.moviefavourite.model.popularmovie.PopularMovieDetail

class PopularMovieAdapter (private val popularMovies: List<PopularMovieDetail>) : RecyclerView.Adapter<PopularMovieAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemPopularMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    class ItemViewHolder(private val binding: ItemPopularMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(
            item: PopularMovieDetail
        ) {
            Glide.with(itemView).load(BuildConfig.BASE_IMAGE_URL +item.backdropPath)
                .placeholder(R.color.colorLoading)
                .into(binding.imgMovie)
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