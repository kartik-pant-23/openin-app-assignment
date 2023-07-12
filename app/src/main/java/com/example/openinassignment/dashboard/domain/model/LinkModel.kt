package com.example.openinassignment.dashboard.domain.model

data class LinkModel(
    val appImage: String,
    val appName: String,
    val linkTitle: String,
    val createdAt: String,
    val webLink: String,
    val totalClicks: Int
)
