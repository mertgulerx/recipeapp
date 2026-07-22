package com.mertguler.recipeapp.data.mapper

import com.mertguler.recipeapp.data.local.entity.DailyMealEntity
import com.mertguler.recipeapp.data.remote.dto.MealDto

fun MealDto.toDailyMealEntity(
    dayKey: String,
    displayOrder: Int
): DailyMealEntity {
    return DailyMealEntity(
        idMeal = idMeal,
        title = strMeal,
        imageUrl = strMealThumb,
        category = strCategory,
        dayKey = dayKey,
        displayOrder = displayOrder
    )
}

fun DailyMealEntity.toMealDto(): MealDto {
    return MealDto(
        idMeal = idMeal,
        strMeal = title,
        strMealThumb = imageUrl,
        strCategory = category
    )
}