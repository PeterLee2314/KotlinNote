package basic.functionParameter

fun main(args : Array<String>) {
    val sayHi : (Int) -> Unit = { a ->
        println(a)
    }
    compare(2,3,sayHi)


    compare(2,3) {
        // override sayHello
        println("answer is $it")
    }

}
fun compare(a : Int, b: Int, sayHello : (Int) -> Unit) {
    sayHello(a+b)
}

fun doSomething(cal : base.() -> Unit) {

}

class base {

}
