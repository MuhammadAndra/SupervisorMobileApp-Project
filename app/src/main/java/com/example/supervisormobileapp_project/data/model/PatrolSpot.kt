package com.example.supervisormobileapp_project.data.model

data class PatrolSpot(
    val companyId: Int? = 0,
    val id: Int,
    val title: String,
    val address: String,
    val latitude: String,
    val longitude:String,
    val description:String?,
    var uidNfcTag: String?
)
