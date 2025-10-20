package com.example.supervisormobileapp_project.data.model


data class ReadNFCResponse(
    val status: String,
    val message: String,
    val data: PatrolSpot?
)
