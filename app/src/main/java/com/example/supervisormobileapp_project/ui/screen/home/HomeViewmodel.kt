package com.example.supervisormobileapp_project.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.supervisormobileapp_project.data.model.Company
import com.example.supervisormobileapp_project.data.model.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.supervisormobileapp_project.data.model.fetchCompanies
import com.example.supervisormobileapp_project.data.repository.CompanyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewmodel @Inject constructor(
    private val repository: CompanyRepository
) : ViewModel() {
    private val _companies = MutableStateFlow<Resource<List<Company>>>(Resource.Loading())
    val companies = _companies

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
}