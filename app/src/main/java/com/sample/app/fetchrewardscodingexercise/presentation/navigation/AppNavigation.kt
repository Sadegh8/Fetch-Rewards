package com.example.dogbreeds.ui.navigation

import androidx.compose.animation.*
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sample.app.fetchrewardscodingexercise.presentation.list.ListScreen


@ExperimentalAnimationApi
@Composable
internal fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    padding: PaddingValues,
) {
    NavHost(
        navController = navController,
        startDestination = Routes.LIST,
        modifier = modifier
    ) {
        addList(
            padding = padding
        )
    }
}


@ExperimentalAnimationApi
private fun NavGraphBuilder.addList(
    padding: PaddingValues,
) {
    composable(
        route = Routes.LIST
    )
    {
        ListScreen(
            modifier = Modifier.padding(padding)
        )
    }

}

