package com.example.openinassignment.dashboard.data.repo

import com.example.openinassignment.core.utils.Resource
import com.example.openinassignment.dashboard.domain.model.DashboardDataModel

interface DashboardRepository {

    suspend fun getHomePageData(): Resource<DashboardDataModel>

}