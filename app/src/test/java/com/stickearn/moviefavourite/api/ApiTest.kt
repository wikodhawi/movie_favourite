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
//
            val requestTopRated = mockWebServer.takeRequest(1, TimeUnit.SECONDS)
            Assert.assertNotNull(resultResponseTopRated)
            Assert.assertThat(requestTopRated!!.path, CoreMatchers.`is`("/movie/top_rated?api_key=${BuildConfig.API_KEY}"))
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