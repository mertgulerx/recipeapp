package com.mertguler.recipeapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mertguler.recipeapp.data.remote.dto.MealDto
import coil3.compose.AsyncImage
import com.mertguler.recipeapp.data.remote.dto.CategoryDto

@Composable
fun CardSlider(
    title: String,
    meals: List<MealDto>,
    onMealClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        Text(
            text = title,
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
//            contentPadding = PaddingValues(horizontal = 12.dp)
        ) {
            items(
                items = meals,
                key = { meal -> meal.idMeal }
            ) {
                    meal -> FoodCard(
                title = meal.strMeal,
                imageUrl = meal.strMealThumb,
                onClick = {
                    onMealClick(meal.idMeal)
                }
            )
            }
        }
    }
}

fun LazyGridScope.cardGrid(
    title: String,
    categories: List<CategoryDto>
) {
    item(
        span = { GridItemSpan(maxLineSpan) }
    ) {
        Text(
            text = title,
            fontSize = 20.sp
        )
    }

    items(
        items = categories,
        key = { category -> category.idCategory }
    ) { category ->
        FoodCard(
            title = category.strCategory,
            imageUrl = category.strCategoryThumb,
            onClick = {
                println("${category.strCategory} selected")
            }
        )
    }
}

@Composable
fun FoodCard(
    title: String,
    imageUrl: String?,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .width(240.dp)
            .height(220.dp),

        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column {
            AsyncImage(
                model = imageUrl,
                contentDescription = "$title tarif gorseli",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(175.dp),
                contentScale = ContentScale.Crop,
                placeholder = ColorPainter(
                    MaterialTheme.colorScheme.surfaceVariant
                ),
                error = ColorPainter(
                    MaterialTheme.colorScheme.errorContainer
                )
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
