package com.stickearn.moviefavourite.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.stickearn.moviefavourite.service.database.dao.PopularMovieDetailDao
import com.stickearn.moviefavourite.util.testMovie1
import com.stickearn.moviefavourite.util.testMovie2
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PopularMovieDaoTest : DbTest() {
    private lateinit var popularMovieDetailDao: PopularMovieDetailDao
    private val setA = testMovie1.copy()
    private val setB = testMovie2.copy()
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        popularMovieDetailDao = db.popularMovieDetailDao()
        runBlocking {
            popularMovieDetailDao.insert(setA)
            popularMovieDetailDao.insert(setB)
        }
    }

    @Test
    fun testGetPopularMovie() {
        val list = popularMovieDetailDao.getAll()
        Assert.assertThat(list.size, Matchers.equalTo(2))

        Assert.assertThat(list[0], Matchers.equalTo(setA))
        Assert.assertThat(list[1], Matchers.equalTo(setB))
    }

    @Test
    fun testQueryDeleteFromDao() {
        runBlocking {
            popularMovieDetailDao.delete(setA)

            val list = popularMovieDetailDao.getAll()
            Assert.assertThat(list.size, Matchers.equalTo(1))
        }
    }

    @Test
    fun testQueryFind1FromDao() {
        runBlocking {
            val dataFound = popularMovieDetailDao.getById(setA.id.toString())
            Assert.assertThat(dataFound, Matchers.equalTo(setA))
        }
    }
}