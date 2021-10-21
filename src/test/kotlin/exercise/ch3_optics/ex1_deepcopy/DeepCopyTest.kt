package exercise.ch3_optics.ex1_deepcopy

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class DeepCopyTest {
    @Test
    fun testUpdateTag() {
        val docFile = MyFile(
            header = MyHeader(
                metadata = MyMetadata(
                    tag = Tag.TYPE_C,
                    title = Title("Functional Programming Learning Note"),
                    author = Author("Jack")
                )
            ),
            content = Content("This is a note for Functional Programming learning."),
            extension = FileExtension(".doc"),
            name = FileName("FP_note")
        )

        Assertions.assertEquals(Tag.TYPE_A, docFile.updateTag(Tag.TYPE_A).header.metadata.tag, "The tag is not updated correctly.")
    }
}