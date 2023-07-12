package com.example.openinassignment.dashboard.ui

import com.example.openinassignment.dashboard.domain.model.DashboardDataModel

data class DashboardUiState(
    val dashboardData: DashboardDataModel?,
    val selectedTab: String,
    val loading: Boolean,
    val message: String?
) {

    companion object {
        const val TAB_TOP_LINKS = "top_links"
        const val TAB_RECENT_LINKS = "recent_links"
    }

}