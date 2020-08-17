package com.stickearn.moviefavourite.view.moviedetail

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.stickearn.moviefavourite.BuildConfig
import com.stickearn.moviefavourite.R
import com.stickearn.moviefavourite.databinding.ActivityMovieDetailBinding
import com.stickearn.moviefavourite.model.popularmovie.PopularMovieDetail
import com.stickearn.moviefavourite.service.network.ApiResult
import com.stickearn.moviefavourite.utilities.extension.setOnSafeClickListener
import com.stickearn.moviefavourite.view.moviedetail.adapter.MovieReviewAdapter
import com.stickearn.moviefavourite.viewmodel.MovieDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat


class MovieDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailBinding
    private lateinit var movieDetailSelected : PopularMovieDetail
    private var isFavourite = false
    private val viewModel: MovieDetailViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)
        movieDetailSelected = intent.getSerializableExtra(DETAIL_MOVIE_SELECTED) as PopularMovieDetail
        binding.lblTitleMovie.text = movieDetailSelected.title
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val sdf2 = SimpleDateFormat("d MMM yyyy")
        val dateFormatted = sdf.parse(movieDetailSelected.releaseDate)
        binding.lblReleaseDate.text = sdf2.format(dateFormatted)
        binding.lblDescription.text = movieDetailSelected.overview
        Glide.with(this).load(BuildConfig.BASE_IMAGE_URL +movieDetailSelected.backdropPath)
            .placeholder(R.color.colorLoading)
            .into(binding.imgPoster)
        binding.rcyReviews.layoutManager = LinearLayoutManager(this)
        binding.fabAddToFavourite.setOnSafeClickListener {
            saveOrDeleteFavourite()
        }
        binding.fabShare.setOnSafeClickListener {
            shareLink()
        }
        viewModel.getReview(movieDetailSelected.id.toString())
        binding.swpMovieDetail.setOnRefreshListener {
            viewModel.getReview(movieDetailSelected.id.toString())
            binding.swpMovieDetail.isRefreshing = false
        }
        setFavouriteIcon()
        initToolbar()
        observeViewModel()
    }

    private fun shareLink() {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT, "${movieDetailSelected.title} movie https://www.themoviedb.org/movie/${movieDetailSelected.id}")
        sendIntent.type = "text/plain"
        startActivity(sendIntent)
    }

    private fun setFavouriteIcon() {
        viewModel.getSelectedFavouriteMovie(movieDetailSelected)
    }

    private fun saveOrDeleteFavourite() {
        if(isFavourite)
        {
            viewModel.deleteSelectedFavouriteMovie(movieDetailSelected)
            binding.fabAddToFavourite.setColorFilter(ContextCompat.getColor(applicationContext, R.color.md_white_1000))
        }
        else
        {
            viewModel.saveFavouriteMovie(movieDetailSelected)
            binding.fabAddToFavourite.setColorFilter(ContextCompat.getColor(applicationContext, R.color.colorRedFavourite))
            Toast.makeText(applicationContext, getString(R.string.addedToFavouriteMovie), Toast.LENGTH_SHORT).show()
        }
    }

    private fun observeViewModel() {
        viewModel.selectedMovieFromLocal.observe(this, Observer {
            if(it != null) {
                isFavourite = true
                binding.fabAddToFavourite.setColorFilter(ContextCompat.getColor(applicationContext, R.color.colorRedFavourite))
            }
        })
        viewModel.reviewMovies.observe(this, Observer {
            when (it.status) {
                ApiResult.Status.LOADING -> {
                    binding.rcyReviews.visibility = View.GONE
                    binding.shimmerReview.visibility = View.VISIBLE
                    binding.shimmerReview.startShimmer()
                }
                ApiResult.Status.ERROR -> {
                    Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
                }
                else -> {
                    binding.shimmerReview.stopShimmer()
                    val adapter = MovieReviewAdapter(it.data!!.results)

                    binding.rcyReviews.adapter = adapter
                    binding.rcyReviews.visibility = View.VISIBLE
                    binding.shimmerReview.visibility = View.GONE
                    binding.nstMovieDetail.scrollTo(0,0)
                }
            }
        })
    }

    private fun initToolbar() {
        binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.title = movieDetailSelected.title
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }

    companion object {
        const val DETAIL_MOVIE_SELECTED = "detailMovieSelected"
    }
}