package com.example.submissionapplication.di

import com.example.submissionapplication.data.CatalogRepository

object Injection {
    fun provideRepository(): CatalogRepository {
        return CatalogRepository.getInstance()
    }
}