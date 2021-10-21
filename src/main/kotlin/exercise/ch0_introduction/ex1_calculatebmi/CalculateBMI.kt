package exercise.ch0_introduction.ex1_calculatebmi

data class PeriodicBodyMeasurement(
    val height: Double,
    val weights: List<Double>
)

fun calculateBMI(height: Double, weight: Double): Double {
    val heightSquare = (height / 100) * (height / 100)
    val bmi = weight / heightSquare
    return "%.2f".format(bmi).toDouble()
}

fun calculateBMIPeriod(periodicBodyRecord: PeriodicBodyMeasurement): List<Double> {
    val bmis = mutableListOf<Double>()
    for (weight in periodicBodyRecord.weights) {
        bmis.add(calculateBMI(periodicBodyRecord.height, weight))
    }
    return bmis
}