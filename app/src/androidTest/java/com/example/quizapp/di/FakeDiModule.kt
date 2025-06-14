package com.example.quizapp.di

import com.example.quizapp.Constants.Constants
import com.example.quizapp.Constants.TestJson
import com.example.quizapp.Domain.RepositoryInterface.Repository
import com.example.quizapp.Domain.UseCases.GetAllQuestionsUseCase
import com.example.quizapp.Domain.UseCases.GetApacheKafkaQuestionUseCase
import com.example.quizapp.Domain.UseCases.GetBashQuestionsUseCase
import com.example.quizapp.Domain.UseCases.GetDockerQuestionUseCase
import com.example.quizapp.Domain.UseCases.GetLinuxQuestionUseCase
import com.example.quizapp.Domain.UseCases.GetPostGreseQuestionsUseCase
import com.example.quizapp.Domain.UseCases.GetReactQuestionsUseCase
import com.example.quizapp.Domain.UseCases.UseCaseAccess
import com.example.quizapp.data.RepositoryImpl.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.ByteReadChannel
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FakeDiModule {

    @Provides
    @Singleton
    fun provideKtorClient(): HttpClient {

        // 🎯 Setup MockEngine with route matching
        val mockEngine = MockEngine { request ->
            val url = request.url.toString()
            val responseJson = when {
                "category=${Constants.REACT}" in url -> TestJson.DOCKERJSON
                "category=${Constants.DOCKER}" in url -> TestJson.DOCKERJSON
                else -> "[]" // Default empty response
            }

            respond(
                content = ByteReadChannel(responseJson),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        // 📦 Build HttpClient with this mock engine
        return HttpClient(mockEngine) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    prettyPrint = true
                })
            }
        }
    }


    @Provides
    fun giveRepoInstance(httpClient: HttpClient):RepositoryImpl{
        return RepositoryImpl(httpClient = httpClient)
    }

    @Provides
    fun giveRepoIntefaceObject(httpClient: HttpClient): Repository{
        return RepositoryImpl(httpClient = httpClient)
    }

    @Provides
    fun giveUsecaseAcessObject(httpClient: HttpClient): UseCaseAccess{
        return UseCaseAccess(
            getAllQuestionsUseCase = GetAllQuestionsUseCase(repository = giveRepoIntefaceObject(httpClient = httpClient)),
            getApacheKafkaQuestionUseCase = GetApacheKafkaQuestionUseCase(repository = giveRepoIntefaceObject(httpClient = httpClient)),
            getBashQuestionsUseCase = GetBashQuestionsUseCase(repository = giveRepoIntefaceObject(httpClient = httpClient)),
            getDockerQuestionUseCase = GetDockerQuestionUseCase(repository = giveRepoIntefaceObject(httpClient = httpClient)),
            getLinuxQuestionUseCase = GetLinuxQuestionUseCase(repository = giveRepoIntefaceObject(httpClient = httpClient)),
            getPostGreseQuestionsUseCase = GetPostGreseQuestionsUseCase(repository = giveRepoIntefaceObject(httpClient = httpClient)),
            getReactQuestionsUseCase = GetReactQuestionsUseCase(repository = giveRepoIntefaceObject(httpClient = httpClient))
        )
    }

}

