package com.example.quizapp.di

import android.app.Application
import androidx.annotation.Keep
import dagger.hilt.android.HiltAndroidApp

@Keep
@HiltAndroidApp
class BaseClass:Application() {

}