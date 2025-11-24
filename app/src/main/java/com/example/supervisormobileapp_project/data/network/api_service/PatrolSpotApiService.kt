package com.example.supervisormobileapp_project.data.network.api_service

import com.example.supervisormobileapp_project.data.model.PatrolSpot
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface PatrolSpotApiService {
    @GET("v1/patrol-spots/{patrolSpotId}")
    suspend fun getPatrolSpot(
        @Header("Authorization") token: String,
        @Path("patrolSpotId") patrolSpotId:Int
    ): Response<PatrolSpot>
}