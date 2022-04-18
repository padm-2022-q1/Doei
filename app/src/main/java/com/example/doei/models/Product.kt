package com.example.doei.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val image: String,
    val name: String,
    val distance: String,
    val description: String
): Parcelable {
}