package com.example.openinassignment.core

import android.app.Application
import com.example.openinassignment.OpeninAppApplication
import com.example.openinassignment.core.utils.SharedPreferenceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Headers.Companion.toHeaders
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun providesSharedPreferencesManager(context: Application): SharedPreferenceManager {
        return SharedPreferenceManager(pref = (context as OpeninAppApplication).prefs)
    }

    @Provides
    fun providesRetrofit(prefManager: SharedPreferenceManager, context: Application): Retrofit {
        val token = prefManager.token
        return Retrofit.Builder()
            .baseUrl("https://api.inopenapp.com/api/v1/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor { chain ->
                val request =
                    chain.request()
                        .newBuilder()
                        .headers(mapOf("Authorization" to "Bearer $token").toHeaders())
                        .build()
                chain.proceed(request)
            }.build())
            .build()
    }

}