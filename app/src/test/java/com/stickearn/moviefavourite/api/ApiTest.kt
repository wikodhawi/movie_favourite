package com.stickearn.moviefavourite.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.stickearn.moviefavourite.BuildConfig
import com.stickearn.moviefavourite.service.network.Api
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.hamcrest.CoreMatchers
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@RunWith(JUnit4::class)
class ApiTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var service: Api

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun tesEndpoint() {
        runBlocking {
            enqueueResponse("now_playing_movie.json")
            val resultResponse = service.getNowPlaying(BuildConfig.API_KEY)
            val request = mockWebServer.takeRequest(1, TimeUnit.SECONDS)
            Assert.assertNotNull(resultResponse)
            Assert.assertThat(request!!.path, CoreMatchers.`is`("/movie/now_playing?api_key=${BuildConfig.API_KEY}"))

            enqueueResponse("popular_movie.json")
            val resultResponsePopularMovie = service.getPopularMovie(BuildConfig.API_KEY)
            val requestPopularMovie = mockWebServer.takeRequest(1, TimeUnit.SECONDS)
            Assert.assertNotNull(resultResponsePopularMovie)
            Assert.assertThat(requestPopularMovie!!.path, CoreMatchers.`is`("/movie/popular?api_key=${BuildConfig.API_KEY}"))

            enqueueResponse("top_rated_movie.json")
            val resultResponseTopRated = service.getTopRatedMovie(BuildConfig.API_KEY)
            val requestTopRated = mockWebServer.takeRequest(1, TimeUnit.SECONDS)
            Assert.assertNotNull(resultResponseTopRated)
            Assert.assertThat(requestTopRated!!.path, CoreMatchers.`is`("/movie/top_rated?api_key=${BuildConfig.API_KEY}"))
        }
    }

    @Test
    fun getNowPlayingMovie(){
        runBlocking {
            enqueueResponse("now_playing_movie.json")
            val resultResponse = service.getNowPlaying(BuildConfig.API_KEY)
            val selectedMovie = resultResponse.results[0]
            Assert.assertThat(selectedMovie.id, CoreMatchers.`is`(521034))
            Assert.assertThat(selectedMovie.releaseDate, CoreMatchers.`is`("2020-07-08"))
            Assert.assertThat(selectedMovie.backdropPath, CoreMatchers.`is`("/8PK4X8U3C79ilzIjNTkTgjmc4js.jpg"))
            Assert.assertThat(selectedMovie.posterPath, CoreMatchers.`is`("/5MSDwUcqnGodFTvtlLiLKK0XKS.jpg"))
            Assert.assertThat(selectedMovie.overview, CoreMatchers.`is`("Mary Lennox is born in India to wealthy British parents who never wanted her. When her parents suddenly die, she is sent back to England to live with her uncle. She meets her sickly cousin, and the two children find a wondrous secret garden lost in the grounds of Misselthwaite Manor."))
            Assert.assertThat(selectedMovie.title, CoreMatchers.`is`("The Secret Garden"))
        }
    }

    @Test
    fun getPopularMovie(){
        runBlocking {
            enqueueResponse("popular_movie.json")
            val resultResponse = service.getPopularMovie(BuildConfig.API_KEY)
            val selectedMovie = resultResponse.results[0]
            Assert.assertThat(selectedMovie.id, CoreMatchers.`is`(612706))
            Assert.assertThat(selectedMovie.releaseDate, CoreMatchers.`is`("2020-08-07"))
            Assert.assertThat(selectedMovie.backdropPath, CoreMatchers.`is`("/ishzDCZIv9iWfI70nv5E4ZreYUD.jpg"))
            Assert.assertThat(selectedMovie.posterPath, CoreMatchers.`is`("/b5XfICAvUe8beWExBz97i0Qw4Qh.jpg"))
            Assert.assertThat(selectedMovie.overview, CoreMatchers.`is`("A brilliant but clumsy high school senior vows to get into her late father's alma mater by transforming herself and a misfit squad into dance champions."))
            Assert.assertThat(selectedMovie.title, CoreMatchers.`is`("Work It"))
        }
    }

    @Test
    fun getTopRated(){
        runBlocking {
            enqueueResponse("top_rated_movie.json")
            val resultResponse = service.getTopRatedMovie(BuildConfig.API_KEY)
            val selectedMovie = resultResponse.results[0]
            Assert.assertThat(selectedMovie.id, CoreMatchers.`is`(724089))
            Assert.assertThat(selectedMovie.releaseDate, CoreMatchers.`is`("2020-07-31"))
            Assert.assertThat(selectedMovie.backdropPath, CoreMatchers.`is`("/jtAI6OJIWLWiRItNSZoWjrsUtmi.jpg"))
            Assert.assertThat(selectedMovie.posterPath, CoreMatchers.`is`("/pci1ArYW7oJ2eyTo2NMYEKHHiCP.jpg"))
            Assert.assertThat(selectedMovie.overview, CoreMatchers.`is`("Professor Gabriel Emerson finally learns the truth about Julia Mitchell's identity, but his realization comes a moment too late. Julia is done waiting for the well-respected Dante specialist to remember her and wants nothing more to do with him. Can Gabriel win back her heart before she finds love in another's arms?"))
            Assert.assertThat(selectedMovie.title, CoreMatchers.`is`("Gabriel's Inferno Part II"))
        }
    }

    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader!!
            .getResourceAsStream("api-response/$fileName")
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(mockResponse.setBody(
            source.readString(Charsets.UTF_8))
        )
    }
}