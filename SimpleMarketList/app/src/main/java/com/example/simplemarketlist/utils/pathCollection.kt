package com.example.simplemarketlist.utils

object Const {
    val PATH_COLLECTION = "products"
    val PATH_AGE = "intAge"

    fun setTimeStamp(): Long {
        val time = (-1 * System.currentTimeMillis())
        return time
    }
}