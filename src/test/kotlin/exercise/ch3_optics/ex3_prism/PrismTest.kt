package exercise.ch3_optics.ex3_prism

import arrow.core.left
import arrow.core.right
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import other.model.CustomFileFormat
import other.model.DocumentFileExtension
import other.model.ImageFileExtension

class PrismTest {
    @Test
    fun testCustomFileFormatPrism() {
        val customFileFormat = CustomFileFormat.DocumentFile(extension = DocumentFileExtension(".doc"))
        val prism = documentFilePrism()

        val expectedXml = CustomFileFormat.DocumentFile(extension = DocumentFileExtension(".xml"))
        val actualXml = prism.modify(customFileFormat) { documentFile ->
            documentFile.copy(extension = DocumentFileExtension(".xml"))
        }
        Assertions.assertEquals(expectedXml, actualXml, "The file extension is not modified correctly.")

        val expectedPdf = CustomFileFormat.DocumentFile(extension = DocumentFileExtension(".pdf")).right()
        val actualPdf = prism.getOrModify(CustomFileFormat.DocumentFile(DocumentFileExtension(".pdf")))
        Assertions.assertEquals(expectedPdf, actualPdf, "The file extension is not modified correctly.")

        val expectedUndefined = CustomFileFormat.UndefinedFile.left()
        val actualUndefined = prism.getOrModify(CustomFileFormat.UndefinedFile)
        Assertions.assertEquals(expectedUndefined, actualUndefined, "The file extension is not modified correctly.")
    }

    @Test
    fun testUpdateDocumentFileExtension() {
        val customFileFormats = listOf(
            CustomFileFormat.DocumentFile(extension = DocumentFileExtension(".doc")),
            CustomFileFormat.MediaFile.ImageFile(extension = ImageFileExtension(".jpg"))
        )
        val expectedFormats = listOf(
            CustomFileFormat.DocumentFile(extension = DocumentFileExtension(".pdf")),
            CustomFileFormat.MediaFile.ImageFile(extension = ImageFileExtension(".jpg"))
        )

        val actualFormats = customFileFormats.map {
            it.updateDocumentFileExtension(DocumentFileExtension(".pdf"))
        }

        expectedFormats.zip(actualFormats) { expected, actual ->
            Assertions.assertEquals(expected, actual, "The file extension is not matched and modified correctly.")
        }
    }
}