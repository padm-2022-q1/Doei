package com.example.doei.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.doei.domain.models.Product
import kotlinx.coroutines.flow.MutableStateFlow

class HomeViewModel : ViewModel() {
    //TODO apagar mock
    private val list = listOf<Product>(
        Product("", "Geladeira","10km", "Geladeira, não possui frostfree, com pequenos amaçados"),
        Product("", "Sofa Antigo","14km", "Sofa bem confortavel")
    )

    val productList: LiveData<List<Product>> = MutableStateFlow(list).asLiveData()
}