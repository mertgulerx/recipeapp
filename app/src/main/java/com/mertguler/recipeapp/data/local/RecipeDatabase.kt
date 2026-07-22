package com.mertguler.recipeapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mertguler.recipeapp.data.local.dao.DailyMealDao
import com.mertguler.recipeapp.data.local.entity.CategoryEntity
import com.mertguler.recipeapp.data.local.entity.DailyMealEntity

@Database(
    entities = [DailyMealEntity::class,
        CategoryEntity::class],
    version = 2,
    exportSchema = false
)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun dailyMealDao(): DailyMealDao
    companion object {

        @Volatile
        private var INSTANCE: RecipeDatabase? = null

        fun getInstance(context: Context): RecipeDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    RecipeDatabase::class.java,
                    "recipe_database"
                )
                    .build()
                    .also { database ->
                        INSTANCE = database
                    }
            }
        }
    }
}