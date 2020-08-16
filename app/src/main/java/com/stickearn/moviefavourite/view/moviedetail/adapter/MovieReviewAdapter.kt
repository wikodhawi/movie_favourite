package com.stickearn.moviefavourite.view.moviedetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.stickearn.moviefavourite.BuildConfig
import com.stickearn.moviefavourite.R
import com.stickearn.moviefavourite.databinding.ItemReviewBinding
import com.stickearn.moviefavourite.databinding.ItemTopRatedMovieBinding
import com.stickearn.moviefavourite.model.moviereview.MovieReviewDetail
import com.stickearn.moviefavourite.model.popularmovie.PopularMovieDetail
import com.stickearn.moviefavourite.view.main.adapter.TopRatedMovieAdapter
import java.text.SimpleDateFormat

class MovieReviewAdapter (private val reviewMovies: List<MovieReviewDetail>) : RecyclerView.Adapter<MovieReviewAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    class ItemViewHolder(private val binding: ItemReviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(
            item: MovieReviewDetail
        ) {
            binding.lblAuthor.text = item.author
            binding.lblContent.text = item.content
        }
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item: MovieReviewDetail = reviewMovies[position]
        holder.bindItem(item)
    }

    override fun getItemCount(): Int {
        return reviewMovies.size
    }
}