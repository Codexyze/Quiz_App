package com.example.quizapp.presentation.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quizapp.presentation.Screens.GetAllQuestionScreen
import com.example.quizapp.presentation.Screens.GetApacheKafkaQuestionScreen
import com.example.quizapp.presentation.Screens.GetBashQuestionScreen
import com.example.quizapp.presentation.Screens.GetDockerQuestionScreen
import com.example.quizapp.presentation.Screens.GetLinuxQuestionScreen
import com.example.quizapp.presentation.Screens.GetPostgreseQuestionScreen
import com.example.quizapp.presentation.Screens.GetReactQuestionScreen
import com.example.quizapp.presentation.Screens.HomeScreen

@Composable
fun MyApp(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = HOMESCREEN) {
        composable<HOMESCREEN> {
           HomeScreen(navController = navController)
        }
        composable<APACHE_KAFKA> {
           GetApacheKafkaQuestionScreen()
        }
        composable<DOCKER> {
            GetDockerQuestionScreen()
        }
        composable<BASH> {
            GetBashQuestionScreen()
        }
        composable<LINUX> {
            GetLinuxQuestionScreen()
        }
        composable<REACT> {
            GetReactQuestionScreen()
        }
        composable<POSTGRESQL> {
            GetPostgreseQuestionScreen()
        }
        composable<ALLQUESTIONSCREEN> {
            GetAllQuestionScreen()
        }

    }

}