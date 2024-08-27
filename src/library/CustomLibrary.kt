package library
// we use the custom library here
class CustomLibrary {
}

fun main() {
    val result = calculate(1,2)
    println(result)
}
fun calculate(a: Int, b: Int) : Int{
    return a + b
}