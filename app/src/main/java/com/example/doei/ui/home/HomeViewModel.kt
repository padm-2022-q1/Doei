package com.example.doei.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.doei.domain.models.Product
import com.example.doei.domain.repository.FirebaseDatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(firebaseDatabaseRepository: FirebaseDatabaseRepository) : ViewModel() {

    val productList: LiveData<List<Product>> = firebaseDatabaseRepository.handleProductList()
    val handleErrorMessage: LiveData<String> = firebaseDatabaseRepository.handleErrorMessage()
}