package com.example.quizapp

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.example.quizapp.presentation.Navigation.MyApp
import org.junit.Rule
import org.junit.Test

class QuizAppTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun HomeScreenTest(){
        composeTestRule.setContent {
            MyApp()
        }
        composeTestRule.onNodeWithTag("Apache Kafka").assertTextEquals("Apache Kafka")
        composeTestRule.onNodeWithTag("Bash").assertTextEquals("Bash")
        composeTestRule.onNodeWithTag("Docker").assertTextEquals("Docker")
        composeTestRule.onNodeWithTag("Linux").assertTextEquals("Linux")
        composeTestRule.onNodeWithTag("Postgres").assertTextEquals("Postgres")
        composeTestRule.onNodeWithTag("React").assertTextEquals("React")
        composeTestRule.onNodeWithTag("Random Quiz").assertTextEquals("Random Quiz")
    }
    @Test
    fun ApacheKafkaButtonClickTest(){
        composeTestRule.setContent {
            MyApp()
        }
        composeTestRule.onNodeWithTag("Apache Kafka").assertTextEquals("Apache Kafka")
        composeTestRule.onNodeWithTag("Apache Kafka").performClick()

    }

    @Test
    fun BashButtonClickTest(){
        composeTestRule.setContent {
            MyApp()
        }
        composeTestRule.onNodeWithTag("Bash").assertTextEquals("Bash")
        composeTestRule.onNodeWithTag("Bash").performClick()

    }

    @Test
    fun DockerButtonClickTest(){
        composeTestRule.setContent {
            MyApp()
        }
        composeTestRule.onNodeWithTag("Docker").assertTextEquals("Docker")
        composeTestRule.onNodeWithTag("Docker").performClick()

    }

    @Test
    fun LinuxButtonClickTest(){
        composeTestRule.setContent {
            MyApp()
        }
        composeTestRule.onNodeWithTag("Linux").assertTextEquals("Linux")
        composeTestRule.onNodeWithTag("Linux").performClick()

    }

    @Test
    fun PostgresButtonClickTest(){
        composeTestRule.setContent {
            MyApp()
        }
        composeTestRule.onNodeWithTag("Postgres").assertTextEquals("Postgres")
        composeTestRule.onNodeWithTag("Postgres").performClick()

    }

    @Test
    fun ReactButtonClickTest(){
        composeTestRule.setContent {
            MyApp()
        }
        composeTestRule.onNodeWithTag("React").assertTextEquals("React")
        composeTestRule.onNodeWithTag("React").performClick()

    }

    @Test
    fun RandomButtonClickTest(){
        composeTestRule.setContent {
            MyApp()
        }
        composeTestRule.onNodeWithTag("Random Quiz").assertTextEquals("Random Quiz")
        composeTestRule.onNodeWithTag("Random Quiz").performClick()

    }
}