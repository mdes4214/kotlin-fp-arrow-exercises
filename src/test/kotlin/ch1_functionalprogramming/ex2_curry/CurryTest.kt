package ch1_functionalprogramming.ex2_curry

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CurryTest {
    @Test
    fun testCreateFruits() {
        val expectedApples = listOf(
            Fruit(name = "Apple", weight = 260, color = "RED"),
            Fruit(name = "Apple", weight = 300, color = "RED"),
            Fruit(name = "Apple", weight = 310, color = "RED")
        )
        val actualApples = createFruits("Apple", "RED", listOf(260, 300, 310))

        expectedApples.zip(actualApples) { expected, actual ->
            Assertions.assertEquals(expected, actual, "The fruit is different from expected.")
        }
    }
}