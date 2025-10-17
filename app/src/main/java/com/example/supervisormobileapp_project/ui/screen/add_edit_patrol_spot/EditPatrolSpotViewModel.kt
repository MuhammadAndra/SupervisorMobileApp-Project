package com.example.supervisormobileapp_project.ui.screen.add_edit_patrol_spot

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.supervisormobileapp_project.data.model.PatrolSpot
import com.example.supervisormobileapp_project.data.model.fetchPatrolSpot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EditPatrolSpotViewModel: ViewModel() {
    private val _patrolSpot = MutableStateFlow<PatrolSpot?>(null)
    val patrolSpot: StateFlow<PatrolSpot?> = _patrolSpot

    fun getPatrolSpotById(id:Int){
        viewModelScope.launch(Dispatchers.IO) {
            _patrolSpot.value = fetchPatrolSpot(id = id)
        }
    }

}