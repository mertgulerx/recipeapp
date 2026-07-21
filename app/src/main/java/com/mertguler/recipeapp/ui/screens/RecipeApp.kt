package com.mertguler.recipeapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mertguler.recipeapp.ui.components.AppHeader

@Composable
fun RecipeApp(){
    Scaffold(
        topBar = {
            AppHeader()
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            HomeScreen()
        }
    }
}