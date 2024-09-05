package com.sample.app.fetchrewardscodingexercise.presentation.list

import com.sample.app.fetchrewardscodingexercise.data.common.Resource
import com.sample.app.fetchrewardscodingexercise.domain.model.ListItems
import com.sample.app.fetchrewardscodingexercise.domain.useCases.GetListUseCase
import com.sample.app.fetchrewardscodingexercise.presentation.list.data.ListState
import com.sample.app.fetchrewardscodingexercise.util.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class ListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var getListUseCase: GetListUseCase
    private lateinit var viewModel: ListViewModel

    @Before
    fun setup() {
        getListUseCase = mock()
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `success with sorted list`() = runTest {
        // Mock the use case to return a flow that emits Resource.Loading, then Resource.Success
        val mockListItems = listOf(
            ListItems(id = "1", listId = 2, name = "B Item"),
            ListItems(id = "2", listId = 1, name = "A Item"),
            ListItems(id = "3", listId = 3, name = "C Item")
        )

        whenever(getListUseCase()).thenReturn(
            flow {
                emit(Resource.Loading())
                emit(Resource.Success(mockListItems))
            }
        )

        // Create the ViewModel
        viewModel = ListViewModel(getListUseCase)

        advanceUntilIdle()

        // After the success emission, the state should have the sorted list
        val expectedList = listOf(
            ListItems(id = "2", listId = 1, name = "A Item"),
            ListItems(id = "1", listId = 2, name = "B Item"),
            ListItems(id = "3", listId = 3, name = "C Item")
        )
        assertEquals(ListState(list = expectedList), viewModel.listState)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `error state`() = runTest {
        // Mock the use case to return a flow that emits Resource.Loading, then Resource.Error
        whenever(getListUseCase()).thenReturn(
            flow {
                emit(Resource.Loading())
                emit(Resource.Error("Network error"))
            }
        )

        // Create the ViewModel
        viewModel = ListViewModel(getListUseCase)
        advanceUntilIdle()
        // After the error emission, the state should have the error message
        assertEquals(ListState(error = "Network error"), viewModel.listState)
    }
}
