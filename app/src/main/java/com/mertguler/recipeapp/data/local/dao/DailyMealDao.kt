package com.mertguler.recipeapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.mertguler.recipeapp.data.local.entity.CategoryEntity
import com.mertguler.recipeapp.data.local.entity.DailyMealEntity

@Dao
interface DailyMealDao{
    @Query(
        """
            SELECT * FROM daily_meals
        """
    )
    suspend fun getAllMails() : List<DailyMealEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeals(
        meals: List<DailyMealEntity>
    )

    @Query("DELETE FROM daily_meals")
    suspend fun deleteAllMeals()

    @Transaction
    suspend fun replaceMeals(
        meals: List<DailyMealEntity>
    ) {
        deleteAllMeals()
        insertMeals(meals)
    }

    @Query(
        "SELECT * FROM categories"
    )
    suspend fun getAllCategories(): List<CategoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<CategoryEntity>)

    @Query("DELETE FROM categories WHERE idCategory = :idCategory")
    suspend fun deleteCategory(idCategory: String)


}
