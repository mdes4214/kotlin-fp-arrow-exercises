package ch3_optics.ex6_iso

import arrow.core.Tuple4
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import other.model.*

class IsoTest {
    @Test
    fun testCustomFileIso() {
        val customFile = CustomFile(
            header = CustomHeader(CustomMetadata(Tag.TYPE_C, Title("Note A"), Author("Joe"))),
            content = Content("Note A Content"),
            fileFormat = CustomFileFormat.DocumentFile(DocumentFileExtension(".doc")),
            name = FileName("Note_A")
        )
        val expected = Tuple4(
            CustomHeader(CustomMetadata(Tag.TYPE_C, Title("Note A"), Author("Joe"))),
            Content("Note A Content"),
            CustomFileFormat.DocumentFile(DocumentFileExtension(".doc")),
            FileName("Note_A")
        )
        val iso = customFileIso()

        Assertions.assertEquals(expected, iso.get(customFile), "The Iso should be implemented.")

        Assertions.assertEquals(expected, customFile.toTuple4(), "The CustomFile should be converted to Tuple4.")

        Assertions.assertEquals(customFile, expected.toCustomFile(), "The Tuple4 should be converted to CustomFile.")
    }
}