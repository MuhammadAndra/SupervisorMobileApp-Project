package com.example.supervisormobileapp_project.data.repository

import android.content.Context
import com.example.supervisormobileapp_project.data.model.Company
import com.example.supervisormobileapp_project.data.model.PatrolSpot
import com.example.supervisormobileapp_project.data.network.RetrofitClient
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CompanyRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val api = RetrofitClient.companyApi
    private val sharedPref = context.getSharedPreferences(
        "MyPrefs",
        Context.MODE_PRIVATE
    )

    suspend fun getCompanies(): List<Company> {
        return try {
            val token = getToken() ?: ""
            val response = api.getCompanies(token = "Bearer $token")
            if (response.isSuccessful){
                response.body() ?: emptyList()
            }else{
                emptyList()
            }
        } catch (e: Exception) {
            //harusnya dikasih respon error yang bener
            emptyList()
        }
    }

    suspend fun getPatrolSpots(companyId: Int): List<PatrolSpot> {
        return try {
            val token = getToken() ?: ""
            val response = api.getPatrolSpots(token = "Bearer $token", companyId = companyId)
            if (response.isSuccessful){
                response.body()?:emptyList()
            }else{
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }


    fun getToken(): String? = sharedPref.getString("AUTH_TOKEN", null)
}