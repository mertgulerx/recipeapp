package com.mertguler.recipeapp.data.repository

import com.mertguler.recipeapp.data.local.dao.DailyMealDao
import com.mertguler.recipeapp.data.mapper.toDailyMealEntity
import com.mertguler.recipeapp.data.mapper.toMealDto
import com.mertguler.recipeapp.data.remote.MealApiService
import com.mertguler.recipeapp.data.remote.RetrofitInstance
import com.mertguler.recipeapp.data.remote.dto.MealDto
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MealRepository(
    private val dailyMealDao: DailyMealDao,
    private val apiService: MealApiService =
        RetrofitInstance.apiService
) {
    suspend fun getRandomMeal(): MealDto? {
        val response = apiService.getRandomMeal()

        return response.meals?.firstOrNull()
    }

    suspend fun getRandomMeals(
        count: Int
    ): List<MealDto> {
        val meals = mutableListOf<MealDto>()

        repeat(count){
            getRandomMeal()?.let {
                meal -> meals.add(meal)
            }
        }

        return meals.distinctBy {
            meal -> meal.idMeal
        }
    }

    suspend fun getDailyMeals(
        count: Int
    ): List<MealDto> {
        val today = getTodayKey()
        val cachedMeals = dailyMealDao.getAllMails()

        if (cachedMeals.firstOrNull()?.dayKey == today){
            return cachedMeals.map {
                entity -> entity.toMealDto()
            }
        }

        val remoteMeals = getRandomMeals(4)

        if (remoteMeals.isEmpty()){
            if (cachedMeals.isNotEmpty()) {
                return cachedMeals.map { entity ->
                    entity.toMealDto() }
            }
            throw IllegalStateException("Tarifler Alinamadi")
        }

        val newEntities = remoteMeals.mapIndexed { index, meal ->
            meal.toDailyMealEntity(
                dayKey = today,
                displayOrder = index
            )
        }

        if (cachedMeals.isEmpty()){
            dailyMealDao.insertMeals(newEntities)
        } else {
            dailyMealDao.replaceMeals(newEntities)
        }

        return remoteMeals
    }

    private fun getTodayKey(): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.US)

        return formatter.format(Date())
    }
}