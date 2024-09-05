package com.sample.app.fetchrewardscodingexercise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.dogbreeds.ui.navigation.AppNavigation
import com.sample.app.fetchrewardscodingexercise.presentation.common.TopBar
import com.sample.app.fetchrewardscodingexercise.ui.theme.FetchRewardsCodingExerciseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            enableEdgeToEdge()
            FetchRewardsCodingExerciseTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()
                    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }

                    Scaffold(
                        topBar = {
                            TopBar(
                                Modifier,
                                navController,
                                bottomBarState
                            )
                        },

                        ) { padding ->

                        AppNavigation(
                            modifier = Modifier.fillMaxSize(),
                            navController = navController,
                            padding = padding,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = name,
        modifier = modifier
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    FetchRewardsCodingExerciseTheme {
        Greeting("Item List")
    }
}
