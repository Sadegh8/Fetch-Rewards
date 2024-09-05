package com.sample.app.fetchrewardscodingexercise.domain.repository

import com.sample.app.fetchrewardscodingexercise.domain.model.ListItems

interface ListRepository {
    suspend fun fetchList() : List<ListItems>
}
