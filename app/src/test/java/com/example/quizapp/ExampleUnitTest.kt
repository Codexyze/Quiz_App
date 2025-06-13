package com.example.quizapp

import com.example.quizapp.Domain.UseCases.GetAllQuestionsUseCase
import com.example.quizapp.Domain.UseCases.GetApacheKafkaQuestionUseCase
import com.example.quizapp.Domain.UseCases.GetBashQuestionsUseCase
import com.example.quizapp.Domain.UseCases.GetDockerQuestionUseCase
import com.example.quizapp.Domain.UseCases.GetLinuxQuestionUseCase
import com.example.quizapp.Domain.UseCases.GetPostGreseQuestionsUseCase
import com.example.quizapp.Domain.UseCases.GetReactQuestionsUseCase
import com.example.quizapp.StateHandling.ApiResult
import com.example.quizapp.data.RepositoryImpl.FakeRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
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
   private lateinit var getApacheKafkaQuestionUseCase: GetApacheKafkaQuestionUseCase
   private lateinit var getBashQuestionsUseCase: GetBashQuestionsUseCase
   private lateinit var getDockerQuestionUseCase: GetDockerQuestionUseCase
   private lateinit var getLinuxQuestionUseCase: GetLinuxQuestionUseCase
   private lateinit var getPostGreseQuestionsUseCase: GetPostGreseQuestionsUseCase
   private lateinit var getReactQuestionsUseCase: GetReactQuestionsUseCase
    @Before
    fun setUp(){
        fakeRepository = FakeRepository()
        getAllQuestionsUseCase = GetAllQuestionsUseCase(repository = fakeRepository)
        getApacheKafkaQuestionUseCase= GetApacheKafkaQuestionUseCase(repository = fakeRepository)
        getBashQuestionsUseCase = GetBashQuestionsUseCase(repository = fakeRepository)
        getDockerQuestionUseCase = GetDockerQuestionUseCase(repository = fakeRepository)
        getLinuxQuestionUseCase = GetLinuxQuestionUseCase(repository = fakeRepository)
        getPostGreseQuestionsUseCase = GetPostGreseQuestionsUseCase(repository = fakeRepository)
        getReactQuestionsUseCase = GetReactQuestionsUseCase(repository = fakeRepository)
    }

    @After
    fun tearDown(){
       //Automatically garbage collected
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

    @Test
    fun `get apache kafka question test`() = runBlocking{
        val getApacaheKafkaFirstElement = getApacheKafkaQuestionUseCase.getApacheKafkaQuestionsUseCase().first()
        val ifSuccess = getApacaheKafkaFirstElement is ApiResult.Success
        assertTrue(ifSuccess)
        val data = (getApacaheKafkaFirstElement as ApiResult.Success).data
        assertEquals(3,data.size)
        assertEquals(1,data[0].id)

    }

    @Test
    fun `get Bash Question Test`() = runBlocking{
        val getBashQuestionFirstElement = getBashQuestionsUseCase.getBashQuestionsUseCase().first()
        val resultSuccess = getBashQuestionFirstElement is ApiResult.Success
        assertTrue(resultSuccess)
        val data = (getBashQuestionFirstElement as ApiResult.Success).data
        val dataAtZero = data[0].id
        assertEquals(1,dataAtZero)
    }

    @Test
    fun `get Docker Question Test`() = runBlocking{
        val getDockerQuestionFirstElement = getDockerQuestionUseCase.getDockerQuestionsUseCase().first()
        val resultSuccess = getDockerQuestionFirstElement is ApiResult.Success
        assertTrue(resultSuccess)
        val data = ( getDockerQuestionFirstElement as ApiResult.Success).data
        val dataAtZero = data[0].id
        assertEquals(1,dataAtZero)
    }

    @Test
    fun `get Linux Question Test`() = runBlocking{
        val getLinuxQuestionFirstElement = getLinuxQuestionUseCase.getLinuxQuestionsUseCase().first()
        val resultSuccess = getLinuxQuestionFirstElement is ApiResult.Success
        assertTrue(resultSuccess)
        val data = ( getLinuxQuestionFirstElement as ApiResult.Success).data
        val dataAtZero = data[0].id
        assertEquals(1,dataAtZero)
    }

    @Test
    fun `get Postgres Question Test`() = runBlocking{
        val getPostGreseQuestionFirstElement = getPostGreseQuestionsUseCase.getPostGreseQuestionsUseCase().first()
        val resultSuccess = getPostGreseQuestionFirstElement is ApiResult.Success
        assertTrue(resultSuccess)
        val data = ( getPostGreseQuestionFirstElement as ApiResult.Success).data
        val dataAtZero = data[0].id
        assertEquals(1,dataAtZero)
    }

    @Test
    fun `get React Question Test`() = runBlocking{
        val getReactQuestionFirstElement = getReactQuestionsUseCase.getReactQuestionsUseCase().first()
        val resultSuccess = getReactQuestionFirstElement is ApiResult.Success
        assertTrue(resultSuccess)
        val data = ( getReactQuestionFirstElement as ApiResult.Success).data
        val dataAtZero = data[0].id
        assertEquals(1,dataAtZero)
    }



}