package com.stickearn.moviefavourite.view.main.activity

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.stickearn.moviefavourite.R
import com.stickearn.moviefavourite.base.BaseActivity
import com.stickearn.moviefavourite.databinding.ActivityMainBinding
import com.stickearn.moviefavourite.model.usertest.UserTestItem
import com.stickearn.moviefavourite.view.main.MainAdapter
import com.stickearn.moviefavourite.view.main.adapter.PopularMovieAdapter
import com.stickearn.moviefavourite.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        title = getString(R.string.movie)
        binding.rcyPopularMovie.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        viewModel.getPopularMovie()
        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.popularMovies.observe(this, Observer {
            val adapter =
                PopularMovieAdapter(it.results)

            binding.rcyPopularMovie.adapter = adapter
        })
        viewModel.errorMessage.observe(this, Observer {
            Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
        })
        val loadingDialog = loadingDialog(this)
        viewModel.isLoading.observe(this, Observer {
            if(it)
            {
                loadingDialog.show()
            }
            else
                loadingDialog.dismiss()
        })
    }
}