package com.example.supervisormobileapp_project.data.network

import com.example.supervisormobileapp_project.data.network.api_service.AuthApiService
import com.example.supervisormobileapp_project.data.network.api_service.CompanyApiService
import com.example.supervisormobileapp_project.data.network.api_service.PatrolSpotApiService
import com.example.supervisormobileapp_project.data.network.api_service.ProfileApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private const val BASEURL = "https://app.arunikaprawira.com/api/"
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val okHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASEURL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val authApi: AuthApiService by lazy { retrofit.create(AuthApiService::class.java) }
    val companyApi: CompanyApiService by lazy { retrofit.create(CompanyApiService::class.java) }
    val patrolSpotApi: PatrolSpotApiService by lazy { retrofit.create(PatrolSpotApiService::class.java) }
    val profileApi: ProfileApiService by lazy { retrofit.create(ProfileApiService::class.java) }
}