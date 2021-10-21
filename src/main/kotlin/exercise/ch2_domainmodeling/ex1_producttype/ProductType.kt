package exercise.ch2_domainmodeling.ex1_producttype

// TODO:
//  Try to use `data class` instead of create a `class` with some basic functions (`equals()`, `hashCode()`, `toString()`).
// hint:
//  This test will pass initially, but we want to simplify this class.
// *** TODO section START ***

data class Fruit(
    val name: String,
    val weight: Int,
    val color: String
)

fun Fruit.updateWeight(newWeight: Int): Fruit =
    this.copy(weight = newWeight)

fun Fruit.updateWeight(newWeight: Int): Fruit =
    TODO("Update the weight of Fruit and return a new instance.")

// *** TODO section END ***