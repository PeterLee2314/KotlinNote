package basic.inheritance

data class Laptop (val brand : String, val price : Int) {
    fun show() {
        println("Awesome ")
    }
}

data class Book (var name : String, var price : Int) {

}

object BookShelf{
    var books = arrayListOf<Book>()

    fun showBooks() {
        for(i in books) {
            println(i)
        }
    }
}

fun main(args : Array<String>) {
    BookShelf.books.add(Book("Java",50))
    BookShelf.books.add(Book("Java",80))
    BookShelf.books.add(Book("Kotlin",450))

    BookShelf.showBooks()
}
fun main2(args : Array<String>) {
    var laptop1 = Laptop("Acer", 1200)
    var laptop2 = Laptop("Acer", 2200)
    var laptop3 = laptop1.copy(price=2300) // change the price variable value
    println(laptop1) // auto toString(), by data class
    println("Is same product ${laptop1.equals(laptop2)}")
    println("Is same ${laptop1.equals(laptop3)}")

}