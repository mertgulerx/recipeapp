package com.mertguler.recipeapp.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute : NavKey

@Serializable
data class MealDetailRoute(
    val mealId: String
) : NavKey