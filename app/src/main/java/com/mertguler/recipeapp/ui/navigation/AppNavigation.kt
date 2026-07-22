package com.mertguler.recipeapp.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.mertguler.recipeapp.ui.screens.HomeScreen
import com.mertguler.recipeapp.ui.screens.MealDetailScreen

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier
) {
    val backStack = rememberNavBackStack(HomeRoute)

    NavDisplay(
        modifier = modifier.fillMaxSize(),
        backStack = backStack,

        onBack = {
            backStack.removeLastOrNull()
        },

        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),

        entryProvider = entryProvider {
            entry<HomeRoute> {
                HomeScreen(
                    onMealClick = { mealId ->
                        backStack.add(
                            MealDetailRoute(mealId = mealId)
                        )
                    }
                )
            }

            entry<MealDetailRoute> { route ->
                MealDetailScreen(
                    mealId = route.mealId,
                    onBackClick = {
                        backStack.removeLastOrNull()
                    }
                )
            }
        }
    )
}