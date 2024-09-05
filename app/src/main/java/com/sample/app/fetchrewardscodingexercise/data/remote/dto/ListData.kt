package com.sample.app.fetchrewardscodingexercise.data.remote.dto

import com.sample.app.fetchrewardscodingexercise.domain.model.ListItems


data class Items(
    val id: Int,
    val listId: Int,
    val name: String?,
)

fun List<Items>.toListItems(): List<ListItems> {
    return this.map {
        ListItems(
            id = it.id.toString(),
            listId = it.listId,
            name = it.name,
        )
    }
}
