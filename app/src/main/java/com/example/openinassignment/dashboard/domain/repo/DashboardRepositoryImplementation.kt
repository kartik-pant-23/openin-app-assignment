package com.example.openinassignment.dashboard.domain.repo

import android.util.Log
import com.example.openinassignment.core.utils.HandleApiCallUseCase
import com.example.openinassignment.core.utils.Resource
import com.example.openinassignment.dashboard.data.dao.DashboardApi
import com.example.openinassignment.dashboard.data.repo.DashboardRepository
import com.example.openinassignment.dashboard.domain.model.DashboardDataModel

class DashboardRepositoryImplementation(
    private val dashboardApi: DashboardApi,
    private val handleApiCall: HandleApiCallUseCase
) : DashboardRepository {

    override suspend fun getHomePageData(): Resource<DashboardDataModel> {
        return try {
            return when (val res = handleApiCall(apiFunction = { dashboardApi.getDashboardData() }, TAG = TAG)) {
                is Resource.Success -> Resource.Success(data = res.data!!.toDashboardDataModel())
                is Resource.Failure -> Resource.Failure(message = res.message)
                else -> Resource.LoggedOut()
            }
        } catch (e: Exception) {
            Log.d("DashboardRepository", "$e")
            Resource.Failure("Something went wrong.")
        }
    }

    companion object {
        const val TAG = "DashboardRepository"
    }

}