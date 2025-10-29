package com.example.quizapp.presentation.AllViewmodel

import androidx.annotation.Keep
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.Domain.RepositoryInterface.ApiResult
import com.example.quizapp.Domain.UseCases.UseCaseAccess
import com.example.quizapp.StateHandling.dockerState.DockerResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@Keep
@HiltViewModel
class DockerViewModel @Inject constructor(private val useCaseAccess: UseCaseAccess) : ViewModel(){
    //private val _getDockerQuestionState = MutableStateFlow(DockerResponseState())

    private val _getDockerQuestionState = MutableStateFlow<DockerResponseState>(DockerResponseState.Idle)

    val getDockerQuestionState=_getDockerQuestionState.asStateFlow()




    fun getDockerQuestions() {
        viewModelScope.launch(Dispatchers.IO) {
            useCaseAccess.getDockerQuestionUseCase.getDockerQuestionsUseCase().collectLatest { ApiResult ->
                withContext(Dispatchers.Main) {
                    when (ApiResult) {
                        is ApiResult.Loading -> {
                            _getDockerQuestionState.value = DockerResponseState.Loading
                        }

                        is ApiResult.Success -> {
                            _getDockerQuestionState.value =
                                DockerResponseState.Success(data = ApiResult.data)
                        }

                        is ApiResult.Error -> {
                            _getDockerQuestionState.value =
                                DockerResponseState.Error(error = ApiResult.message)
                        }
                    }
                }
            }
        }
    }

}