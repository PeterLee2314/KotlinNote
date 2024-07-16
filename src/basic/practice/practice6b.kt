package basic.practice

interface Producer<out T> {
    fun produce(): T
}

class StringProducer : Producer<String> {
    override fun produce(): String = "Hello1"

}

class AnyProducer : Producer<Any> {
    override fun produce(): Any = "Hello2"
}

fun main() {

    val secondStringProducer = StringProducer()
    val secondAnyProducer = secondStringProducer
    val p : Producer<Any> = secondStringProducer // only when "out" then work
    println(secondStringProducer.produce())
    println(secondAnyProducer.produce())

}
