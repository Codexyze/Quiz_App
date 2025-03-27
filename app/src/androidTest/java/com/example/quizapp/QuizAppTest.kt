package com.example.quizapp

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.quizapp.Navigation.MyApp
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
    }
}