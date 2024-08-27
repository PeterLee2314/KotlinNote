package basic
// Extension Function
fun main2() {
    // make a array list
    var arr = arrayOf(1,2,3,4,9,2,4,5,7,11) // the type is Array<Int>
    println(arr.findSecondLargest())
}
// custom find second large by Extension function
fun Array<Int>.findSecondLargest() : Int {
    // this refer to the Array object that put inside
    var largest : Int = this.first()
    var target : Int = this.first()
    for(i in this) {
        if(i > largest) {
            target = largest
            largest = i
        }else if(i > target) {
            target = i
        }
    }
    return target
}


open class Shape
class Rectangle: Shape()

fun Shape.getName() = "Shape"
fun Rectangle.getName2() = "Rectangle"

fun printClassName(s: Shape) {
    println(s.getName())
}

fun main() {
    printClassName(Rectangle()) // even though we pass Rectangle, and its child of Shape, because the accept type is s: Shape
    // which is Shape, so Shape.getName() executed
    println("Hello".toString(5))


}

val <T> List<T>.newSize: Int
    get() = size - 1

fun Any?.toString(space : Int): String {
    if (this == null) return "null"
    // After the null check, 'this' is autocast to a non-nullable type, so the toString() below
    // resolves to the member function of the Any class
    println("Space $space")
    return toString()
}