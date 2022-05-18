package com.example.doei.ui.product_register

import androidx.lifecycle.ViewModel
import com.example.doei.domain.models.Product
import com.example.doei.domain.repository.FirebaseDatabaseRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
@AndroidEntryPoint
class ProductRegisterViewModel @Inject constructor(private val firebaseDatabaseRepository: FirebaseDatabaseRepository)  : ViewModel() {
    fun addProductToDatabase(product : Product) : Boolean{
        return firebaseDatabaseRepository.addProductToDatabase(product)
    }
}