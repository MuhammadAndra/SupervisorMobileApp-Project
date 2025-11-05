package com.example.supervisormobileapp_project.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.supervisormobileapp_project.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {
    private val _loginMessage = MutableStateFlow<String>("")
    val loginMessage: StateFlow<String> get() = _loginMessage

    private val _verifyOtpMessage = MutableStateFlow<String>("")
    val verifyOtpMessage: StateFlow<String> get() = _verifyOtpMessage

    private val _requestOtpMessage = MutableStateFlow<String>("")
    val requestOtpMessage: StateFlow<String> get() = _requestOtpMessage

    private val _resetPasswordMessage = MutableStateFlow<String>("")
    val resetPasswordMessage: StateFlow<String> get() = _resetPasswordMessage

    private val _isOtpVerified = MutableStateFlow<Boolean>(false)
    val isOtpVerified: StateFlow<Boolean> get() = _isOtpVerified

    private val _isSuccess = MutableStateFlow<Boolean>(false)
    val isSuccess: StateFlow<Boolean> get() = _isSuccess

    private val _changePasswordMessage = MutableStateFlow<String>("")
    val changePasswordMessage: StateFlow<String> get() = _changePasswordMessage

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = repository.login(email, password)
                if (response.isSuccessful) {
                    val responseBody =
                        response.body()?.string() ?: "Empty response"
                    Log.d("login", responseBody)

                    // Simpan token ke SharedPreferences
                    val token = extractToken(responseBody)
                    if (token != null) {
                        saveToken(token)
                        _loginMessage.value = "Login Berhasil"
                    } else {
                        _loginMessage.value =
                            "Login berhasil, tetapi token tidak ditemukan"
                    }
                } else {
                    val errorBody =
                        response.errorBody()?.string() ?: "Empty response"
                    Log.e("login", errorBody)
                    _loginMessage.value = "Login gagal"
                }
            } catch (e: Exception) {
                Log.e("login", "Error: ${e.message}")
                _loginMessage.value = "Error: ${e.message}"
            }
        }
    }

    private fun extractToken(responseBody: String): String? {
        return try {
            val json = JSONObject(responseBody)
            json.getJSONObject("data").optString("token", null)
        } catch (e: Exception) {
            Log.e("AuthViewModel", "Error parsing token: ${e.message}")
            null
        }
    }

    private fun saveToken(token: String) {
        repository.putToken(token)
    }

    fun requestOtp(email: String) {
        viewModelScope.launch {
            try {
                val response = repository.requestOtp(email)
                if (response.isSuccessful) {
                    val responseBody =
                        response.body()?.string() ?: "Empty response"
                    Log.d("ForgotPassword", "Response: $responseBody")
                    _requestOtpMessage.value =
                        "Link telah dikirim. Silakan cek email anda! (${System.currentTimeMillis()})"
                } else {
                    val errorBody =
                        response.errorBody()?.string() ?: "Unknown error"
                    Log.e(
                        "ForgotPassword",
                        "Failed: ${response.code()}, Error: $errorBody"
                    )
                    _requestOtpMessage.value = "Link gagal terkirim (${System.currentTimeMillis()})"
                }
            } catch (e: Exception) {
                Log.e("ForgotPassword", "Error: ${e.message}")
                _requestOtpMessage.value = "Error: ${e.message}"

            }
        }
    }

    fun verifyOtp(email: String, otp: String) {
        viewModelScope.launch {
            try {
                val response = repository.verifyOtp(email, otp)
                if (response.isSuccessful) {
                    val responseBody =
                        response.body()?.string() ?: "Empty response"
                    Log.d("VerifyOtp", "Response: $responseBody")
                    _verifyOtpMessage.value = "Verifikasi berhasil!"
                    _isOtpVerified.value = true
                } else {
                    val errorBody =
                        response.errorBody()?.string() ?: "Unknown error"
                    Log.e(
                        "ForgotPassword",
                        "Failed: ${response.code()}, Error: $errorBody"
                    )
                    _verifyOtpMessage.value = "Verifikasi gagal!"
                }
            } catch (e: Exception) {
                Log.e("ForgotPassword", "Error: ${e.message}")
                _verifyOtpMessage.value = "Error: ${e.message}"
            }
        }
    }

    fun resetPassword(email: String, newPassword: String) {
        _isSuccess.value = false
        viewModelScope.launch {
            try {
                val response = repository.resetPassword(email, newPassword)
                if (response.isSuccessful) {
                    val responseBody =
                        response.body()?.string() ?: "Empty response"
                    Log.d("ResetPassword", "Response: $responseBody")
                    _resetPasswordMessage.value =
                        ("Password berhasil diperbarui!")
                    _isSuccess.value = true
                } else {
                    val errorBody =
                        response.errorBody()?.string() ?: "Unknown error"
                    try {
                        val json = JSONObject(errorBody)
                        val message = json.getString("message")
                        Log.e("ResetPassword", "Error Message: $message")
                        _resetPasswordMessage.value = "Gagal: $message (${System.currentTimeMillis()})"
                    } catch (e: Exception) {
                        Log.e("ResetPassword", "JSON parse error: ${e.message}")
                        _resetPasswordMessage.value = "Gagal parse error: ${e.message}"
                    }
//                    Log.e(
//                        "ResetPassword",
//                        "Failed: ${response.code()}, Error: $errorBody"
//                    )
//                    _resetPasswordMessage.value = ("Gagal: $errorBody ${System.currentTimeMillis()}")
                }
            } catch (e: Exception) {
                Log.e("ResetPassword", "Exception: ${e.message}")
                _resetPasswordMessage.value = "Error: ${e.message}"
            }
        }
    }

    fun changePassword(
        token: String,
        currentPassword: String,
        newPassword: String
    ) {
        _isSuccess.value = false

        val authToken = "Bearer $token"
        Log.d("ProfileViewModel", "Auth Token: $authToken")

        viewModelScope.launch {
            try {
                val response = repository.changePassword(
                    authToken,
                    currentPassword,
                    newPassword
                )
                if (response.isSuccessful) {
                    val responseBody =
                        response.body()?.string() ?: "Empty response"
                    Log.d("change password", "Response: $responseBody")
                    _changePasswordMessage.value =
                        "Password berhasil diperbarui!"
                    _isSuccess.value = true
                } else {

                    val errorBody =
                        response.errorBody()?.string() ?: "Unknown error"

                    try {
                        val json = JSONObject(errorBody)
                        val message = json.getString("message")
                        Log.e("ResetPassword", "Error Message: $message")
                        _changePasswordMessage.value = "Gagal: $message (${System.currentTimeMillis()})"
                    } catch (e: Exception) {
                        Log.e("ResetPassword", "JSON parse error: ${e.message}")
                        _changePasswordMessage.value = "Gagal parse error: ${e.message}"
                    }

//                    Log.e(
//                        "change password",
//                        "Failed: ${response.code()}, Error: $errorBody"
//                    )
//                    _changePasswordMessage.value = "Gagal: $errorBody ${System.currentTimeMillis()}"
//                    _changePasswordMessage.value = "Gagal: Password lama salah atau tidak valid."

                }
            } catch (e: Exception) {
                Log.e("change Password", "Exception: ${e.message}")
                _changePasswordMessage.value = "Error: ${e.message}"
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            try {
                // Hapus token dari SharedPreferences
                repository.deleteToken()
                Log.d("logout", "Logout successful")
            } catch (e: Exception) {
                Log.e("logout", "Error: ${e.message}")
            }
        }
    }

    fun getToken(): String? {
        return repository.getToken()
    }
}