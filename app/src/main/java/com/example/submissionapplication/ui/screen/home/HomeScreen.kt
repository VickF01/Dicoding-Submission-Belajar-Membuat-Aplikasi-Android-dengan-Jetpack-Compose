package com.example.submissionapplication.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.submissionapplication.di.Injection
import com.example.submissionapplication.model.LaptopList
import com.example.submissionapplication.ui.common.UiState
import com.example.submissionapplication.ui.components.ListCatalog
import com.example.submissionapplication.ui.screen.ViewModelFactory

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = ViewModelFactory(Injection.provideRepository())),
    navigateToDetail: (Long) -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let {
        when(it) {
            is UiState.Loading -> {
                viewModel.getAllData()
            }
            is UiState.Success -> {
                HomeListData(laptopList = it.data, navigateToDetail = navigateToDetail)
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun HomeListData(
    laptopList: List<LaptopList>,
    navigateToDetail: (Long) -> Unit
) {
    Box(modifier = Modifier) {
        LazyColumn {
            items(laptopList) {
                ListCatalog(name = it.laptop.laptop_name, highlights = it.laptop.laptop_highlights, image = it.laptop.laptop_picture, modifier = Modifier.clickable {
                    navigateToDetail(it.laptop.id)
                })
            }
        }
    }

}