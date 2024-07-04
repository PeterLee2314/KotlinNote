package basic

import java.util.*

fun main(args : Array<String>) {
    println("Hello world")
    var alien = Alien()
    alien.name = "Bob"
    println("Name is : ${alien.name}")

    var javaAlien = JavaAlien()
    javaAlien.name = "Bobby" // no need setter or getter
    println("Java basic.Alien Name is : ${javaAlien.name}")

    // if-else Expression in value
    var num1 : Int = 4
    var num2 : Int = 7
    var result : Int = 0;
    result = if(num1 > num2) num1 else num2

    // String
    var str1 : String = "Peter"
    var str2 : String = "peter"
    println("${str1.equals(str2)}") // false
    println("${str1.equals(str2,true)}") // true (match character ignore capital or small
    println("${str1 == str2}") // false
    var str3 : String = "Peter"
    println("${str1 == str3}") // true

    // ? (handle null)
    // var strNull : String = null // Null can not be a value of a non-null type String
    var strnotNull : String?

    // When
    val num : Int = 2
    when(num) {
        1 -> println("its one")
        2 -> println("its two")
        else -> println("other num")
    }

    // While, For
    var nums = 1..5
    for(a in nums step 2) {
        println(a) // 1 to 5
    }

    var numsList = listOf(1,2,3,4) // Collections

    for (i in numsList) {
        println(i)
    }
    for ((i,e) in numsList.withIndex()) {
        println("$i $e")
    }

    var aliens = TreeMap<String,Int>()
    aliens["Peter"] = 543
    aliens["Bobby"] = 123
    for ((name,age) in aliens) {

    }


}
fun peterSpeak(a : Int , b : Int): Int {
    return a + b
}
fun max(a : Int, b : Int) : Int = if(a > b) a else b

