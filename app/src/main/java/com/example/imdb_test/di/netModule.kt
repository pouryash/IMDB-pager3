package com.example.imdb_test.di

import com.example.imdb_test.data.api.MovieDiscoverWebService
import com.example.imdb_test.utils.Const
import okhttp3.CertificatePinner
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

const val DISCOVER_MOVIE_BASE_URL = "DISCOVER_MOVIE_BASE_URL"
const val DISCOVER_MOVIE_DI = "DISCOVER_MOVIE_DI"

val netModule = module {

    single(named(DISCOVER_MOVIE_BASE_URL)) {
        Const.BASE_URL
    }

    single(named(DISCOVER_MOVIE_DI)) {
        val baseUrl: String = get(named(DISCOVER_MOVIE_BASE_URL))
        val client = createOKHttpClient()
        createRetrofit(client, baseUrl)
    }

    single<MovieDiscoverWebService> { createRetrofitService(get(named(DISCOVER_MOVIE_DI))) }

}


private val loggingInterceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}


private const val CONNECT_TIMEOUT = 5L
private const val READ_TIMEOUT = 10L
private const val WRITE_TIMEOUT = 10L


fun createOKHttpClient(): OkHttpClient {


    val certificatePinner = CertificatePinner.Builder()
        .add("ddn.csdiran.com", "sha256/fyC/fwsOUBFIi+kxzCBNFhTlZQDZbEpgUGVjIx9Kkhg=")
        .add("ddn.csdiran.com", "sha256/1du3EfOp93FwG10en54tLejZ+LiGC4VDvyvFeaa50D8=")
        .add("ddn.csdiran.com", "sha256/lSwgOcAkPrUV3XPYP8NkMYSHT+sIYqmDdzHtm0dC4Xo=")
        .build()

    return OkHttpClient.Builder()
        .addInterceptor(AuthHeaderInterceptor())
        .certificatePinner(certificatePinner)
        .addInterceptor(loggingInterceptor)
        .hostnameVerifier { _, _ ->
            true
        }
        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        .build()
}

fun createRetrofit(client: OkHttpClient, baseUrl: String): Retrofit =
    Retrofit.Builder()
        .client(client)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

inline fun <reified T : Any> createRetrofitService(retrofit: Retrofit): T =
    retrofit.create(T::class.java)

class AuthHeaderInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

            val newReq = chain.request().newBuilder().addHeader("Content-Type", "application/json")
                .build()
            return chain.proceed(newReq)
    }
}