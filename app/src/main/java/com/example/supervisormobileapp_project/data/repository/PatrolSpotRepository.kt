package com.example.supervisormobileapp_project.data.repository

import android.content.Context
import com.example.supervisormobileapp_project.data.model.PatrolSpot
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

    fun getToken(): String? = sharedPref.getString("AUTH_TOKEN", null)
}