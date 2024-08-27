package basic.pattern

class A {

    companion object {
        fun create() : A = A()
    }


    fun show() {
        println("Show")
    }
}

fun main(args : Array<String>) {
    var obj = A.create()

}