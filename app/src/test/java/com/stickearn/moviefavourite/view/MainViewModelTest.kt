package com.stickearn.moviefavourite.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.stickearn.moviefavourite.repository.login.MainRepository
import com.stickearn.moviefavourite.viewmodel.MainViewModel
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito

@RunWith(JUnit4::class)
class MainViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val repository = Mockito.mock(MainRepository::class.java)
    private var viewModel = MainViewModel(repository)
    private val mockedViewModel = Mockito.mock(MainViewModel::class.java)

    @Test
    fun testNull() {
        MatcherAssert.assertThat(viewModel.popularMovies, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(viewModel.nowPlayingMovies, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(viewModel.topRatedMovies, CoreMatchers.notNullValue())
        Mockito.verify(mockedViewModel, Mockito.never()).getPopularMovie()
        Mockito.verify(mockedViewModel, Mockito.never()).getTopRatedMovies()
        Mockito.verify(mockedViewModel, Mockito.never()).getNowPlayingMovies()
    }

    @Test
    fun fetchDataFromChangeData() {
//        viewModel.queryParameterData.query = "p"
//
//        Mockito.verify(repository, Mockito.never()).getGithubUsers(viewModel.queryParameterData)
    }
}