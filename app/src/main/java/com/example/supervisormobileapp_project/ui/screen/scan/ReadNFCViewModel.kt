package com.example.supervisormobileapp_project.ui.screen.scan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.supervisormobileapp_project.data.model.PatrolSpot
import com.example.supervisormobileapp_project.data.model.ReadNFCResponse
import com.example.supervisormobileapp_project.data.model.fetchPatrolSpotByNfcUidResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ReadNFCViewModel: ViewModel() {
    private val _patrolSpot = MutableStateFlow<ReadNFCResponse?>(null)
    val patrolSpot: StateFlow<ReadNFCResponse?> = _patrolSpot

    fun getPatrolSpotByNfcUid(nfcUid:String){
        viewModelScope.launch(Dispatchers.IO) {
            _patrolSpot.value = fetchPatrolSpotByNfcUidResponse(nfcUid = nfcUid)
        }
    }
    fun clearPatrolSpot(){
        _patrolSpot.value = null
    }
}