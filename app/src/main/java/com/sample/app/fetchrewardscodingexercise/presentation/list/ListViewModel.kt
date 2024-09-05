package com.sample.app.fetchrewardscodingexercise.presentation.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.app.fetchrewardscodingexercise.data.common.Resource
import com.sample.app.fetchrewardscodingexercise.domain.useCases.GetListUseCase
import com.sample.app.fetchrewardscodingexercise.presentation.list.data.ListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    getListUseCase: GetListUseCase
) : ViewModel() {
    var listState by mutableStateOf(ListState())
        private set

    init {
        getListUseCase().onEach { result ->
            listState = when (result) {
                is Resource.Success -> {
                    ListState(list = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    ListState(error = result.message ?: "An unexpected error occurred")
                }

                is Resource.Loading -> {
                    listState.copy(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)
    }
}
