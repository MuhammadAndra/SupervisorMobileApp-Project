package com.example.supervisormobileapp_project.data.repository

import android.content.Context
import android.util.Log
import com.example.supervisormobileapp_project.data.network.RetrofitClient
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class AuthRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val api = RetrofitClient.authApi
    private val sharedPref = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    suspend fun login(email: String, password: String): Response<ResponseBody> {
        val request = hashMapOf(
            "email" to  email,
            "password" to password
        )
        return api.login(request)
    }

    suspend fun requestOtp(email: String): Response<ResponseBody> {
        val emailMap = hashMapOf("email" to email)
        return api.requestOtp(emailMap)
    }

    suspend fun verifyOtp(email: String, otp: String): Response<ResponseBody>{
        val map = hashMapOf("email" to email, "otp" to otp)
        return api.verifyOtp(map)
    }

    suspend fun resetPassword(email: String, newPassword: String): Response<ResponseBody> {
        val request = hashMapOf(
            "email" to email,
            "password" to newPassword,
            "password_confirmation" to newPassword
        )
        return api.resetPassword(request)
    }

    suspend fun changePassword(token: String, currentPassword: String, newPassword: String): Response<ResponseBody> {
        val request = hashMapOf(
            "current_password" to currentPassword,
            "new_password" to newPassword,
            "new_password_confirmation" to newPassword
        )
        val authToken = "Bearer $token"
        Log.d("ProfileRepository", "Auth Token: $authToken")

        return api.changePassword(authToken, request)
    }

    fun putToken(token: String) {
        val sharedPref = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString("AUTH_TOKEN", token)
            apply()
        }
    }

    fun getToken(): String? = sharedPref.getString("AUTH_TOKEN", null)

    fun deleteToken(){
        val sharedPref = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            remove("AUTH_TOKEN")
            apply()
        }
    }
}