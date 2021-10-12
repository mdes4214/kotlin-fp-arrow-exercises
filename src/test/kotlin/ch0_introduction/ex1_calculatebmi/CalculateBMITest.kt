package ch0_introduction.ex1_calculatebmi

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CalculateBMITest {
    @Test
    fun testCalculateBMI() {
        Assertions.assertEquals(20.76, calculateBMI(170.0, 60.0), "The BMI is incorrect.")
    }

    @Test
    fun testCalculateBMIPeriod() {
        val periodicBodyRecord = PeriodicBodyMeasurement(
            height = 170.0,
            weights = listOf(60.0, 59.7, 59.2, 60.5)
        )
        val bmis = listOf(20.76, 20.66, 20.48, 20.93)
        bmis.zip(calculateBMIPeriod(periodicBodyRecord)).forEachIndexed { idx, (expected, actual) ->
            Assertions.assertEquals(expected, actual, "The BMI is incorrect. (${idx + 1}/${bmis.size})")
        }
    }
}