package exercise.ch4_errorhandling.ex2_erroraccumulation

import arrow.core.Validated
import arrow.core.invalid
import arrow.core.nonEmptyListOf
import arrow.core.valid
import org.apache.commons.lang3.StringUtils
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import other.model.SimpleFile

class ErrorAccumulationTest {
    @Test
    fun testValidateName() {
        val name1 = ""
        println(validateName(name1))
        Assertions.assertTrue(validateName(name1) is Validated.Invalid, "The name [$name1] should be invalid.")

        val name2 = "Domain Modeling Made Functional"
        println(validateName(name2))
        Assertions.assertTrue(validateName(name2) is Validated.Valid, "The name [$name2] should be valid.")

        val name3 = " "
        println(validateName(name3))
        Assertions.assertTrue(validateName(name3) is Validated.Invalid, "The name [$name3] should be invalid.")

        val name4 = "end of a life"
        println(validateName(name4))
        Assertions.assertTrue(validateName(name4) is Validated.Valid, "The name [$name4] should be valid.")
    }

    @Test
    fun testValidateInputFields() {
        val file1 = SimpleFile(name = "Domain Modeling Made Functional", extension = ".pdf", author = "Scott Wlaschin123", tag = null)
        val expected1 = nonEmptyListOf(Error.ValidationError.AuthorInvalidError, Error.ValidationError.TagInvalidError).invalid()
        println(validateInputFields(file1.name, file1.extension, file1.author, StringUtils.defaultString(file1.tag)))
        Assertions.assertEquals(expected1, validateInputFields(file1.name, file1.extension, file1.author, StringUtils.defaultString(file1.tag)), "The invalid error should be accumulated.")

        val file2 = SimpleFile(name = "screenshot_2021_10_23", extension = ".png", author = "SYSTEM", tag = "SYSTEM")
        val expected2 = nonEmptyListOf(Error.ValidationError.TagInvalidError).invalid()
        println(validateInputFields(file2.name, file2.extension, file2.author, StringUtils.defaultString(file2.tag)))
        Assertions.assertEquals(expected2, validateInputFields(file2.name, file2.extension, file2.author, StringUtils.defaultString(file2.tag)), "The invalid error should be accumulated.")

        val file3 = SimpleFile(name = "", extension = "mp4", author = "Joe", tag = "TYPE_B")
        val expected3 = nonEmptyListOf(Error.ValidationError.NameInvalidError, Error.ValidationError.ExtensionInvalidError).invalid()
        println(validateInputFields(file3.name, file3.extension, file3.author, StringUtils.defaultString(file3.tag)))
        Assertions.assertEquals(expected3, validateInputFields(file3.name, file3.extension, file3.author, StringUtils.defaultString(file3.tag)), "The invalid error should be accumulated.")

        val file4 = SimpleFile(name = "end of a life", extension = ".mp3", author = "Gawr Gura?", tag = null)
        val expected4 = nonEmptyListOf(Error.ValidationError.AuthorInvalidError, Error.ValidationError.TagInvalidError).invalid()
        println(validateInputFields(file4.name, file4.extension, file4.author, StringUtils.defaultString(file4.tag)))
        Assertions.assertEquals(expected4, validateInputFields(file4.name, file4.extension, file4.author, StringUtils.defaultString(file4.tag)), "The invalid error should be accumulated.")

        val file5 = SimpleFile(name = "RGB", extension = ".mp3", author = "YOASOBI", tag = "TYPE_C")
        val expected5 = file5.valid()
        println(validateInputFields(file5.name, file5.extension, file5.author, StringUtils.defaultString(file5.tag)))
        Assertions.assertEquals(expected5, validateInputFields(file5.name, file5.extension, file5.author, StringUtils.defaultString(file5.tag)), "This file is valid.")
    }

    @Test
    fun testEvaluateValidatedName() {
        val name1 = ""
        println(evaluateValidatedName(validateName(name1)))
        Assertions.assertTrue(evaluateValidatedName(validateName(name1)).contains("NameInvalidError"), "The validated name [$name1] should be invalid and unwrapped.")

        val name2 = "end of a life"
        println(evaluateValidatedName(validateName(name2)))
        Assertions.assertEquals(name2, evaluateValidatedName(validateName(name2)), "The validated name [$name2] should be valid and unwrapped to itself.")
    }
}