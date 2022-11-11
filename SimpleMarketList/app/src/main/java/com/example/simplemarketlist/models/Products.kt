package com.example.simplemarketlist.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Products(
    var strId: String = "0",
    var strName: String? = null,
    var strBrand: String? = null,
    var intPrice: Int? = 0
) : Parcelable