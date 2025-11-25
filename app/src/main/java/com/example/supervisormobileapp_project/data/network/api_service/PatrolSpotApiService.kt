package com.example.supervisormobileapp_project.data.network.api_service

import com.example.supervisormobileapp_project.data.model.EditPatrolSpotResponse
import com.example.supervisormobileapp_project.data.model.PatrolSpot
import com.example.supervisormobileapp_project.data.model.VerifyNfcResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface PatrolSpotApiService {
    @GET("v1/patrol-spots/{patrolSpotId}")
    suspend fun getPatrolSpot(
        @Header("Authorization") token: String,
        @Path("patrolSpotId") patrolSpotId:Int
    ): Response<PatrolSpot>

    @POST("v1/patrol-spots/{patrolSpotId}")
    suspend fun editPatrolSpot(
        @Header("Authorization") token: String,
        @Path("patrolSpotId") patrolSpotId:Int,
        @Body editedPatrolSpot: PatrolSpot
    ): Response<EditPatrolSpotResponse>

    @POST("v1/patrol-spots/{patrolSpotId}/verify")
    suspend fun verifyNfc(
        @Header("Authorization") token: String,
        @Path("patrolSpotId") patrolSpotId:Int,
        @Body verifyingPatrolSpot: PatrolSpot
    ): Response<VerifyNfcResponse>
}