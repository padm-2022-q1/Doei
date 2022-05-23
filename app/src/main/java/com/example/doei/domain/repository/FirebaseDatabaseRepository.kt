package com.example.doei.domain.repository

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.doei.domain.constants.Constants
import com.example.doei.domain.models.Product
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageTask
import com.google.firebase.storage.UploadTask
import com.google.firebase.storage.ktx.storage
import javax.inject.Inject

class FirebaseDatabaseRepository @Inject constructor() {

    private val database = Firebase.database(Constants.DATABASE)

    private val productAdded = MutableLiveData<Boolean>()
    fun handleAddProduct(): LiveData<Boolean> = productAdded

    val productList = getProductListFromDatabase()
    fun handleProductList(): LiveData<List<Product>> = productList

    private val errorMessage = MutableLiveData<String>()
    fun handleErrorMessage():LiveData<String> = errorMessage

    private fun getProductListFromDatabase(): MutableLiveData<List<Product>> {
        val products = MutableLiveData<List<Product>>()
        val reference = database.getReference("productList")
        val postListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.value
                if (value != null) {
                    if (value is ArrayList<*>)
                        products.value = transformValueIntoProductList(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                errorMessage.value = error.message
            }

        }
        reference.addValueEventListener(postListener)
        return products
    }

    private fun transformValueIntoProductList(value: ArrayList<*>): List<Product> {

        val productList = arrayListOf<Product>()
        value.forEach { map ->
            if (map is HashMap<*, *>) {
                val product = Product()
                map.forEach {
                    if (it.key is String && it.value is String) {
                        when (it.key) {
                            Product.NAME -> product.name = it.value as String
                            Product.USERID -> product.id = (it.value as String).toLong()
                            Product.LOCAL -> product.local = it.value as String
                            Product.DESCRIPTION -> product.description = it.value as String
                            Product.IMAGE_URL -> product.photo = it.value as String
                            Product.PHONE -> product.phone = it.value as String
                            Product.CATEGORY -> product.category = it.value as String
                        }
                    }
                }
                productList.add(product)
            }

        }
        return productList
    }

    fun addProductToDatabase(jsonProduct: Product){
        try {
            saveImageInStorageAndReturnPhotoUrl(jsonProduct.photo).addOnCompleteListener {
                if (it.isSuccessful) {
                    jsonProduct.photo = it.result.toString()
                    if (jsonProduct.photo.isNotBlank()) {
                        database.getReference("productList").get().addOnSuccessListener { snapshot ->
                            val idFirebase = snapshot.childrenCount
                            database.getReference("productList").child("${idFirebase + 1}")
                                .setValue(jsonProduct)
                            productAdded.postValue(true)
                        }
                    }
                } else {
                    productAdded.postValue(false)
                }
            }
        } catch (e: Exception) {
            productAdded.postValue(false)
        }
    }

    private fun saveImageInStorageAndReturnPhotoUrl(photo: String): Task<Uri> {
        val uri = Uri.parse(photo)
        val storageReference = Firebase.storage.reference
        val imageRef =
            storageReference.child("images/${uri.lastPathSegment}")
        val task = imageRef.putFile(uri).continueWithTask { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {
                        throw it
                    }
                }
                    imageRef.downloadUrl
            }
        return task
    }

    //*private fun getProductListLastId() : String{
     //   var productList = getProductList().observe();
     //   return (productList.value?.size?.minus(1)).toString()
    //}

    private fun getLastIdDonation() : String{
        val reference = database.getReference("productList")
        var productList : List<Product> = emptyList()
        val postListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.value
                if (value != null) {
                    if (value is ArrayList<*>)
                        productList = transformValueIntoProductList(value)
                }
            }

                override fun onCancelled(error: DatabaseError) {
                    errorMessage.value = error.message
                }

            }
            reference.addValueEventListener(postListener)
            return productList.size.toString()
        }


}


