package com.sample.app.fetchrewardscodingexercise.presentation.list.data

import com.sample.app.fetchrewardscodingexercise.domain.model.ListItems

data class ListState(
    val isLoading: Boolean = false,
    val list: List<ListItems> = emptyList(),
    val error: String = ""
)
