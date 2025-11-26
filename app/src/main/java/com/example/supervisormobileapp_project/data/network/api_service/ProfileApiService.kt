package com.example.supervisormobileapp_project.data.network.api_service

import com.example.supervisormobileapp_project.data.model.EmployeeResponse
import com.example.supervisormobileapp_project.data.model.UserProfileResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT

interface ProfileApiService {
    @GET("v1/profile/employee")
    suspend fun fetchEmployeeProfile(@Header("Authorization") token: String): Response<EmployeeResponse>

    @GET("v1/profile/user")
    suspend fun getUserProfile(@Header("Authorization") token: String): Response<UserProfileResponse>
}