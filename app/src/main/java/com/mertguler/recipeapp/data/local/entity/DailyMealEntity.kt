package com.mertguler.recipeapp.data.local.entity

import androidx.room.Entity

@Entity(
    tableName = "daily_meals",
    primaryKeys = ["idMeal"]
)
data class DailyMealEntity(
    val idMeal: String,
    val title: String,
    val imageUrl: String?,
    val category: String?,
)

@Entity(
    tableName = "categories",
    primaryKeys = ["idCategory"]
)
data class CategoryEntity(
    val idCategory: String,
    val title: String,
    val imageUrl: String?
)