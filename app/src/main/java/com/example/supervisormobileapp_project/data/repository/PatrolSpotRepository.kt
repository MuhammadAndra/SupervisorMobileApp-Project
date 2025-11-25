package com.example.supervisormobileapp_project.data.repository

import android.content.Context
import android.util.Log
import com.example.supervisormobileapp_project.data.model.EditPatrolSpotResponse
import com.example.supervisormobileapp_project.data.model.PatrolSpot
import com.example.supervisormobileapp_project.data.model.Resource
import com.example.supervisormobileapp_project.data.model.VerifyNfcResponse
import com.example.supervisormobileapp_project.data.network.RetrofitClient
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PatrolSpotRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val api = RetrofitClient.patrolSpotApi
    private val sharedPref = context.getSharedPreferences(
        "MyPrefs",
        Context.MODE_PRIVATE
    )

    suspend fun getPatrolSpot(patrolSpotId: Int): PatrolSpot? {
        return try {
            val token = getToken() ?: ""
            val response = api.getPatrolSpot(
                token = "Bearer $token",
                patrolSpotId = patrolSpotId
            )
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    suspend fun editPatrolSpot(
        patrolSpotId: Int,
        editedPatrolSpot: PatrolSpot
    ): Resource<EditPatrolSpotResponse> {
        return try {
            val token = getToken() ?: ""
            val response = api.editPatrolSpot(
                token = "Bearer $token",
                patrolSpotId = patrolSpotId,
                editedPatrolSpot = editedPatrolSpot
            )
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Resource.Success(body)
                } else {
                    Resource.Error("Empty response body")
                }
            } else {
                Resource.Error("Server error (${response.code()})")
            }
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "Unexpected error")
        }
    }

    suspend fun verifyNfc(
        patrolSpotId: Int,
        verifyingPatrolSpot: PatrolSpot
    ): Resource<VerifyNfcResponse> {
        return try{
            val token = getToken() ?: ""
            val response = api.verifyNfc(token = "Bearer $token",
                patrolSpotId = patrolSpotId,
                verifyingPatrolSpot = verifyingPatrolSpot
            )
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Resource.Success(body)
                } else {
                    Resource.Error("Empty response body")
                }
            } else {
                Resource.Error("Server error (${response.code()})")
            }
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "Unexpected error")
        }
    }

    fun getToken(): String? = sharedPref.getString("AUTH_TOKEN", null)
}