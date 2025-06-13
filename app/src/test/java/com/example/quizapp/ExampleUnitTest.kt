package com.example.quizapp

import com.example.quizapp.Domain.UseCases.GetAllQuestionsUseCase
import com.example.quizapp.StateHandling.ApiResult
import com.example.quizapp.data.RepositoryImpl.FakeRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
   private lateinit var fakeRepository: FakeRepository
   private lateinit var getAllQuestionsUseCase: GetAllQuestionsUseCase
    @Before
    fun setUp(){
        fakeRepository = FakeRepository()
        getAllQuestionsUseCase = GetAllQuestionsUseCase(repository = fakeRepository)
    }
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun `get all questions test case`() = runBlocking{
        // Collect the flow emissions
        val result = getAllQuestionsUseCase.getAllQuestionsUseCase().first()

        // Check if the result is Success
        assertTrue(result is ApiResult.Success)

        // If it's a success, check the size and content
        val data = (result as ApiResult.Success).data

        assertEquals(3, data.size) // we added 3 dummy items

        // Check content of first item for sanity
        assertEquals("abc", data[0].category)
        assertEquals("abc", data[0].question)


    }



}