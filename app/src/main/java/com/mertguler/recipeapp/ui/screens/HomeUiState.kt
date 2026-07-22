package com.mertguler.recipeapp.ui.screens

import com.mertguler.recipeapp.data.remote.dto.CategoryDto
import com.mertguler.recipeapp.data.remote.dto.MealDto

sealed interface HomeUiState {
    data object Loading : HomeUiState

    data class HomeSuccess (
        val meals: List<MealDto>,
        val categories: List<CategoryDto>
    ) : HomeUiState
    data class Error(
        val message: String
    ) : HomeUiState

}