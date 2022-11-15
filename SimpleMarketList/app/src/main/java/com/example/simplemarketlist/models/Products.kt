package com.example.simplemarketlist.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class Products(
    var prdId: String? = null,
    var prdName: String? = null,
    var prdPrice: String? = null
)