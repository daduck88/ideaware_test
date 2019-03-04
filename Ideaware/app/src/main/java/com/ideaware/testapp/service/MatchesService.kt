package com.ideaware.testapp.service

import com.ideaware.testapp.data.Match
import io.reactivex.Observable
import retrofit2.http.GET

private const val API_FIXTURES_BASE_PATH = "cdn-og-test-api/test-task/fixtures.json"
private const val API_RESULTS_BASE_PATH = "cdn-og-test-api/test-task/results.json"

interface MatchesService {
    @GET(API_FIXTURES_BASE_PATH)
    fun getFixturesMatches(): Observable<List<Match>>

    @GET(API_RESULTS_BASE_PATH)
    fun getResultsMatches(): Observable<List<Match>>
}