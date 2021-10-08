package ch1_functionalprogramming.ex1_sideeffect

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class SideEffectTest {
    @Test
    fun testCreateFruit() {
        Assertions.assertEquals("Successfully remove the side effect.", createFruit("apple", 260, "RED"), "The side effect still exists.")
    }
}