package basic.practice.practice8

import kotlin.enums.enumEntries

fun main() {
    var list = listOf<Int>(1,2,3,4).filterSth<Double>()
    println(list)
}

enum class RGB { RED, GREEN, BLUE }

inline fun <reified T : Enum<T>> printAllValues() {
    println(enumValues<T>().joinToString { it.name })
}
// RED, GREEN, BLUE


inline fun <reified T> List<Any>.filterSth() : List<T> {
    return this.filter { it is T }.map { it as T }
}