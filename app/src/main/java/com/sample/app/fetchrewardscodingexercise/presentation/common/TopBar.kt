package com.sample.app.fetchrewardscodingexercise.presentation.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.sample.app.fetchrewardscodingexercise.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    navController: NavController,
    bottomBarState: MutableState<Boolean>
) {

    TopAppBar(
        modifier = modifier,
        title =
        {
            Text(
                text = stringResource(R.string.app_name),
            )

        },
        navigationIcon = {
            if (!bottomBarState.value) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Close"
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(),
    )

}

