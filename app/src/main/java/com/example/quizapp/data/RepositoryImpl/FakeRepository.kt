package com.example.quizapp.data.RepositoryImpl

import com.example.quizapp.Domain.RepositoryInterface.ApiResult
import com.example.quizapp.Domain.RepositoryInterface.Repository
import com.example.quizapp.data.Models.Answers
import com.example.quizapp.data.Models.CorrectAnswers
import com.example.quizapp.data.Models.QnaResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRepository: Repository {
    val listOfQnaResponse: List<QnaResponse> =listOf(
        QnaResponse(
            answers = Answers(),
            category = "abc",
            correct_answer = "abc",
            correct_answers = CorrectAnswers(),
            description = "abc",
            difficulty = "abc",
            explanation = "abc",
            id = 1,
            multiple_correct_answers = "abc",
            question = "abc",
            tags = listOf(),
            tip = "abc"
        ),
        QnaResponse(
            answers = Answers(),
            category = "bac",
            correct_answer = "bac",
            correct_answers = CorrectAnswers(),
            description = "bac",
            difficulty = "bac",
            explanation = "bac",
            id = 1,
            multiple_correct_answers = "bac",
            question = "bac",
            tags = listOf(),
            tip = "bac"
        ),
        QnaResponse(
            answers = Answers(),
            category = "axxxbc",
            correct_answer = "axbc",
            correct_answers = CorrectAnswers(),
            description = "axbc",
            difficulty = "abxc",
            explanation = "axbc",
            id = 1,
            multiple_correct_answers = "axxbc",
            question = "abxxx",
            tags = listOf(),
            tip = "axx"
        ),


    )

    override suspend fun getPostgreseQuestions(): Flow<ApiResult<List<QnaResponse>>> =flow{
            emit(ApiResult.Success(listOfQnaResponse))
    }

    override suspend fun getReactQuestions(): Flow<ApiResult<List<QnaResponse>>> =flow{
         emit(ApiResult.Success(listOfQnaResponse))

    }

    override suspend fun getApacheKafkaQuestions(): Flow<ApiResult<List<QnaResponse>>> =flow{
        emit(ApiResult.Success(listOfQnaResponse))
    }

    override suspend fun getBashQuestions(): Flow<ApiResult<List<QnaResponse>>> {
        return flow {
            emit(ApiResult.Success(listOfQnaResponse))
        }
    }

    override suspend fun getLinuxQuestions(): Flow<ApiResult<List<QnaResponse>>> {
        return flow {
            emit(ApiResult.Success(listOfQnaResponse))
        }
    }

    override suspend fun getDockerQuestions(): Flow<ApiResult<List<QnaResponse>>> {
        return flow {
            emit(ApiResult.Success(listOfQnaResponse))
        }
    }

    override suspend fun getAllQuestions(): Flow<ApiResult<List<QnaResponse>>> =flow{
        emit(ApiResult.Success(listOfQnaResponse))
    }
}
