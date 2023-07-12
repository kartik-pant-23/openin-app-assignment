package com.example.openinassignment.dashboard.data.model


import com.example.openinassignment.dashboard.domain.model.DashboardDataModel
import com.example.openinassignment.dashboard.domain.model.LinksPerformanceDataModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DashboardDataDto(
    @Json(name = "applied_campaign")
    val appliedCampaign: Int,
    @Json(name = "data")
    val linksData: LinksDataDto,
    @Json(name = "extra_income")
    val extraIncome: Double,
    @Json(name = "links_created_today")
    val linksCreatedToday: Int,
    @Json(name = "startTime")
    val startTime: String,
    @Json(name = "support_whatsapp_number")
    val supportWhatsappNumber: String,
    @Json(name = "today_clicks")
    val todayClicks: Int,
    @Json(name = "top_location")
    val topLocation: String,
    @Json(name = "top_source")
    val topSource: String,
    @Json(name = "total_clicks")
    val totalClicks: Int,
    @Json(name = "total_links")
    val totalLinks: Int
) {

    fun toDashboardDataModel(): DashboardDataModel {
        return DashboardDataModel(
            chartData = linksData.overallUrlChart,
            linksPerformanceData = LinksPerformanceDataModel(
                todayClicks = todayClicks,
                topLocation = topLocation,
                topSource = topSource,
                totalClicks = totalClicks,
                totalLinks = totalLinks
            ),
            linksData = linksData.toLinksDataModel()
        )
    }

}