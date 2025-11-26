package com.example.supervisormobileapp_project.data.repository

import android.content.Context
import com.example.supervisormobileapp_project.data.model.Company
import com.example.supervisormobileapp_project.data.model.EmployeeResponse
import com.example.supervisormobileapp_project.data.model.Resource
import com.example.supervisormobileapp_project.data.model.UserProfileResponse
import com.example.supervisormobileapp_project.data.network.RetrofitClient
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ProfileRepository@Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val api = RetrofitClient.profileApi
    private val sharedPref = context.getSharedPreferences(
        "MyPrefs",
        Context.MODE_PRIVATE
    )

    suspend fun getUserProfile(): UserProfileResponse? {
        return try {
            val token = getToken() ?: ""
            val response = api.getUserProfile(token = "Bearer $token")
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }

        } catch (e: Exception) {
            null
        }
    }

    suspend fun getEmployeeProfile(): EmployeeResponse? {
        return try {
            val token = getToken() ?: ""
            val response = api.fetchEmployeeProfile(token = "Bearer $token")
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