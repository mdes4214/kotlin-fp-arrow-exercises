package exercise.ch4_errorhandling.ex4_traverse

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import other.model.SimpleFile

class TraverseTest {
    @Test
    fun testEvaluateFilesByFileName() {
        val fileNames1 = listOf(
            "Design Pattern.pdf",
            "Domain Modeling Made Functional.pdf"
        )
        println(evaluateFilesByFileName(fileNames1))
        Assertions.assertEquals(Error.FileNotFoundError(msg = "File [Design Pattern.pdf] is not found in storage.").toString(), evaluateFilesByFileName(fileNames1), "If one of list causes error, the result will be error.")

        val fileNames2 = listOf(
            "end of a life.mp3",
            "Domain Modeling Made Functional.pdf"
        )
        val expected = listOf(
            SimpleFile(name = "end of a life", extension = ".mp3", author = "Calliope Mori", tag = null),
            SimpleFile(name = "Domain Modeling Made Functional", extension = ".pdf", author = "Scott Wlaschin", tag = null)
        )
        println(evaluateFilesByFileName(fileNames2))
        Assertions.assertEquals(expected.toString(), evaluateFilesByFileName(fileNames2), "All the files are found.")
    }
}