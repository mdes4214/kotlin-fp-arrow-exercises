val squareSum: (Int) -> ((Int) -> Int) = { x -> { y -> x*x + y*y }}
// x -> (y -> x^2 + y^2)
val stillSquareSum: (Int) -> (Int) -> Int = { x -> { y -> x*x + y*y }}
// x -> y -> x^2 + y^2

val squareSum5: (Int) -> Int = squareSum(5)
// x = 5, thus return (y -> 5^2 + y^2)

fun main() {
    println(squareSum5(10))
    // y = 10, then get 5^2 + 10^2 = 125
}