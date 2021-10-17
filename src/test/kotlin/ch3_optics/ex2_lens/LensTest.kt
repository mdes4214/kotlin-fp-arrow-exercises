package ch3_optics.ex2_lens

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import other.model.Author
import other.model.CustomMetadata
import other.model.Tag
import other.model.Title

class LensTest {
    @Test
    fun tesCustomMetadataTagLens() {
        val customMetadata = CustomMetadata(
            tag = Tag.TYPE_C,
            title = Title("Functional Programming in Kotlin"),
            author = Author("Joe")
        )
        val lens = customMetadataTagLens()

        val expected = customMetadata.copy(tag = Tag.TYPE_B)

        Assertions.assertEquals(expected, lens.modify(customMetadata) { Tag.TYPE_B }, "The tag is not updated correctly.")
    }

    @Test
    fun testUpdateTag() {
        val customMetadata = CustomMetadata(
            tag = Tag.TYPE_C,
            title = Title("Functional Programming in Kotlin"),
            author = Author("Joe")
        )
        val expected = customMetadata.copy(tag = Tag.TYPE_A)

        Assertions.assertEquals(expected, customMetadata.updateTag(Tag.TYPE_A), "The tag is not updated correctly.")
    }
}