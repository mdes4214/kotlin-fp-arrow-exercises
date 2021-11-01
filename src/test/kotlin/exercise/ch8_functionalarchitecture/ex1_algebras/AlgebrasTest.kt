package exercise.ch8_functionalarchitecture.ex1_algebras

import arrow.core.Either
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import other.model.FileName
import other.model.Tag

class AlgebrasTest {
    @Test
    fun testAlgebras() {
        val fileName = FileName("Note_A")
        val newTag = Tag.TYPE_A
        runBlocking {
            val actualFile = updateOnlineFileTag(fileName, newTag)
            println(actualFile)
            Assertions.assertTrue(actualFile is Either.Right, "Update online file's tag failed.")
            val actualTag = actualFile.fold(
                ifLeft = { Tag.TYPE_C },
                ifRight = { it.header.metadata.tag }
            )
            Assertions.assertEquals(newTag, actualTag, "The updated file's tag should be TYPE_A.")
        }
    }
}