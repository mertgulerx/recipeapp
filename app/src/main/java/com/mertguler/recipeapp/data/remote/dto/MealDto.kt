package com.mertguler.recipeapp.data.remote.dto

data class MealDto(
    val idMeal: String,
    val strMeal: String, // Tarif basligi
    val strMealThumb: String, // Gorsel URL
    val strCategory: String? // Kategori
)