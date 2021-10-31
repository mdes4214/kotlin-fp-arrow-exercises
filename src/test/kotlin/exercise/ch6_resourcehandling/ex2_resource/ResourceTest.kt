package exercise.ch6_resourcehandling.ex2_resource

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import arrow.fx.coroutines.ExitCase
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import other.model.FileNotFoundException
import other.model.SimpleFile

class ResourceTest {
    @Test
    fun testLoadFilesSafely() {
        val fileNames1 = listOf("end of a life.mp3", "FP_note.txt")
        var actualExitCase1: ExitCase = ExitCase.Completed
        val expectedExitCase1: ExitCase = ExitCase.Failure(FileNotFoundException("File [FP_note.txt] is not found in storage."))
        val actual1: Either<Throwable, List<SimpleFile>> = runBlocking {
            Either.catch {
                loadFilesSafely(
                    fileNames1,
                    { actualExitCase1 = it },
                    { actualExitCase1 = it },
                    { actualExitCase1 = it }
                )
            }
        }
        val expected1: Either<Throwable, List<SimpleFile>> = FileNotFoundException("File [FP_note.txt] is not found in storage.").left()
        println(actual1)
        Assertions.assertEquals(expected1, actual1, "Should get an exception wrapped in Either.")
        println(actualExitCase1)
        Assertions.assertEquals(expectedExitCase1, actualExitCase1, "Should release resource with ExitCase.Failure.")

        val fileNames2 = listOf("end of a life.mp3", "Domain Modeling Made Functional.pdf")
        var actualExitCase2: ExitCase = ExitCase.Completed
        val expectedExitCase2: ExitCase = ExitCase.Completed
        val actual2: Either<Throwable, List<SimpleFile>> = runBlocking {
            Either.catch {
                loadFilesSafely(
                    fileNames2,
                    { actualExitCase2 = it },
                    { actualExitCase2 = it },
                    { actualExitCase2 = it }
                )
            }
        }
        val expected2: Either<Throwable, List<SimpleFile>> = listOf(
            SimpleFile(name = "end of a life", extension = ".mp3", author = "Calliope Mori", tag = null),
            SimpleFile(name = "Domain Modeling Made Functional", extension = ".pdf", author = "Scott Wlaschin", tag = null)
        ).right()
        println(actual2)
        Assertions.assertEquals(expected2, actual2, "Should get a list of SimpleFile wrapped in Either.")
        println(actualExitCase2)
        Assertions.assertEquals(expectedExitCase2, actualExitCase2, "Should release resource with ExitCase.Completed.")
    }
}