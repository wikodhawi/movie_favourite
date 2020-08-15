package com.stickearn.moviefavourite.di.module

import com.google.gson.GsonBuilder
import com.stickearn.moviefavourite.BuildConfig
import com.stickearn.moviefavourite.service.network.Api
import com.stickearn.moviefavourite.service.network.ApiHolder
import com.google.gson.Gson
import okhttp3.Dispatcher
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val apiModule = module {
    single { provideGson() }
    single { provideRetrofit(get(), get()) }
    single { provideOkHttpClient() }
    single { provideApiHolder()}
    single { provideApi(get(), get()) }
}

const val BASE_URL = BuildConfig.BASE_URL
const val TIMEOUT = 5L
val TIMEOUT_UNIT = TimeUnit.MINUTES

fun provideApi(retrofit: Retrofit, apiHolder: ApiHolder): Api {
    val api = retrofit.create(Api::class.java)
    apiHolder.api = api
    return api
}
fun provideApiHolder() = ApiHolder()

fun provideGson() = GsonBuilder().serializeNulls().create()

fun provideRetrofit(
    okHttpClient: OkHttpClient,
    gson: Gson) =
    Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).client(
        okHttpClient
    ).build()

fun provideOkHttpClient(): OkHttpClient {
    val builder = OkHttpClient.Builder().readTimeout(TIMEOUT, TIMEOUT_UNIT).connectTimeout(
        TIMEOUT, TIMEOUT_UNIT
    )
    if (BuildConfig.DEBUG) {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        builder.addInterceptor(httpLoggingInterceptor)
    }

    val dispatcher = Dispatcher()
    dispatcher.maxRequests = 1
    builder.dispatcher(dispatcher)


    builder.addInterceptor { chain ->
        var request = chain.request()
        val newBuilder = request.newBuilder()
        val url = BASE_URL
        val httpUrl = url.toHttpUrlOrNull()
        httpUrl?.let {
            if (!request.url.toString().contains(httpUrl.toString())) {
                val newUrl =
                    request.url.newBuilder()
                        .scheme(httpUrl.scheme)
                        .host(httpUrl.host)
                        .port(httpUrl.port)
                if (httpUrl.pathSegments.isNotEmpty())
                    newUrl.setPathSegment(0, httpUrl.pathSegments[0])
                newBuilder.url(newUrl.build())
            }
        }
        request = newBuilder.build()
        chain.proceed(request)
    }
    return builder.build()
}
