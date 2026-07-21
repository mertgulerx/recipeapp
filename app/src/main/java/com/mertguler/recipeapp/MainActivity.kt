package com.mertguler.recipeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.mertguler.recipeapp.ui.screens.RecipeApp
import com.mertguler.recipeapp.ui.theme.RecipeAppTheme

class MainActivity : ComponentActivity() { // Extends
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecipeAppTheme {
                RecipeApp()
            }
        }
    }
}