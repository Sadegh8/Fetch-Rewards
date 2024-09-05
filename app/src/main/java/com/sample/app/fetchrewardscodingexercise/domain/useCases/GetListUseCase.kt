package com.sample.app.fetchrewardscodingexercise.domain.useCases

import android.util.Log
import com.sample.app.fetchrewardscodingexercise.data.common.Resource
import com.sample.app.fetchrewardscodingexercise.domain.model.ListItems
import com.sample.app.fetchrewardscodingexercise.domain.repository.ListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetListUseCase @Inject constructor(
    private val repository: ListRepository
) {
    operator fun invoke(): Flow<Resource<List<ListItems>>> = flow {
        emit(Resource.Loading())
        try {
            val list = repository.fetchList()
            val sortedList = list
                .filter { !it.name.isNullOrEmpty() }
                .sortedWith(compareBy<ListItems> { it.listId }.thenBy { it.name })
            if (sortedList.isNotEmpty()) {
                emit(Resource.Success(sortedList))
            } else {
                emit(Resource.Error("Not found"))
            }

        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
            Log.e("Error in getting data", e.message.toString())
        }
    }
}
