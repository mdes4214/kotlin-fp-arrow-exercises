package ch2_domainmodeling.ex1_producttype

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ProductTypeTest {
    @Test
    fun testFruit() {
        val apple = Fruit("Apple", 260, "RED")
        val orange = Fruit("Orange", 150, "ORANGE")

        Assertions.assertFalse(apple == orange, "The `equals()` function is not implemented correctly.")
        Assertions.assertNotEquals(apple.hashCode(), orange.hashCode(), "The `hashCode()` function is not implemented correctly.")
        Assertions.assertEquals("Fruit(name=Apple, weight=260, color=RED)", apple.toString(), "The `toString()` function is not implemented correctly.")
    }
}