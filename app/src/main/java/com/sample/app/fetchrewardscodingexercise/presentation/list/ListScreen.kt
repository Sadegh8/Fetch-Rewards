package com.sample.app.fetchrewardscodingexercise.presentation.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sample.app.fetchrewardscodingexercise.presentation.list.components.ListItemView
import com.sample.app.fetchrewardscodingexercise.presentation.list.data.ListState
import com.sample.app.fetchrewardscodingexercise.utils.DummyData

@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    listViewModel: ListViewModel = hiltViewModel(),
) {
    val state = listViewModel.listState
    ListScreen(modifier = modifier, state = state)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    state: ListState,
) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when {
            state.isLoading -> {
                CircularProgressIndicator()
            }

            state.error.isNotEmpty() -> {
                Text(text = "Error in getting data!")
            }

            else -> {
                LazyColumn(modifier = Modifier.fillMaxSize(), state = rememberLazyListState()) {
                    items(state.list) { item ->
                        item.name?.let {
                            ListItemView(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(115.dp)
                                    .animateItemPlacement(),
                                id = item.id,
                                listId = item.listId.toString(),
                                name = it,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun BreedsScreenPreview() {
    ListScreen(state = ListState(list = DummyData.dummyList))
}
