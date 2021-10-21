package exercise.ch2_domainmodeling.ex2_typesafety

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TypeSafetyTest {
    @Test
    fun testMyFile() {
        val myFile = createMyFile()
        Assertions.assertFalse(myFile.title::class == String::class, "The type of `title` should be wrapped.")
        Assertions.assertFalse(myFile.content::class == String::class, "The type of `content` should be wrapped.")
        Assertions.assertFalse(myFile.extension::class == String::class, "The type of `extension` should be wrapped.")
        Assertions.assertFalse(myFile.name::class == String::class, "The type of `name` should be wrapped.")
    }
}