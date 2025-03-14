package com.moon.beautygirlkotlin.gank.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.moon.beautygirlkotlin.gank.viewmodel.GankViewModel

class GankModelFactory(private val repository: GankRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GankViewModel(repository) as T
    }
}