package com.mertguler.recipeapp.data.remote

import com.mertguler.recipeapp.data.remote.dto.CategoryResponse
import com.mertguler.recipeapp.data.remote.dto.MealResponse
import retrofit2.http.GET

interface MealApiService {
    @GET("random.php")
    suspend fun getRandomMeal(): MealResponse

    @GET(value = "categories.php")
    suspend fun getCategories(): CategoryResponse
}