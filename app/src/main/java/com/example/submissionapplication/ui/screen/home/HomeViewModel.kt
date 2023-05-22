package com.example.submissionapplication.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.submissionapplication.data.CatalogRepository
import com.example.submissionapplication.model.LaptopList
import com.example.submissionapplication.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: CatalogRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<LaptopList>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<LaptopList>>>
        get() = _uiState

    fun getAllData() {
        viewModelScope.launch {
            repository.getAllData()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderRewards ->
                    _uiState.value = UiState.Success(orderRewards)
                }
        }
    }

}