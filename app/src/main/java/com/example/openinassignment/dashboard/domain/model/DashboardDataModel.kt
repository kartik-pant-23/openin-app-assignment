package com.example.openinassignment.dashboard.domain.model

data class DashboardDataModel(
    val chartData: Map<String, Int>,
    val linksPerformanceData: LinksPerformanceDataModel,
    val linksData: LinksDataModel
) {

    val greetings: String
        get() {
            // TODO - can have a logic here to give greeting according to time
            return "Good Morning"
        }

}
