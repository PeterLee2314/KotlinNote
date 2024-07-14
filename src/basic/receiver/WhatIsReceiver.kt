package basic.receiver

fun main(args : Array<String>) {
    val intToLong : Int.(String) -> Long = {
        println("Hello")
        toLong()
    }
    intToLong(5, "Hello")

    val sayHi : (Int) -> Long = { a ->
        a.toLong()
    }
    sayHi(5)
    // Higher Order Function (with a function required : (Int) -> Unit)
    fun highOrder(name : String) : (Int) -> Unit {
        // becaues the accept type of the function is (Int) and that parameter function return Unit

        // This part is the function details
        return {
            // by default the (Int) is "it"
            e ->
            println("Inner executed $e")
        }

    }
    highOrder("YO")(5)
    var printInner = highOrder("YO")
    printInner(6)


    // Normal Function (with type Unit)
    fun test(name: String) : Unit {
        println("Just a Normal Function by $name")
    }
    test("Bobby")


}
class Foo
class Bar

fun Foo.functionInFoo(): Unit = TODO()
fun Bar.functionInBar(): Unit = TODO()

inline fun higherOrderFunctionTakingFoo(body: (Foo).() -> Unit) = body(Foo())
inline fun higherOrderFunctionTakingBar(body: (Bar).() -> Unit) = body(Bar())

fun example() {
    higherOrderFunctionTakingFoo {
        higherOrderFunctionTakingBar {
            functionInFoo()
            functionInBar()
        }
    }
}
