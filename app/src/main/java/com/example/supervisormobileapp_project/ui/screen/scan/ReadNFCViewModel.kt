package com.example.supervisormobileapp_project.ui.screen.scan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.supervisormobileapp_project.data.model.CheckPatrolSpotResponse
import com.example.supervisormobileapp_project.data.model.PatrolSpot
import com.example.supervisormobileapp_project.data.model.ReadNFCResponse
import com.example.supervisormobileapp_project.data.model.Resource
import com.example.supervisormobileapp_project.data.model.fetchPatrolSpotByNfcUidResponse
import com.example.supervisormobileapp_project.data.repository.PatrolSpotRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReadNFCViewModel @Inject constructor(
    private val repository: PatrolSpotRepository
) : ViewModel() {
    private val _patrolSpot = MutableStateFlow<ReadNFCResponse?>(null)
    val patrolSpot: StateFlow<ReadNFCResponse?> = _patrolSpot

    private val _checkPatrolSpotResponse =
        MutableStateFlow<Resource<CheckPatrolSpotResponse>>(Resource.Idle())
    val checkPatrolSpotResponse = _checkPatrolSpotResponse


    fun getPatrolSpotByNfcUid(nfcUid:String){
        viewModelScope.launch(Dispatchers.IO) {
            _patrolSpot.value = fetchPatrolSpotByNfcUidResponse(nfcUid = nfcUid)
        }
    }
    fun clearPatrolSpot(){
        _checkPatrolSpotResponse.value = Resource.Idle()
        _patrolSpot.value = null
    }

    fun checkNfcFromApi(nfcTagUid:String){
        viewModelScope.launch(Dispatchers.IO) {
            _checkPatrolSpotResponse.value = Resource.Loading()
            _checkPatrolSpotResponse.value = repository.checkNfc(nfcTagUid = nfcTagUid)
        }
    }
}