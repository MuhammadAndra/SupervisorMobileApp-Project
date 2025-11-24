package com.example.supervisormobileapp_project.data.network.api_service

import com.example.supervisormobileapp_project.data.model.Company
import com.example.supervisormobileapp_project.data.model.PatrolSpot
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface CompanyApiService {
    @GET("v1/companies")
    suspend fun getCompanies(
        @Header("Authorization") token: String
    ): Response<List<Company>>

    @GET("v1/companies/{companyId}/patrol-spots")
    suspend fun getPatrolSpots(
        @Header("Authorization") token: String,
        @Path("companyId") companyId:Int
    ): Response<List<PatrolSpot>>
}