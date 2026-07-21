package com.mertguler.recipeapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mertguler.recipeapp.ui.components.CardSlider

@Composable
fun HomeScreen() {
        Column(
            modifier = Modifier
                .fillMaxSize()  // Match Parent veya sabit vermek icin width, height
                .padding(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CardSlider()
        }
}

