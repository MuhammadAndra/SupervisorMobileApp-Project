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