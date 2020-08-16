package com.stickearn.moviefavourite.view.main.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.stickearn.moviefavourite.R
import com.stickearn.moviefavourite.base.BaseActivity
import com.stickearn.moviefavourite.databinding.ActivityMainBinding
import com.stickearn.moviefavourite.model.popularmovie.PopularMovieDetail
import com.stickearn.moviefavourite.model.usertest.UserTestItem
import com.stickearn.moviefavourite.service.network.ApiResult
import com.stickearn.moviefavourite.view.favouritemovie.FavouriteMovieActivity
import com.stickearn.moviefavourite.view.main.MainAdapter
import com.stickearn.moviefavourite.view.main.adapter.PopularMovieAdapter
import com.stickearn.moviefavourite.view.main.adapter.TopRatedMovieAdapter
import com.stickearn.moviefavourite.view.moviedetail.MovieDetailActivity
import com.stickearn.moviefavourite.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        if(savedInstanceState == null)
        {
            title = getString(R.string.movie)
            binding.rcyPopularMovie.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            binding.rcyTopRatedMovie.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            binding.rcyNowPlaying.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            viewModel.getPopularMovie()
            viewModel.getNowPlayingMovies()
            viewModel.getTopRatedMovies()
            observeViewModel()
            binding.swpMain.setOnRefreshListener {
                viewModel.getPopularMovie()
                viewModel.getNowPlayingMovies()
                viewModel.getTopRatedMovies()
                swpMain.isRefreshing = false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.actFavourite -> {
                startActivity(Intent(applicationContext, FavouriteMovieActivity::class.java))
            }
        }
        return true
    }

    private fun observeViewModel(){
        viewModel.popularMovies.observe(this, Observer {
            when (it.status) {
                ApiResult.Status.LOADING -> {
                    binding.rcyPopularMovie.visibility = View.GONE
                    binding.shimmerPopularMovie.visibility = View.VISIBLE
                    binding.shimmerPopularMovie.startShimmer()
                }
                ApiResult.Status.ERROR -> {
                    Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
                }
                else -> {
                    binding.shimmerPopularMovie.stopShimmer()
                    val adapter =
                        PopularMovieAdapter(it.data!!.results, object: PopularMovieAdapter.OnClickCallback {
                            override fun toDetailMovie(popularMovieDetail: PopularMovieDetail) {
                                startActivity(Intent(applicationContext, MovieDetailActivity::class.java).putExtra(MovieDetailActivity.DETAIL_MOVIE_SELECTED, popularMovieDetail))
                            }
                        })

                    binding.rcyPopularMovie.adapter = adapter
                    binding.rcyPopularMovie.visibility = View.VISIBLE
                    binding.shimmerPopularMovie.visibility = View.GONE
                }
            }
        })

        viewModel.topRatedMovies.observe(this, Observer {
            when(it.status){
                ApiResult.Status.LOADING -> {
                    binding.rcyTopRatedMovie.visibility = View.GONE
                    binding.shimmerTopRatedMovie.visibility = View.VISIBLE
                    binding.shimmerTopRatedMovie.startShimmer()
                }
                ApiResult.Status.ERROR -> {
                    Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
                }
                else -> {
                    val adapter =
                        TopRatedMovieAdapter(it.data!!.results)
                    binding.rcyTopRatedMovie.adapter = adapter
                    binding.rcyTopRatedMovie.visibility = View.VISIBLE
                    binding.shimmerTopRatedMovie.visibility = View.GONE
                }
            }
        })

        viewModel.nowPlayingMovies.observe(this, Observer {
            when(it.status){
                ApiResult.Status.LOADING -> {
                    binding.rcyNowPlaying.visibility = View.GONE
                    binding.shimmerNowPlayingMovie.visibility = View.VISIBLE
                    binding.shimmerNowPlayingMovie.startShimmer()
                }
                ApiResult.Status.ERROR -> {
                    Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
                }
                else -> {
                    val adapter =
                        TopRatedMovieAdapter(it.data!!.results)

                    binding.rcyNowPlaying.adapter = adapter
                    binding.rcyNowPlaying.visibility= View.VISIBLE
                    binding.shimmerNowPlayingMovie.visibility = View.GONE
                }
            }
        })
    }
}