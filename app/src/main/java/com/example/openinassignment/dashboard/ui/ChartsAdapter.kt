package com.example.openinassignment.dashboard.ui

import com.robinhood.spark.SparkAdapter

class ChartsAdapter(private val itemsList: Map<String, Int>): SparkAdapter() {

    override fun getCount(): Int {
        return itemsList.size
    }

    override fun getItem(index: Int): Any {
        return itemsList.entries.toList()[index]
    }

    override fun getY(index: Int): Float {
        return itemsList.entries.toList()[index].value.toFloat()
    }

}