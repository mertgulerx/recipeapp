package com.mertguler.recipeapp.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitInstance { // Object = Singleton
    private const val BASE_URL =
        "https://www.themealdb.com/api/json/v1/1/"

    private val retrofit: Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val apiService: MealApiService =
        retrofit.create(MealApiService::class.java)
}