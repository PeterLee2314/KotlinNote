package basic.practice

interface MyInterface {
    fun bar()
    fun foo() = "foo"
}

@JvmInline
value class MyInterfaceWrapper(val myInterface: MyInterface) : MyInterface by myInterface

fun main2() {
    val my = MyInterfaceWrapper(object : MyInterface {
        override fun bar() {
            // body
        }
    })
    println(my.foo()) // prints "foo"
}

fun performOperation(x: Int, operation: (Int) -> Int) {
    val result = operation(x)
    println("Result: $result")
}

fun main() {
    performOperation(5) { number ->
        number * 2
    }

    performOperation(5, operation = {
        number ->
        number * 2
    })
}
