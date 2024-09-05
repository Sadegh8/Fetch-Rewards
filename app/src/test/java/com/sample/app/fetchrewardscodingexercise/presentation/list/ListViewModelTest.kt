package com.sample.app.fetchrewardscodingexercise.presentation.list

import com.sample.app.fetchrewardscodingexercise.data.common.Resource
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
