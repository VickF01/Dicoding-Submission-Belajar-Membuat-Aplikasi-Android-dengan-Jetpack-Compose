package com.example.submissionapplication.ui.screen.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.submissionapplication.di.Injection
import com.example.submissionapplication.ui.common.UiState
import com.example.submissionapplication.ui.screen.ViewModelFactory
import com.example.submissionapplication.ui.theme.SubmissionApplicationTheme

@Composable
fun DetailScreen(
    id: Long,
    viewModel: DetailViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateBack: () -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { 
        when(it) {
            is UiState.Loading -> {
                viewModel.getById(id)
            }
            is UiState.Success -> {
                val data = it.data
                DetailLaptop(
                    laptop_name = data.laptop.laptop_name,
                    laptop_highlights = data.laptop.laptop_highlights,
                    laptop_cpu = data.laptop.laptop_cpu,
                    laptop_os = data.laptop.laptop_os,
                    laptop_ram = data.laptop.laptop_ram,
                    laptop_storage = data.laptop.laptop_storage,
                    laptop_screen = data.laptop.laptop_screen,
                    laptop_gpu = data.laptop.laptop_gpu,
                    laptop_features = data.laptop.laptop_features,
                    laptop_price = data.laptop.laptop_price,
                    laptop_picture = data.laptop.laptop_picture,
                    onBackClick = navigateBack)
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun DetailLaptop(
    laptop_name: String,
    laptop_highlights: String,
    laptop_cpu: String,
    laptop_os: String,
    laptop_ram: String,
    laptop_storage: String,
    laptop_screen: String,
    laptop_gpu: String,
    laptop_features: String,
    laptop_price: Int,
    laptop_picture: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { onBackClick() }
            )
            AsyncImage(model = laptop_picture, contentDescription = "laptop_image", contentScale = ContentScale.Crop)
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = laptop_name,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = laptop_highlights,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Specifications",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "CPU : ${laptop_cpu}")
            Text(text = "OS : ${laptop_os}")
            Text(text = "RAM : ${laptop_ram}")
            Text(text = "STORAGE : ${laptop_storage}")
            Text(text = "SCREEN : ${laptop_screen}")
            Text(text = "GPU : ${laptop_gpu}")
            Text(text = "FEATURES : ${laptop_features}")
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Price: Rp ${laptop_price}",
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailLaptopPreview() {
    SubmissionApplicationTheme {
        DetailLaptop(
            laptop_name = "laptop name",
            laptop_highlights = "laptop highlights",
            laptop_cpu = "laptop cpu",
            laptop_os = "laptop os",
            laptop_ram = "laptop ram",
            laptop_storage = "laptop storage",
            laptop_screen = "laptop screen",
            laptop_gpu = "laptop gpu",
            laptop_features = "laptop features",
            laptop_price = 1234567,
            laptop_picture = "laptop picture",
            onBackClick = {}
        )
    }
}