package com.example.doei.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.doei.domain.models.Product
import com.example.doei.domain.repository.FirebaseDatabaseRepository
import com.example.doei.domain.utils.StringUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.concurrent.fixedRateTimer

@HiltViewModel
class HomeViewModel @Inject constructor(private val firebaseDatabaseRepository: FirebaseDatabaseRepository) :
    ViewModel() {

    init {
        firebaseDatabaseRepository.handleProductList().observeForever {
            productList.value = it
        }
    }

    fun searchingProduct(query: String?) {
        val text = StringUtils.removeAccentsAndCaps(query)
        val list = mutableListOf<Product>()
        firebaseDatabaseRepository.productList.value?.forEach {
            if (!text.isNullOrBlank()) {
                it.takeIf { product ->
                    val name = StringUtils.removeAccentsAndCaps(product.name)
                    name?.contains(text) == true
                }?.let { it1 ->
                    list.add(it1)
                }
            } else {
                list.add(it)
            }
        }

        productList.value = list
    }

    private val productList = MutableLiveData<List<Product>>()
    fun handleProductList(): LiveData<List<Product>> = productList

    val handleErrorMessage: LiveData<String> = firebaseDatabaseRepository.handleErrorMessage()
}