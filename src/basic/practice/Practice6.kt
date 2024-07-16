package basic.practice

fun main() {
    var strs = listOf("A","B","C")
    var list : List<Any> = listOf<String>("A","B","C") // valid , because List<out T> , we want to extend the relationship of List

    var strs2 = mutableListOf("A","B","C")
    //var list2 : MutableList<Any> = strs2 // invalid because MutalbeList<T> list is changeable
    var a : Int
}
