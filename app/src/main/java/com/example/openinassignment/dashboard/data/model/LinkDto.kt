package com.example.openinassignment.dashboard.data.model


import com.example.openinassignment.core.utils.TimestampHelper
import com.example.openinassignment.dashboard.domain.model.LinkModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LinkDto(
    @Json(name = "app")
    val app: String,
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "domain_id")
    val domainId: String,
    @Json(name = "original_image")
    val originalImage: String,
    @Json(name = "smart_link")
    val smartLink: String,
    @Json(name = "thumbnail")
    val thumbnail: String?,
    @Json(name = "times_ago")
    val timesAgo: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "total_clicks")
    val totalClicks: Int,
    @Json(name = "url_id")
    val urlId: Int,
    @Json(name = "url_prefix")
    val urlPrefix: String?,
    @Json(name = "url_suffix")
    val urlSuffix: String,
    @Json(name = "web_link")
    val webLink: String
) {

    fun toLinkModel(): LinkModel {
        val convertedCreatedAt =
            TimestampHelper.getFormattedString(createdAt, TimestampHelper.DEFAULT)
        return LinkModel(
            appImage = originalImage,
            appName = app,
            linkTitle = title,
            createdAt = convertedCreatedAt,
            webLink = webLink,
            totalClicks = totalClicks
        )
    }

}