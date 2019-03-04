package com.ideaware.testapp.data

import com.google.gson.annotations.SerializedName

data class Score(
    @SerializedName("away")
    val away: Int,
    @SerializedName("home")
    val home: Int,
    @SerializedName("winner")
    val winner: String?
)