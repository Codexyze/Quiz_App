package com.example.quizapp.Domain.UseCases

data class UseCaseAccess(
    val getAllQuestionsUseCase: GetAllQuestionsUseCase,
    val getApacheKafkaQuestionUseCase: GetApacheKafkaQuestionUseCase,
    val getBashQuestionsUseCase: GetBashQuestionsUseCase,
    val getDockerQuestionUseCase: GetDockerQuestionUseCase,
    val getLinuxQuestionUseCase: GetLinuxQuestionUseCase,
    val getPostGreseQuestionsUseCase: GetPostGreseQuestionsUseCase,
    val getReactQuestionsUseCase: GetReactQuestionsUseCase,
)
