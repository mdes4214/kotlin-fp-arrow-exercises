package exercise.ch6_resourcehandling.ex3_guaranteecase

import arrow.fx.coroutines.ExitCase
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class GuaranteeCaseTest {
    @Test
    fun testRunJobWithTasks() {
        val delayTime1 = 500L
        val actual1: List<ExitCase> = runBlocking { runJobWithTasks(delayTime1) }
        println(actual1)
        actual1.forEach { actualExitCase ->
            Assertions.assertTrue(actualExitCase is ExitCase.Cancelled, "The job should be cancelled.")
        }

        val delayTime2 = 1500L
        val actual2: List<ExitCase> = runBlocking { runJobWithTasks(delayTime2) }
        println(actual2)
        actual2.forEach { actualExitCase ->
            Assertions.assertTrue(actualExitCase is ExitCase.Completed, "The job should be completed.")
        }
    }
}