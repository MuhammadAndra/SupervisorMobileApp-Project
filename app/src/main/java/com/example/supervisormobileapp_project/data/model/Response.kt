package com.example.supervisormobileapp_project.data.model

data class EditPatrolSpotResponse(
    val status:String,
    val message:String,
    val data: ResponseData
)

data class VerifyNfcResponse(
    val status:String,
    val message:String,
    val data: ResponseData
)

data class ResponseData(
    val id: String
)

data class CheckPatrolSpotResponse(
    val status:String,
    val message:String,
    val data: PatrolSpot
)

data class EmployeeResponse(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: Employees,
    val exceptions: Any? = null
)

data class UserProfileResponse(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: UserProfileData
)