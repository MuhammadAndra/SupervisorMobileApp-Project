package com.example.supervisormobileapp_project.ui.screen.add_edit_patrol_spot

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.supervisormobileapp_project.data.model.CheckPatrolSpotResponse
import com.example.supervisormobileapp_project.data.model.EditPatrolSpotResponse
import com.example.supervisormobileapp_project.data.model.PatrolSpot
import com.example.supervisormobileapp_project.data.model.Resource
import com.example.supervisormobileapp_project.data.model.VerifyNfcResponse
import com.example.supervisormobileapp_project.data.model.editPatrolSpot
import com.example.supervisormobileapp_project.data.model.fetchPatrolSpotById
import com.example.supervisormobileapp_project.data.repository.PatrolSpotRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditPatrolSpotViewModel @Inject constructor(
    private val repository: PatrolSpotRepository
) : ViewModel() {
    private val _patrolSpot = MutableStateFlow<PatrolSpot?>(null)
    val patrolSpot: StateFlow<PatrolSpot?> = _patrolSpot

    private val _editPatrolSpotResponse =
        MutableStateFlow<Resource<EditPatrolSpotResponse>>(
            Resource.Idle()
        )
    val editPatrolSpotResponse = _editPatrolSpotResponse

    private val _verifyPatrolSpotResponse =
        MutableStateFlow<Resource<VerifyNfcResponse>>(Resource.Idle())
    val verifyPatrolSpotResponse = _verifyPatrolSpotResponse



    fun getPatrolSpotById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _patrolSpot.value = fetchPatrolSpotById(id = id)
        }
    }

    fun getPatrolSpotByIdFromApi(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _patrolSpot.value = repository.getPatrolSpot(patrolSpotId = id)
        }
    }

    fun changePatrolSpot(id: Int, newSpot: PatrolSpot) {
        viewModelScope.launch(Dispatchers.IO) {
            editPatrolSpot(id = id, newSpot = newSpot)
        }
    }

    fun editPatrolSpotFromApi(id: Int, editedPatrolSpot: PatrolSpot) {
        viewModelScope.launch(Dispatchers.IO) {
            _editPatrolSpotResponse.value = Resource.Loading()
            _editPatrolSpotResponse.value = repository.editPatrolSpot(
                patrolSpotId = id,
                editedPatrolSpot = editedPatrolSpot
            )
        }
    }

    fun verifyNfcFromApi(id: Int,verifyingNfcPatrolSpot: PatrolSpot){
        viewModelScope.launch(Dispatchers.IO) {
            _verifyPatrolSpotResponse.value = Resource.Loading()
            _verifyPatrolSpotResponse.value = repository.verifyNfc(
                patrolSpotId = id,
                verifyingPatrolSpot = verifyingNfcPatrolSpot
            )
        }
    }

}