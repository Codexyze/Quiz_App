package com.example.quizapp.presentation.AllViewmodel

import androidx.annotation.Keep
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.Domain.RepositoryInterface.ApiResult
import com.example.quizapp.Domain.UseCases.UseCaseAccess
import com.example.quizapp.StateHandling.linuxResponseState.LinuxResponseState
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
class LinuxViewModel @Inject constructor(private val usecaseAcess: UseCaseAccess):ViewModel (){
    private val _getLinuxResponseState= MutableStateFlow<LinuxResponseState>(LinuxResponseState.Idle)
    val getLinuxResponseState=_getLinuxResponseState.asStateFlow()
    fun getLinuxQuestions() {
        viewModelScope.launch(Dispatchers.IO) {
            usecaseAcess.getLinuxQuestionUseCase.getLinuxQuestionsUseCase().collectLatest { ApiResult ->
                withContext(Dispatchers.Main) {
                    when (ApiResult) {
                        is ApiResult.Loading -> {
                            _getLinuxResponseState.value = LinuxResponseState.Loading
                        }

                        is ApiResult.Success -> {
                            _getLinuxResponseState.value =
                                LinuxResponseState.Success(data = ApiResult.data)
                        }

                        is ApiResult.Error -> {
                            _getLinuxResponseState.value =
                                LinuxResponseState.Error(error = ApiResult.message)
                        }
                    }
                }
            }
        }
    }
}