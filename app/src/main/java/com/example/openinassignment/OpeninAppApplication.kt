package com.example.openinassignment

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class OpeninAppApplication : Application() {

    lateinit var prefs: SharedPreferences

    override fun onCreate() {
        super.onCreate()
        prefs = applicationContext.getSharedPreferences(
            "not_so_secure_prefs_name",
            Context.MODE_PRIVATE
        )
        prefs.edit().putString(
            "token",
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MjU5MjcsImlhdCI6MTY3NDU1MDQ1MH0.dCkW0ox8tbjJA2GgUx2UEwNlbTZ7Rr38PVFJevYcXFI"
        ).commit()
    }

}