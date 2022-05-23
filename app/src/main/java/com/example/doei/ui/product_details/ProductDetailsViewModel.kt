package com.example.doei.ui.product_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.doei.domain.models.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(handle: SavedStateHandle) : ViewModel() {
    val product = handle.getLiveData<Product>("product")

}