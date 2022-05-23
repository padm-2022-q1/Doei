package com.example.doei.ui.product_register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.doei.domain.models.Product
import com.example.doei.domain.repository.FirebaseDatabaseRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductRegisterViewModel @Inject constructor(private val firebaseDatabaseRepository: FirebaseDatabaseRepository)  : ViewModel() {

    fun handleProductAdded(): LiveData<Boolean> = firebaseDatabaseRepository.handleAddProduct()

    fun addProductToDatabase(product : Product) {
        firebaseDatabaseRepository.addProductToDatabase(product)
    }
}