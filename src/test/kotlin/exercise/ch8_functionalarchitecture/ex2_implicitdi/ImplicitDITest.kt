package exercise.ch8_functionalarchitecture.ex2_implicitdi

import arrow.core.Either
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import other.model.*

class ImplicitDITest {
    @Test
    fun testLoadFilesAndUpdateTagWithDependencies() {
        val expectedFiles = listOf(
            CustomFile(
                header = CustomHeader(CustomMetadata(Tag.values().toList().first(), Title("Domain Modeling Made Functional"), Author("Scott Wlaschin"))),
                content = Content("The useful knowledge of Domain Modeling and design mindset."),
                fileFormat = CustomFileFormat.DocumentFile(DocumentFileExtension(".pdf")),
                name = FileName("Domain Modeling Made Functional")
            ),
            CustomFile(
                header = CustomHeader(CustomMetadata(Tag.values().toList().first(), Title("end of a life"), Author("Calliope Mori"))),
                content = Content("A beautiful song."),
                fileFormat = CustomFileFormat.MediaFile.AudioFile(AudioFileExtension(".mp3"), BitRateKBitPerS(320)),
                name = FileName("end of a life")
            )
        )
        runBlocking {
            val actualFiles = loadFilesAndUpdateTagWithDependencies()
            println(actualFiles)
            Assertions.assertTrue(actualFiles is Either.Right, "Load files and update tag failed.")
            actualFiles.fold(
                ifLeft = {},
                ifRight = {
                    it.zip(expectedFiles) { actualFile, expectedFile ->
                        Assertions.assertEquals(expectedFile, actualFile, "Load files and update tag failed.")
                    }
                }
            )
        }
    }
}