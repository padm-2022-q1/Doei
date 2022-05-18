package com.example.doei.domain.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.doei.domain.constants.Constants
import com.example.doei.domain.models.Product
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class FirebaseDatabaseRepository @Inject constructor() {

    private val database = Firebase.database(Constants.DATABASE)

    val productList = MutableLiveData<List<Product>>()
    fun handleProductList(): LiveData<List<Product>> = productList

    private val errorMessage = MutableLiveData<String>()
    fun handleErrorMessage():LiveData<String> = errorMessage

    init {
        val reference = database.getReference("productList")
        val postListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.value
                if (value != null) {
                    if (value is ArrayList<*>)
                    transformValueIntoProductList(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                errorMessage.value = error.message
            }

        }
        reference.addValueEventListener(postListener)
    }

    private fun transformValueIntoProductList(value: ArrayList<*>) {

        val productList = arrayListOf<Product>()
        value.forEach { map ->
            if (map is HashMap<*, *>) {
                val product = Product()
                map.forEach {
                    if (it.key is String && it.value is String) {
                        when (it.key) {
                            Product.NAME -> product.name = it.value as String
                            Product.USERID -> product.userId = (it.value as String).toLong()
                            Product.LOCAL -> product.local = it.value as String
                            Product.DESCRIPTION -> product.description = it.value as String
                            Product.IMAGE_URL -> product.imageUrl = it.value as String
                        }
                    }
                }
                productList.add(product)
            }

        }
        this.productList.value = productList
    }
}