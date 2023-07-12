package com.example.openinassignment.dashboard.data.model


import com.example.openinassignment.dashboard.domain.model.LinksDataModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LinksDataDto(
    @Json(name = "overall_url_chart")
    val overallUrlChart: Map<String, Int>,
    @Json(name = "recent_links")
    val recentLinks: List<LinkDto>,
    @Json(name = "top_links")
    val topLinks: List<LinkDto>
) {

    fun toLinksDataModel(): LinksDataModel {
        return LinksDataModel(
            topLinks = topLinks.map { it.toLinkModel() },
            recentLinks = recentLinks.map { it.toLinkModel() }
        )
    }

}