package exercise.ch6_resourcehandling.ex1_bracketcase

import arrow.core.Either
import arrow.fx.coroutines.ExitCase
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import other.model.FileNotFoundException

class BracketCaseTest {
    @Test
    fun testLoadFileSafely() {
        val fileName1 = "FP_note.txt"
        var actual1: ExitCase = ExitCase.Completed
        val expected1: ExitCase = ExitCase.Failure(FileNotFoundException("File [${fileName1}] is not found in storage."))
        runBlocking {
            Either.catch {
                loadFileSafely(
                    fileName1,
                    { actual1 = it },
                    { actual1 = it },
                    { actual1 = it }
                )
            }
        }
        println(actual1)
        Assertions.assertEquals(expected1, actual1, "Should release resource with ExitCase.Failure.")

        val fileName2 = "end of a life.mp3"
        var actual2: ExitCase = ExitCase.Completed
        val expected2: ExitCase = ExitCase.Completed
        runBlocking {
            Either.catch {
                loadFileSafely(
                    fileName2,
                    { actual2 = it },
                    { actual2 = it },
                    { actual2 = it }
                )
            }
        }
        println(actual2)
        Assertions.assertEquals(expected2, actual2, "Should release resource with ExitCase.Completed.")
    }
}