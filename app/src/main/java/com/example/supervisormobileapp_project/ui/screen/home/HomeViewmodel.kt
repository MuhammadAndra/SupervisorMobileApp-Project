package com.example.supervisormobileapp_project.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.supervisormobileapp_project.data.model.Company
import com.example.supervisormobileapp_project.data.model.EmployeeResponse
import com.example.supervisormobileapp_project.data.model.PatrolSpot
import com.example.supervisormobileapp_project.data.model.Resource
import com.example.supervisormobileapp_project.data.model.UserProfileResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.supervisormobileapp_project.data.model.fetchCompanies
import com.example.supervisormobileapp_project.data.repository.CompanyRepository
import com.example.supervisormobileapp_project.data.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.Dispatcher
import javax.inject.Inject

@HiltViewModel
class HomeViewmodel @Inject constructor(
    private val repository: CompanyRepository,
    private val profileRepository: ProfileRepository
) : ViewModel() {
    private val _companies = MutableStateFlow<Resource<List<Company>>>(Resource.Loading())
    val companies = _companies

    private val _userProfile = MutableStateFlow<UserProfileResponse?>(null)
    val userProfile: StateFlow<UserProfileResponse?> = _userProfile

    private val _employeeProfile = MutableStateFlow<EmployeeResponse?>(null)
    val employeeProfile: StateFlow<EmployeeResponse?> = _employeeProfile

    fun getCompanies() {
        viewModelScope.launch(Dispatchers.IO) {
            //_companies.value = fetchCompanies()
        }
    }

    fun getCompaniesFromApi() {
        viewModelScope.launch(Dispatchers.IO) {
            _companies.value = Resource.Loading()
            _companies.value = repository.getCompanies()
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