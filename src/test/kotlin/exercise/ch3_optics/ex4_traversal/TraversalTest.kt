package exercise.ch3_optics.ex4_traversal

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import other.model.*

class TraversalTest {
    @Test
    fun testUpdateAllTag() {
        val customFiles = CustomFiles(
            listOf(
                CustomFile(
                    header = CustomHeader(CustomMetadata(Tag.TYPE_C, Title("Note A"), Author("Joe"))),
                    content = Content("Note A Content"),
                    fileFormat = CustomFileFormat.DocumentFile(DocumentFileExtension(".doc")),
                    name = FileName("Note_A")
                ),
                CustomFile(
                    header = CustomHeader(CustomMetadata(Tag.TYPE_C, Title("Note B"), Author("Mark"))),
                    content = Content("Note B Content"),
                    fileFormat = CustomFileFormat.DocumentFile(DocumentFileExtension(".pdf")),
                    name = FileName("Note_B")
                ),
                CustomFile(
                    header = CustomHeader(CustomMetadata(Tag.TYPE_C, Title("Photo A"), Author("Sam"))),
                    content = Content("(Binary Encoded)"),
                    fileFormat = CustomFileFormat.MediaFile.ImageFile(ImageFileExtension(".jpg")),
                    name = FileName("Photo_A")
                )
            )
        )
        val actualFiles = updateAllTag(customFiles, Tag.TYPE_A)
        actualFiles.customFiles.forEach { actualFile ->
            Assertions.assertEquals(Tag.TYPE_A, actualFile.header.metadata.tag, "The tag is not updated correctly.")
        }
    }
}