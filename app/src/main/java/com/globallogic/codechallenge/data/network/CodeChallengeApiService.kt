package com.globallogic.codechallenge.data.network

import com.globallogic.codechallenge.BuildConfig
import com.globallogic.codechallenge.data.network.response.ItemData
import com.globallogic.codechallenge.data.network.response.ItemsReponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface CodeChallengeApiService {

    @GET("list")
    fun getItems(): Deferred<List<ItemData>>

    companion object {
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ): CodeChallengeApiService {
            val requestInterceptor = Interceptor { chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CodeChallengeApiService::class.java)
        }
    }
}