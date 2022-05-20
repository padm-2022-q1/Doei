package com.example.doei.domain.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Account(
    @SerializedName("photo")
    var imageUrl: String = "",
    @SerializedName("name")
    var name: String = "",
    @SerializedName("local")
    var local: String = "",
    @SerializedName("description")
    var age: String = "",
    @SerializedName("id")
    var userId: Long = -1
): Parcelable {
    companion object {
        const val NAME = "name"
        const val USERID = "id"
        const val LOCAL = "local"
        const val IMAGE_URL = "photo"
        const val DESCRIPTION = "description"
    }
}