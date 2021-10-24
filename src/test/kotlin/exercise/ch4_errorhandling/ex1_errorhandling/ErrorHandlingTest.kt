package exercise.ch4_errorhandling.ex1_errorhandling

import arrow.core.left
import arrow.core.right
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import other.model.SimpleFileStorage
import other.model.Tag

class ErrorHandlingTest {
    private val simpleFileStorage = SimpleFileStorage()

    @Test
    fun testFindFileByFileNameSafely() {
        val fileName1 = "Design Pattern.pdf"
        println(findFileByFileNameSafely(fileName1))
        Assertions.assertEquals(Error.FileNotFoundError("File [${fileName1}] is not found in storage.").left(), findFileByFileNameSafely(fileName1), "The exception should be wrapped to Either.Left(FileNotFoundError(...))")

        val fileName2 = "Domain Modeling Made Functional.pdf"
        val expectedFile = simpleFileStorage.findFileByFileName(fileName2)
        println(findFileByFileNameSafely(fileName2))
        Assertions.assertEquals(expectedFile.right(), findFileByFileNameSafely(fileName2), "The SimpleFile should be wrapped to Either.Right(SimpleFile(...))")
    }

    @Test
    fun testFindTagByFileNameSafely() {
        val fileName1 = "RGB.mp3"
        println(findTagByFileNameSafely(fileName1))
        Assertions.assertEquals(Error.NullTagError.left(), findTagByFileNameSafely(fileName1), "The null tag should be wrapped to Either.Left(NullTagError)")

        val fileName2 = "course_1005.mp4"
        val expectedTag = simpleFileStorage.findTagByFileName(fileName2)
        println(findTagByFileNameSafely(fileName2))
        Assertions.assertEquals(expectedTag.right(), findTagByFileNameSafely(fileName2), "The tag should be wrapped to Either.Right(...)")

        // [Advanced]
//        val fileName3 = "Design Pattern.pdf"
//        println(findTagByFileNameSafely(fileName3))
//        Assertions.assertEquals(Error.FileNotFoundError("File [${fileName3}] is not found in storage.").left(), findTagByFileNameSafely(fileName3), "The exception should be wrapped to Either.Left(FileNotFoundError(...))")
    }

    @Test
    fun testFindTagByFileNameSafelyWithDefaultTag() {
        val defaultTag = Tag.TYPE_C.name

        val fileName1 = "end of a life.mp3"
        println(findTagByFileNameSafely(fileName1, defaultTag))
        Assertions.assertEquals(defaultTag.right(), findTagByFileNameSafely(fileName1, defaultTag), "The null tag should be changed to [$defaultTag] and wrapped to Either.Right(...)")

        val fileName2 = "course_1005.mp4"
        val expectedTag2 = simpleFileStorage.findTagByFileName(fileName2)
        println(findTagByFileNameSafely(fileName2, defaultTag))
        Assertions.assertEquals(expectedTag2.right(), findTagByFileNameSafely(fileName2, defaultTag), "The tag should be wrapped to Either.Right(...)")

        // [Advanced]
//        val fileName3 = "Design Pattern.pdf"
//        println(findTagByFileNameSafely(fileName3, defaultTag))
//        Assertions.assertEquals(Error.FileNotFoundError("File [${fileName3}] is not found in storage.").left(), findTagByFileNameSafely(fileName3, defaultTag), "The exception should be wrapped to Either.Left(FileNotFoundError(...))")
    }
}