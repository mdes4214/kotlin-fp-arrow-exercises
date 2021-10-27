package exercise.ch4_errorhandling.ex5_computationblock

import arrow.core.left
import arrow.core.nonEmptyListOf
import arrow.core.right
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import other.model.Author
import other.model.FileName
import other.model.Tag

class ComputationBlockTest {
    @Test
    fun testCreateSimpleFileSafely() {
        val fileName1 = "Domain Modeling Made Functional"
        val fileExtension1 = ".pdf"
        val defaultTag = Tag.TYPE_C
        val expected1 = SafeSimpleFile(
            FileName(fileName1),
            FileExtension(fileExtension1),
            Author("Scott Wlaschin"),
            defaultTag
        )
        println(createSafeSimpleFile(fileName1, fileExtension1, defaultTag))
        Assertions.assertEquals(expected1.right(), createSafeSimpleFile(fileName1, fileExtension1, defaultTag), "The file [$fileName1$fileExtension1] should be found and update to [TYPE_C].")

        val fileName2 = "Domain Modeling Made Functional"
        val fileExtension2 = ""
        println(createSafeSimpleFile(fileName2, fileExtension2, defaultTag))
        Assertions.assertEquals(Error.InvalidInputFieldsError(nel = nonEmptyListOf(Error.ValidationError.ExtensionInvalidError)).left(), createSafeSimpleFile(fileName2, fileExtension2, defaultTag), "File [$fileName2$fileExtension2] is invalid.")

        val fileName3 = "Design Pattern"
        val fileExtension3 = ".pdf"
        println(createSafeSimpleFile(fileName3, fileExtension3, defaultTag))
        Assertions.assertEquals(Error.FileNotFoundError(msg = "File [$fileName3$fileExtension3] is not found in storage.").left(), createSafeSimpleFile(fileName3, fileExtension3, defaultTag), "File [$fileName3$fileExtension3] not found.")
    }
}