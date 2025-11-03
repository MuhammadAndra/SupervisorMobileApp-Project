package com.example.supervisormobileapp_project.ui.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.supervisormobileapp_project.data.model.Supervisor
import com.example.supervisormobileapp_project.data.model.changeSupervisorData
import com.example.supervisormobileapp_project.data.model.fetchSupervisorData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(): ViewModel() {
    private val _supervisor = MutableStateFlow<Supervisor?>(null)
    val supervisor: StateFlow<Supervisor?> = _supervisor

    fun getSupervisor(){
        viewModelScope.launch(Dispatchers.IO) {
            _supervisor.value = fetchSupervisorData()
        }
    }
    fun editSupervisorData(supervisor: Supervisor){
        viewModelScope.launch(Dispatchers.IO) {
            changeSupervisorData(supervisor = supervisor)
            getSupervisor()
        }
    }
}