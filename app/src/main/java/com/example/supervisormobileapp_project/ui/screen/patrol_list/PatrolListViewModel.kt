package com.example.supervisormobileapp_project.ui.screen.patrol_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.supervisormobileapp_project.data.model.PatrolSpot
import com.example.supervisormobileapp_project.data.model.fetchPatrolSpots
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PatrolListViewModel : ViewModel() {
    private val _patrolSpots = MutableStateFlow<List<PatrolSpot>>(emptyList())
    val patrolSpots: StateFlow<List<PatrolSpot>> = _patrolSpots

    fun getPatrolSpots(companyId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _patrolSpots.value = fetchPatrolSpots(companyId = companyId)
        }
    }
}