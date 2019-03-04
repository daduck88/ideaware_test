package com.ideaware.testapp.data

import com.google.gson.annotations.SerializedName

data class CompetitionStage(
    @SerializedName("competition")
    val competition: Competition
)