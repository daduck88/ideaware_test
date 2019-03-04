package com.ideaware.testapp.data

import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import com.ideaware.testapp.ui.matches.MatchesAdapter
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@SuppressLint("NewApi")
data class Match(
    @SerializedName("awayTeam")
    val awayTeam: Team,
    @SerializedName("competitionStage")
    val competitionStage: CompetitionStage,
    @SerializedName("date")
    val date: String,
    @SerializedName("homeTeam")
    val homeTeam: Team,
    @SerializedName("id")
    val id: Int,
    @SerializedName("state")
    val state: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("venue")
    val venue: Venue,
    @SerializedName("score")
    val score: Score? = null
) {
    val localDate : LocalDateTime
        get() = LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME)
    fun getMonth(): Int {
        return localDate.monthValue
    }

    fun localDate(formatter: DateTimeFormatter?): CharSequence? {
        return localDate.format(formatter)
    }

    fun getMonthYear(): CharSequence? {
        return localDate.month.name.toLowerCase().capitalize() + " "+ localDate.year
    }
}