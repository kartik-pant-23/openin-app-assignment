package com.example.openinassignment.dashboard.domain.model

data class LinksPerformanceDataModel(
    val todayClicks: Int,
    val topLocation: String,
    val topSource: String,
    val totalClicks: Int,
    val totalLinks: Int
)
