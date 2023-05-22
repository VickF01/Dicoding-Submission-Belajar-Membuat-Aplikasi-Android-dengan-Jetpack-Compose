package com.example.submissionapplication.data

import com.example.submissionapplication.model.LaptopData
import com.example.submissionapplication.model.LaptopList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class CatalogRepository {

    private val laptop = mutableListOf<LaptopList>()

    init {
        if (laptop.isEmpty()) {
            LaptopData.laptop.forEach {
                laptop.add(LaptopList(it, 0))
            }
        }
    }

    fun getAllData(): Flow<List<LaptopList>> {
        return flowOf(laptop)
    }

    fun getById(id: Long): LaptopList {
        return laptop.first {
            it.laptop.id == id
        }
    }

    companion object {
        @Volatile
        private var instance: CatalogRepository? = null

        fun getInstance(): CatalogRepository =
            instance ?: synchronized(this) {
                CatalogRepository().apply {
                    instance = this
                }
            }
    }
}