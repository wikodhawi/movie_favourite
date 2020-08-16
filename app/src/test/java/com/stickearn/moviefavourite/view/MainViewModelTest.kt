package com.stickearn.moviefavourite.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ActivityScenario.launch
import com.nhaarman.mockitokotlin2.whenever
import com.stickearn.moviefavourite.model.popularmovie.PopularMovie
import com.stickearn.moviefavourite.repository.login.MainRepository
import com.stickearn.moviefavourite.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.`when`


@RunWith(JUnit4::class)
class MainViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val repository = Mockito.mock(MainRepository::class.java)
    private var viewModel = MainViewModel(repository)
    private val mockedViewModel = Mockito.mock(MainViewModel::class.java)
    private val mockPopularMovie = Mockito.mock(PopularMovie::class.java)

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
    fun `when called data from repository return with true data type`() {
        runBlocking {
            whenever(repository.getNowPlaying()).thenReturn(mockPopularMovie)
            whenever(repository.getPopularMovie()).thenReturn(mockPopularMovie)
            whenever(repository.getTopRatedMovie()).thenReturn(mockPopularMovie)
        }
    }
}