package com.mertguler.recipeapp.ui.screens

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertguler.recipeapp.data.local.RecipeDatabase
import com.mertguler.recipeapp.data.repository.MealRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository = MealRepository(
        dailyMealDao = RecipeDatabase
            .getInstance(application)
            .dailyMealDao()
    )

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)

    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadDailyMeals()
    }
    fun loadDailyMeals() {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading

            try {
                val meals = repository.getDailyMeals(count = 4)

                _uiState.value =
                    if (meals.isEmpty()) {
                        HomeUiState.Error(
                            message = "Tarif Bulunamadi."
                        )
                    } else {
                        HomeUiState.Success(meals = meals)
                    }
            } catch (exception: Exception) {
                _uiState.value = HomeUiState.Error(
                    message = exception.message ?: "Tarifler yuklenemedi."
                )
            }
        }
    }
}