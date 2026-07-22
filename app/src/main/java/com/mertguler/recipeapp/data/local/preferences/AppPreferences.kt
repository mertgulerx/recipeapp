package com.mertguler.recipeapp.data.local.preferences

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private val Context.dataStore by preferencesDataStore(
    name = "app_preferences"
)

object AppPreferences{

    private lateinit var appContext: Context

    fun initialize(context: Context) {
        appContext = context.applicationContext
    }

    private val LAST_CATEGORY_CHECK_DATE =
            stringPreferencesKey("last_category_check_date")

    suspend fun getLastCategoryCheckDateString(): String{
        val preferences = appContext.dataStore.data.first()

        return preferences[LAST_CATEGORY_CHECK_DATE].orEmpty()
    }

    suspend fun getLastCategoryCheckDate(): LocalDateTime?{
        val preferences = appContext.dataStore.data.first()

        if (preferences[LAST_CATEGORY_CHECK_DATE].isNullOrEmpty()){
            return null
        }

        return LocalDateTime.parse(preferences[LAST_CATEGORY_CHECK_DATE], DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))
    }

    suspend fun setLastCategoryCheckDate(
        date: LocalDateTime
    ) {
        appContext.dataStore.edit { preferences ->
            preferences[LAST_CATEGORY_CHECK_DATE] = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))
        }
    }
}