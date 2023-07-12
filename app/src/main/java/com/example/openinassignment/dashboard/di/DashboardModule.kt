package com.example.openinassignment.dashboard.di

import com.example.openinassignment.core.utils.HandleApiCallUseCase
import com.example.openinassignment.dashboard.data.dao.DashboardApi
import com.example.openinassignment.dashboard.domain.repo.DashboardRepositoryImplementation
import com.example.openinassignment.dashboard.domain.use_case.DashboardUseCases
import com.example.openinassignment.dashboard.domain.use_case.GetDashboardDataUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class DashboardModule {

    @Provides
    fun providesDashboardApi(retrofit: Retrofit): DashboardApi {
        return retrofit.create(DashboardApi::class.java)
    }

    @Provides
    fun providesUseCases(api: DashboardApi): DashboardUseCases {
        val repo = DashboardRepositoryImplementation(
            dashboardApi = api,
            handleApiCall = HandleApiCallUseCase()
        )
        return DashboardUseCases(
            getDashboardPageData = GetDashboardDataUseCase(repo = repo)
        )
    }

}