package com.example.supervisormobileapp_project.data.model

data class EditPatrolSpotResponse(
    val status:String,
    val message:String,
    val data: EditData
)

data class EditData(
    val id: String
)
