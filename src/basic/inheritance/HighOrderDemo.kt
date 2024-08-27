package basic.inheritance

import java.util.function.Consumer

data class Alien2(var name:String, var points:Int)

fun main (args : Array<String>) {
    var values = listOf<Int>(2,3,5,5,2)

    for(i in values) {
        println(i)
    }
    values.forEach { println(it) }
//    values.forEach { t -> println(t) }
//    values.forEach(::println) // function reference

    // var even = values.filter {e -> e%2 ==0} // too long
    var even = values.filter{it%2==0}.forEach(::println) // better
    println()
    val doubleValues = values.filter{it%2==0}.map {it*2}.forEach(::println) // filter it and double it and print it
    doubleValues
}