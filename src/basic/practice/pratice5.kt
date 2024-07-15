package basic.practice

fun main() {
    var obj = Human()
    obj.addComputer(Computer { println( "Computer") })
    obj.computer.open()
}
class Human() {
    lateinit var computer : Computer
    fun addComputer(computer : Computer) {
        this.computer = computer
    }

}

fun interface Computer {
    fun open() : Unit
}