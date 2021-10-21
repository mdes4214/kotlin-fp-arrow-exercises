package exercise.ch0_introduction.ex2_refactorcalculatebmi

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class RefactorCalculateBMITest {
    @Test
    fun testCalculateBMI() {
        Assertions.assertEquals(BMI2Decimal(20.76), calculateBMI(HeightCM(170.0), WeightKG(60.0)), "The BMI is incorrect.")
    }

    @Test
    fun testCalculateBMIPeriod() {
        val periodicBodyRecord = PeriodicBodyMeasurement(
            height = HeightCM(170.0),
            weights = listOf(WeightKG(60.0), WeightKG(59.7), WeightKG(59.2), WeightKG(60.5))
        )
        val bmis = listOf(BMI2Decimal(20.76), BMI2Decimal(20.66), BMI2Decimal(20.48), BMI2Decimal(20.93))
        bmis.zip(calculateBMIPeriod(periodicBodyRecord)).forEachIndexed { idx, (expected, actual) ->
            Assertions.assertEquals(expected, actual, "The BMI is incorrect. (${idx + 1}/${bmis.size})")
        }
    }
}