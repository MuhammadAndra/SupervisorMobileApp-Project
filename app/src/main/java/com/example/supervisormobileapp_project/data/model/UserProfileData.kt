package com.example.supervisormobileapp_project.data.model

data class UserProfileData(
    val id: Int,
    val role_id: Int,
    val name: String,
    val username: String,
    val email: String,
    val email_verified_at: String?,
    val photo: String,
    val created_at: String,
    val updated_at: String
)
