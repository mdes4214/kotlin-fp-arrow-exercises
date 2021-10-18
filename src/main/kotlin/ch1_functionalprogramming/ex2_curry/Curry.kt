package ch1_functionalprogramming.ex2_curry

data class Fruit(
    val name: String,
    val weight: Int,
    val color: String
)

// TODO:
//  Try to "curry" the `createFruit()` function! We need to create the same fruits with different weights several times.
// hint:
//  This test will pass initially, but we want to refactor the `createFruit()` function with curry.
//  Which means it will only be able to accept one argument at a time.
//  If is no idea how to do, can try these in sequence (note the order of arguments):
//  1. (String, String, Int) -> Fruit
//  2. (String) -> ((String, Int) -> Fruit)
//  3. (String) -> (String) -> ((Int) -> Fruit)
// *** TODO section START ***

fun createFruit(fruitName: String, fruitColor: String, fruitWeight: Int): Fruit =
    Fruit(name = fruitName, weight = fruitWeight, color = fruitColor)

fun createFruits(fruitName: String, fruitColor: String, fruitWeights: List<Int>): List<Fruit> =
    fruitWeights.map { fruitWeight ->
        createFruit(fruitName, fruitColor, fruitWeight)
    }

// *** TODO section END ***