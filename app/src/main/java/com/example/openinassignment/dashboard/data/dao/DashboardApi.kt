package com.example.openinassignment.dashboard.data.dao

import com.example.openinassignment.dashboard.data.model.DashboardDataDto
import retrofit2.Response
import retrofit2.http.GET

interface DashboardApi {

    @GET("dashboardNew")
    suspend fun getDashboardData(): Response<DashboardDataDto>

}