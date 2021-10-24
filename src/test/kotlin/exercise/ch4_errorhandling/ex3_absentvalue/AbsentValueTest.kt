package exercise.ch4_errorhandling.ex3_absentvalue

import arrow.core.left
import arrow.core.none
import arrow.core.right
import arrow.core.some
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import other.model.Tag

class AbsentValueTest {
    @Test
    fun testFindInTagSafely() {
        val tag1 = ""
        println(findInTagSafely(tag1))
        Assertions.assertEquals(none<Tag>(), findInTagSafely(tag1), "The tag [$tag1] doesn't exist.")

        val tag2 = "TYPE_C"
        println(findInTagSafely(tag2))
        Assertions.assertEquals(Tag.valueOf(tag2).some(), findInTagSafely(tag2), "The tag [$tag2] exists.")
    }

    @Test
    fun testFindInTagSafelyWithError() {
        val tag1 = ""
        println(findInTagSafelyWithError(tag1))
        Assertions.assertEquals(Error.ValidationError.TagInvalidError.left(), findInTagSafelyWithError(tag1), "The tag [$tag1] is invalid.")

        val tag2 = "TYPE_C"
        println(findInTagSafelyWithError(tag2))
        Assertions.assertEquals(Tag.valueOf(tag2).right(), findInTagSafelyWithError(tag2), "The tag [$tag2] is valid.")
    }

    @Test
    fun testEvaluateOptionalTag() {
        val tag1 = ""
        println(evaluateOptionalTag(findInTagSafely(tag1)))
        Assertions.assertEquals("EMPTY", evaluateOptionalTag(findInTagSafely(tag1)), "The optional tag [$tag1] should be unwrapped to string [EMPTY].")

        val tag2 = "TYPE_C"
        println(evaluateOptionalTag(findInTagSafely(tag2)))
        Assertions.assertEquals(tag2, evaluateOptionalTag(findInTagSafely(tag2)), "The optional tag [$tag2] should be unwrapped to itself.")

    }
}