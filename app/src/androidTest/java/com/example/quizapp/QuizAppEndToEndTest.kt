package com.example.quizapp

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.navigation.compose.rememberNavController
import com.example.quizapp.Constants.TestTags
import com.example.quizapp.di.DiObject
import com.example.quizapp.presentation.Screens.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@HiltAndroidTest
@UninstallModules(DiObject::class)
class QuizAppEndToEndTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp(){
        hiltRule.inject()

    }

    @Test
    fun CheckIfHomeScreenHasAllButtons(){
        composeTestRule.onNodeWithTag("Apache Kafka").assertTextEquals("Apache Kafka")
        composeTestRule.onNodeWithTag("Bash").assertTextEquals("Bash")
        composeTestRule.onNodeWithTag("Docker").assertTextEquals("Docker")
        composeTestRule.onNodeWithTag("Linux").assertTextEquals("Linux")
        composeTestRule.onNodeWithTag("Postgres").assertTextEquals("Postgres")
        composeTestRule.onNodeWithTag("React").assertTextEquals("React")
        composeTestRule.onNodeWithTag("Random Quiz").assertTextEquals("Random Quiz")

    }

    @Test
    fun DockerCompleteTest(){
        composeTestRule.onNodeWithTag(TestTags.DOCKER).performClick()
        composeTestRule.onNodeWithTag(testTag = TestTags.DOCKERSCORE).assertExists()
        composeTestRule.onNodeWithTag(TestTags.DOCKERFIRSTQUESTION).performScrollTo().assertExists()

    }

    @Test
    fun ApacheKafkaScreentest(){
        composeTestRule.onNodeWithTag(TestTags.APACHE_KAFKA).performClick()
        composeTestRule.onNodeWithTag(testTag = TestTags.APACHE_KAFKA_SCORE).assertExists()
        composeTestRule.onNodeWithTag(TestTags.APACHE_KAFKA_SCROLL).performScrollTo().assertExists()

    }

    @Test
    fun BashScreenTest(){
        composeTestRule.onNodeWithTag(TestTags.BASH).performClick()
        composeTestRule.onNodeWithTag(testTag = TestTags.BASH_SCORE).assertExists()
        composeTestRule.onNodeWithTag(TestTags.BASH_SCROLL).performScrollTo().assertExists()
    }

    @Test
    fun PostgresScreenTest() {
        composeTestRule.onNodeWithTag(TestTags.POSTGRESQL).performClick()
        composeTestRule.onNodeWithTag(testTag = TestTags.POSTGRESQL_SCORE).assertExists()
        composeTestRule.onNodeWithTag(TestTags.POSTGRESQL_SCROLL).performScrollTo().assertExists()
    }

    @Test
    fun LinuxScreenTest() {
        composeTestRule.onNodeWithTag(TestTags.LINUX).performClick()
        composeTestRule.onNodeWithTag(testTag = TestTags.LINUX_SCORE).assertExists()
        composeTestRule.onNodeWithTag(TestTags.LINUX_SCROLL).performScrollTo().assertExists()
    }

    @Test
    fun ReactScreenTest() {
        composeTestRule.onNodeWithTag(TestTags.REACT).performClick()
        composeTestRule.onNodeWithTag(testTag = TestTags.REACT_SCORE).assertExists()
        composeTestRule.onNodeWithTag(TestTags.REACT_SCROLL).performScrollTo().assertExists()
        
    }



}