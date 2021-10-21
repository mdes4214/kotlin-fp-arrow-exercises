package exercise.ch0_introduction.ex2_refactorcalculatebmi

@JvmInline
value class HeightCM(val value: Double)
@JvmInline
value class WeightKG(val value: Double)
@JvmInline
value class BMI2Decimal(val value: Double)

data class PeriodicBodyMeasurement(
    val height: HeightCM,
    val weights: List<WeightKG>
)

fun calculateBMI(height: HeightCM, weight: WeightKG): BMI2Decimal {
    TODO("Calculate the BMI by `height` and `weight`.")
}

fun calculateBMIPeriod(periodicBodyRecord: PeriodicBodyMeasurement): List<BMI2Decimal> {
    TODO("Calculate the BMI list by a man's periodic body measurement record. " +
            "Note: The man's height isn't changed.")
}
