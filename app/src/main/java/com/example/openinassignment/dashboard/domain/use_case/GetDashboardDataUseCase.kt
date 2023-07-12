package com.example.openinassignment.dashboard.domain.use_case

import com.example.openinassignment.core.utils.Resource
import com.example.openinassignment.dashboard.data.repo.DashboardRepository
import com.example.openinassignment.dashboard.domain.model.DashboardDataModel

class GetDashboardDataUseCase(
    private val repo: DashboardRepository
) {

    suspend operator fun invoke(): Resource<DashboardDataModel> {
        return repo.getHomePageData()
    }

}