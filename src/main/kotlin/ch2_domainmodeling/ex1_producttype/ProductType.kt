package ch2_domainmodeling.ex1_producttype

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

// *** TODO section END ***