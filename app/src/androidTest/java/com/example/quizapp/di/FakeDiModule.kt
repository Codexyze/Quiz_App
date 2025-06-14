package com.example.quizapp.di

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
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@HiltAndroidTest
@Module
@UninstallModules(DiObject::class)
object FakeDiModule {


    @Provides
    @Singleton
    fun provideKtorClient(): HttpClient{
        val httpClient = HttpClient(MockEngine){

            install(ContentNegotiation){
                json(Json{
                    ignoreUnknownKeys=true
                    isLenient = true
                    prettyPrint=true
                })
            }

        }
        return httpClient

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

