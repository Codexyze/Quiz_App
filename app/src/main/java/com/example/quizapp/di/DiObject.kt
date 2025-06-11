package com.example.quizapp.di

import androidx.annotation.Keep
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
import dagger.hilt.components.SingletonComponent

@Keep
@Module
@InstallIn(SingletonComponent::class)
object DiObject {

@Provides
fun giveRepoInstance():RepositoryImpl{
    return RepositoryImpl()
}

@Provides
fun giveRepoIntefaceObject(): Repository{
    return RepositoryImpl()
}

@Provides
fun giveUsecaseAcessObject(): UseCaseAccess{
    return UseCaseAccess(
        getAllQuestionsUseCase = GetAllQuestionsUseCase(repository = giveRepoIntefaceObject()),
        getApacheKafkaQuestionUseCase = GetApacheKafkaQuestionUseCase(repository = giveRepoIntefaceObject()),
        getBashQuestionsUseCase = GetBashQuestionsUseCase(repository = giveRepoIntefaceObject()),
        getDockerQuestionUseCase = GetDockerQuestionUseCase(repository = giveRepoIntefaceObject()),
        getLinuxQuestionUseCase = GetLinuxQuestionUseCase(repository = giveRepoIntefaceObject()),
        getPostGreseQuestionsUseCase = GetPostGreseQuestionsUseCase(repository = giveRepoIntefaceObject()),
        getReactQuestionsUseCase = GetReactQuestionsUseCase(repository = giveRepoIntefaceObject())
    )
}

}