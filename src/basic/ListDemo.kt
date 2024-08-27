package basic

fun main(args : Array<String>)
{
    var values : MutableList<Int> = mutableListOf<Int>(8,9,4,2)
    var values2 : Collection<Int> = listOf<Int>(8,9,4,2)
    var values3 : Iterable<Int> = listOf<Int>(8,9,4,2)
    var values4 : Any = listOf<Int>(8,9,4,2)

    println(values.get(0)) // get index 0
    println(values.indexOf(9)) // where is 9 located
    values.add(17)
    values.forEach { e -> println(e) }

    var book1 = Books()
    var book2 = Books("Alie")
    var book3 = Books("Cookie")

    var bookList = mutableListOf<Books>(book1,book2,book3,Books("Monster"))
    for(book in bookList) {
        println(book)
    }

}

data class Books (var n : String = "no name"){
}