package com.mertguler.recipeapp.data.remote.dto

data class MealResponse(
    val meals: List<MealDto>?
)

data class CategoryResponse(
    val categories: List<CategoryDto>?
)