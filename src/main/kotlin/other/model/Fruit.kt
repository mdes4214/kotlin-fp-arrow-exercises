package other.model

@JvmInline value class FruitName(val value: String)
@JvmInline value class WeightGram(val value: Int)

enum class FruitColor {
    RED, YELLOW, ORANGE
}

data class Fruit(
    val name: FruitName,
    val weight: WeightGram,
    val color: FruitColor
)