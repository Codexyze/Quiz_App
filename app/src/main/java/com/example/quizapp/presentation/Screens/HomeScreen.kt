package com.example.quizapp.presentation.Screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.quizapp.Constants.TestTags
import com.example.quizapp.presentation.Navigation.ALLQUESTIONSCREEN
import com.example.quizapp.presentation.Navigation.APACHE_KAFKA
import com.example.quizapp.presentation.Navigation.BASH
import com.example.quizapp.presentation.Navigation.DOCKER
import com.example.quizapp.presentation.Navigation.LINUX
import com.example.quizapp.presentation.Navigation.POSTGRESQL
import com.example.quizapp.presentation.Navigation.REACT


@Composable
fun HomeScreen(navController: NavController) {
   Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()), horizontalAlignment = Alignment.CenterHorizontally) {
       Spacer(modifier = Modifier.height(32.dp))
       Button(
           onClick = {
               navController.navigate(APACHE_KAFKA)
           },
           modifier = Modifier.fillMaxWidth(0.9f).height(60.dp).testTag(TestTags.APACHE_KAFKA),
           colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)), // Green background
           shape = RectangleShape // Removes rounded corners
       ) {
           Text(text = "Apache Kafka")
       }
       Spacer(modifier = Modifier.height(16.dp))

       Button(
           onClick = {
               navController.navigate(BASH)
           },
           modifier = Modifier.fillMaxWidth(0.9f).height(60.dp).testTag(TestTags.BASH),
           colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2)), // Custom color
           shape = RectangleShape
       ) {
           Text(text = "Bash")
       }
       Spacer(modifier = Modifier.height(16.dp))
       Button(
           onClick = {
               navController.navigate(DOCKER)
           },
           modifier = Modifier.fillMaxWidth(0.9f).height(60.dp).testTag(TestTags.DOCKER),
           colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
           shape = RectangleShape
       ) {
           Text(text = "Docker")
       }
       Spacer(modifier = Modifier.height(16.dp))
       Button(
           onClick = {
               navController.navigate(REACT)
           },
           modifier = Modifier.fillMaxWidth(0.9f).height(60.dp).testTag(TestTags.REACT),
           colors = ButtonDefaults.buttonColors(containerColor = Color.Cyan),
           shape = RectangleShape
       ) {
           Text(text = "React")
       }
       Spacer(modifier = Modifier.height(16.dp))
       Button(
           onClick = {
               navController.navigate(LINUX)
           },
           modifier = Modifier.fillMaxWidth(0.9f).height(60.dp).testTag(TestTags.LINUX),
           colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF0DE43)),
           shape = RectangleShape
       ) {
           Text(text = "Linux")
       }
       Spacer(modifier = Modifier.height(16.dp))
       Button(
           onClick = {
               navController.navigate(POSTGRESQL)
           },
           modifier = Modifier.fillMaxWidth(0.9f).height(60.dp).testTag(TestTags.POSTGRESQL),
           colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF74C40)),
           shape = RectangleShape
       ) {
           Text(text = "Postgres")
       }
       Spacer(modifier = Modifier.height(16.dp))
       Button(
           onClick = {
               navController.navigate(ALLQUESTIONSCREEN)
           },
           modifier = Modifier.fillMaxWidth(0.9f).height(60.dp).testTag(TestTags.GETRANDOMQUIZ),
           colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFCF62E2)),
           shape = RectangleShape
       ) {
           Text(text = "Random Quiz")
       }

   }


}