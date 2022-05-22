package com.example.doei.domain.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    @SerializedName("photo")
    var photo: String = "",
    @SerializedName("name")
    var name: String = "",
    @SerializedName("local")
    var local: String = "",
    @SerializedName("description")
    var description: String = "",
    @SerializedName("id")
    var id: Long = -1,
    @SerializedName("category")
    var category: String = "",
    @SerializedName("phone")
    var phone: String = ""
): Parcelable {
    companion object {
        const val NAME = "name"
        const val USERID = "id"
        const val LOCAL = "local"
        const val IMAGE_URL = "photo"
        const val DESCRIPTION = "description"
    }
}