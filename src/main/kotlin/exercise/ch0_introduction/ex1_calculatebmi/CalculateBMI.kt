package exercise.ch0_introduction.ex1_calculatebmi

data class PeriodicBodyMeasurement(
    val height: Double,
    val weights: List<Double>
)

fun calculateBMI(height: Double, weight: Double): Double {
    TODO("Calculate the BMI by `height`(cm) and `weight`(kg). " +
            "Note: The BMI need to round to 2 decimal place.")
}

fun calculateBMIPeriod(periodicBodyRecord: PeriodicBodyMeasurement): List<Double> {
    TODO("Calculate the BMI list by a man's periodic body measurement record. " +
            "Note 1: The man's height isn't changed. " +
            "Note 2: `height`(cm), `weight`(kg), and the BMI need to round to 2 decimal place.")
}