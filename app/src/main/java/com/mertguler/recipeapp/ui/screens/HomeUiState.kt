package com.mertguler.recipeapp.ui.screens

import com.mertguler.recipeapp.data.remote.dto.MealDto

sealed interface HomeUiState {
    data object Loading : HomeUiState

    data class Success (
        val meals: List<MealDto>
    ) : HomeUiState

    data class Error(
        val message: String
    ) : HomeUiState

}