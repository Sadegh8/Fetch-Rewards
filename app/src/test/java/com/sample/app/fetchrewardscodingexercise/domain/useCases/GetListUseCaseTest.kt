package com.sample.app.fetchrewardscodingexercise.domain.useCases

import com.sample.app.fetchrewardscodingexercise.data.common.Resource
import com.sample.app.fetchrewardscodingexercise.domain.model.ListItems
import com.sample.app.fetchrewardscodingexercise.domain.repository.ListRepository
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import org.mockito.kotlin.doReturn

class GetListUseCaseTest {

    private lateinit var useCase: GetListUseCase
    private lateinit var mockRepository: ListRepository

    @Before
    fun setUp() {
        mockRepository = mock()
        useCase = GetListUseCase(mockRepository)
    }

    @Test
    fun `invoke - successful data retrieval - emits Success`() = runTest {
        // Mock repository behavior
        val mockList = listOf(
            ListItems("id1", 1, "Item 1"),
            ListItems("id2", 2, null),
            ListItems("id3", 1, "Item 2"),
        )
        whenever(mockRepository.fetchList()).doReturn(mockList)

        // Invoke use case and collect first emission
        val result = useCase()
        val emissions = result.toList()

        // Assert the first emission is Loading
        assert(emissions.first() is Resource.Loading)

        // Assert emission is Success with sorted list
        assertEquals(
            Resource.Success(
                listOf(
                    ListItems("id1", 1, "Item 1"),
                    ListItems("id3", 1, "Item 2")
                )
            ).data, emissions.last().data
        )
    }

    @Test
    fun `invoke - empty list - emits Error with message`() = runTest {
        // Mock repository behavior
        whenever(mockRepository.fetchList()).doReturn(emptyList())
        // Invoke use case and collect first emission
        val result = useCase()
        val emissions = result.toList()

        // Assert the first emission is Loading
        assert(emissions.first() is Resource.Loading)

        // Assert the last emission is Error with "Not found" message
        assertEquals(Resource.Error(message = "Not found", data = null).message, emissions.last().message)
    }
}
