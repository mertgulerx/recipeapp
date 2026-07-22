package com.mertguler.recipeapp.ui.screens

import android.app.Application
import androidx.annotation.Nullable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertguler.recipeapp.data.local.RecipeDatabase
import com.mertguler.recipeapp.data.remote.dto.CategoryDto
import com.mertguler.recipeapp.data.remote.dto.MealDto
import com.mertguler.recipeapp.data.repository.MealRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.mertguler.recipeapp.data.local.preferences.AppPreferences

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
            loadHomeData()
    }

    fun loadHomeData() {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading

            try {
                val meals = repository.getDailyMeals(count = 4)
                val categories = repository.getCategories()

                _uiState.value =
                    if (meals.isEmpty()) {
                        HomeUiState.Error(
                            message = "Tarif Bulunamadi."
                        )
                    } else if (categories.isEmpty()){
                        HomeUiState.Error(
                            message = "Kategori Bulunamadi."
                        )
                    } else {
                        HomeUiState.HomeSuccess(meals = meals, categories = categories)
                    }
            } catch (exception: Exception) {
                _uiState.value = HomeUiState.Error(
                    message = exception.message ?: "Yukleme Hatasi."
                )
            }
        }
    }
}