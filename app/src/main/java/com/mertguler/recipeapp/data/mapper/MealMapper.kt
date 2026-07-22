package com.mertguler.recipeapp.data.mapper

import com.mertguler.recipeapp.data.local.entity.CategoryEntity
import com.mertguler.recipeapp.data.local.entity.DailyMealEntity
import com.mertguler.recipeapp.data.remote.dto.CategoryDto
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

fun CategoryDto.toCategoryEntity(): CategoryEntity{
    return CategoryEntity(
        idCategory = idCategory,
        title = strCategory,
        imageUrl = strCategoryThumb
    )
}

fun CategoryEntity.toCategoryDto(): CategoryDto{
    return CategoryDto(
        idCategory = idCategory,
        strCategory = title,
        strCategoryThumb = imageUrl
    )
}

