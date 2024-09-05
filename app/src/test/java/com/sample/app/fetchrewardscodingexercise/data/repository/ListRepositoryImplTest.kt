package com.sample.app.fetchrewardscodingexercise.data.repository

import com.sample.app.fetchrewardscodingexercise.data.remote.ListApi
import com.sample.app.fetchrewardscodingexercise.data.remote.dto.Items
import com.sample.app.fetchrewardscodingexercise.domain.model.ListItems
import com.sample.app.fetchrewardscodingexercise.domain.repository.ListRepository
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.junit.Assert.*

class ListRepositoryImplTest {

    private lateinit var listRepository: ListRepository
    private lateinit var listApi: ListApi

    @Before
    fun setup() {
        listApi = mock()
        listRepository = ListRepositoryImpl(listApi)
    }

    @Test
    fun `fetchList returns list from API`() = runTest {
        // Mock the API response
        val mockItems = listOf(
            Items(id = 1, listId = 1, name = "Item 1"),
            Items(id = 2, listId = 2, name = null)
        )
        whenever(listApi.getList()).thenReturn(mockItems)

        // Expected result after conversion
        val expectedListItems = listOf(
            ListItems(id = "1", listId = 1, name = "Item 1"),
            ListItems(id = "2", listId = 2, name = null)
        )

        // Call the repository function
        val result = listRepository.fetchList()

        // Verify the result matches the expected output
        assertEquals(expectedListItems, result)
    }
}