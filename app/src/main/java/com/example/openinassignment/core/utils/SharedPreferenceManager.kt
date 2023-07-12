package com.example.openinassignment.core.utils

import android.content.SharedPreferences

class SharedPreferenceManager(private val pref: SharedPreferences) {

    var token: String?
        get() = pref.getString("token", null)
        set(value) {
            value?.let { pref.edit().putString("token", it).apply() }
        }

}