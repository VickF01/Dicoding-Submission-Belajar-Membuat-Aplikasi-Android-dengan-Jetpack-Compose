package com.example.submissionapplication.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.submissionapplication.data.CatalogRepository
import com.example.submissionapplication.model.LaptopList
import com.example.submissionapplication.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: CatalogRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<LaptopList>> = MutableStateFlow(UiState.Loading)

    val uiState: StateFlow<UiState<LaptopList>>
        get() = _uiState

    fun getById(phoneId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getById(phoneId))
        }
    }
}