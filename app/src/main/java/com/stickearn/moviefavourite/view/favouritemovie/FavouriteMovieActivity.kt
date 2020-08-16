package com.stickearn.moviefavourite.view.favouritemovie

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.stickearn.moviefavourite.R
import com.stickearn.moviefavourite.base.BaseActivity
import com.stickearn.moviefavourite.databinding.ActivityFavouriteMovieBinding
import com.stickearn.moviefavourite.model.popularmovie.PopularMovieDetail
import com.stickearn.moviefavourite.view.favouritemovie.adapter.FavouriteMovieAdapter
import com.stickearn.moviefavourite.view.main.adapter.PopularMovieAdapter
import com.stickearn.moviefavourite.view.moviedetail.MovieDetailActivity
import com.stickearn.moviefavourite.viewmodel.FavouriteMovieViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouriteMovieActivity : BaseActivity() {

    private lateinit var binding: ActivityFavouriteMovieBinding
    private val viewModel: FavouriteMovieViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_favourite_movie)
        binding.rcyFavouriteMovie.layoutManager = LinearLayoutManager(this)
        viewModel.getAllFavouriteMovie()
        observeViewModel()
        initToolbar()
    }

    private fun initToolbar() {
        binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.title = getString(R.string.favouriteMovie)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }

    override fun onRestart() {
        super.onRestart()
        viewModel.getAllFavouriteMovie()
    }

    private fun observeViewModel() {
        viewModel.allFavouriteMovie.observe(this, Observer {
            val adapter =
                FavouriteMovieAdapter(it, object: PopularMovieAdapter.OnClickCallback {
                    override fun toDetailMovie(popularMovieDetail: PopularMovieDetail) {
                        startActivity(
                            Intent(applicationContext, MovieDetailActivity::class.java).putExtra(
                                MovieDetailActivity.DETAIL_MOVIE_SELECTED, popularMovieDetail))
                    }
                })

            binding.rcyFavouriteMovie.adapter = adapter
        })
    }
}