package com.ideaware.testapp.data

import com.google.gson.annotations.SerializedName

data class Competition(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)