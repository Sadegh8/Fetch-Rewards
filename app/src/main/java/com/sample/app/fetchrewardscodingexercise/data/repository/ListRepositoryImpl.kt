package com.sample.app.fetchrewardscodingexercise.data.repository

import com.sample.app.fetchrewardscodingexercise.data.remote.ListApi
import com.sample.app.fetchrewardscodingexercise.data.remote.dto.toListItems
import com.sample.app.fetchrewardscodingexercise.domain.model.ListItems
import com.sample.app.fetchrewardscodingexercise.domain.repository.ListRepository
import javax.inject.Inject

class ListRepositoryImpl @Inject constructor(
    private val listApi: ListApi,
) : ListRepository {

    override suspend fun fetchList(): List<ListItems> {
        return listApi.getList().toListItems()
    }
}
