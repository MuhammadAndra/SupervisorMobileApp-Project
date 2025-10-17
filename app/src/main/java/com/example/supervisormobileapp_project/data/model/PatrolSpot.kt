package com.example.supervisormobileapp_project.data.model

data class PatrolSpot(
    val companyId: Int? = 0,
    val id: Int,
    val name: String,
    val address: String,
    val latitude: String,
    val longitude:String,
    val description:String,
    val uidNfcTag: String?
)
