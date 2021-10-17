package ch1_functionalprogramming.ex1_sideeffect

import other.model.Fruit
import other.model.FruitColor
import other.model.FruitName
import other.model.WeightGram

class DBRepository() {
    fun save(fruit: Fruit): Nothing = throw Exception("Saving a fruit to database... This is a side effect!")
}

fun createFruit(fruitName: String, weightGram: Int, fruitColor: String): String {
    // TODO:
    //  Please find out the side effect and remove it.
    // *** TODO section START ***

    val fruit = Fruit(
        name = FruitName(fruitName),
        weight = WeightGram(weightGram),
        color = FruitColor.valueOf(fruitColor)
    )

    val repo = DBRepository()
//    repo.save(fruit)

    // *** TODO section END ***

    return "Successfully remove the side effect."
}
