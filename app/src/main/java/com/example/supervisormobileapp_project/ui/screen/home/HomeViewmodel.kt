package com.example.supervisormobileapp_project.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.supervisormobileapp_project.data.model.Company
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.supervisormobileapp_project.data.model.fetchCompanies

class HomeViewmodel : ViewModel() {
    private val _companies = MutableStateFlow<List<Company>>(emptyList())
    val companies: StateFlow<List<Company>> = _companies

    fun getCompanies(){
        viewModelScope.launch(Dispatchers.IO) {
            _companies.value = fetchCompanies()
        }
    }
}