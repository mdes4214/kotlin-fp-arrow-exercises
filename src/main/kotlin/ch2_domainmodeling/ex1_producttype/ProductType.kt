package ch2_domainmodeling.ex1_producttype

// TODO:
//  Try to use `data class` instead of create a `class` with some basic functions (`equals()`, `hashCode()`, `toString()`).
// hint:
//  This test will pass initially, but we want to simplify this class.
// *** TODO section START ***

class Fruit(
    val name: String,
    val weight: Int,
    val color: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Fruit

        if (name != other.name) return false
        if (weight != other.weight) return false
        if (color != other.color) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + weight
        result = 31 * result + color.hashCode()
        return result
    }

    override fun toString(): String {
        return "Fruit(name=$name, weight=$weight, color=$color)"
    }
}

fun Fruit.updateWeight(newWeight: Int): Fruit =
    TODO("Update the weight of Fruit and return a new instance.")

// *** TODO section END ***