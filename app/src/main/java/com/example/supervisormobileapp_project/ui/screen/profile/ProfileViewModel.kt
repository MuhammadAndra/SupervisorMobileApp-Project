package com.example.supervisormobileapp_project.ui.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.supervisormobileapp_project.data.model.EmployeeResponse
import com.example.supervisormobileapp_project.data.model.Supervisor
import com.example.supervisormobileapp_project.data.model.UserProfileResponse
import com.example.supervisormobileapp_project.data.model.changeSupervisorData
import com.example.supervisormobileapp_project.data.model.fetchSupervisorData
import com.example.supervisormobileapp_project.data.repository.CompanyRepository
import com.example.supervisormobileapp_project.data.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
): ViewModel() {
    private val _supervisor = MutableStateFlow<Supervisor?>(null)
    val supervisor: StateFlow<Supervisor?> = _supervisor

    private val _userProfile = MutableStateFlow<UserProfileResponse?>(null)
    val userProfile: StateFlow<UserProfileResponse?> = _userProfile

    private val _employeeProfile = MutableStateFlow<EmployeeResponse?>(null)
    val employeeProfile: StateFlow<EmployeeResponse?> = _employeeProfile


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
    fun getUserProfile(){
        viewModelScope.launch(Dispatchers.IO) {
            _userProfile.value = profileRepository.getUserProfile()
        }
    }

    fun getEmployeeProfile(){
        viewModelScope.launch {
            _employeeProfile.value = profileRepository.getEmployeeProfile()
        }
    }
}