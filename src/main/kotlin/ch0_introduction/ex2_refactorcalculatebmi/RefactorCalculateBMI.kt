package ch0_introduction.ex2_refactorcalculatebmi

import arrow.core.curried

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

fun Double.round(decimal: Int): Double = "%.${decimal}f".format(this).toDouble()

fun heightMeterSquare(height: HeightCM): Double = (height.value / 100) * (height.value / 100)

val calculateBMI: (HeightCM, WeightKG) -> BMI2Decimal = { height, weight ->
    BMI2Decimal((weight.value / (heightMeterSquare(height))).round(2))
}

//val calculateBMICurried: (HeightCM) -> ((WeightKG) -> BMI2Decimal) = { height ->
//    { weight ->
//        calculateBMI(height, weight)
//    }
//}
val calculateBMICurried = calculateBMI.curried()

fun calculateBMIPeriod(periodicBodyRecord: PeriodicBodyMeasurement): List<BMI2Decimal> {
    val calculateBMIByWeight: (WeightKG) -> BMI2Decimal = calculateBMICurried(periodicBodyRecord.height)
    return periodicBodyRecord.weights.map { weight ->
        calculateBMIByWeight(weight)
    }
}
