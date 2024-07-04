package basic.inheritance

interface A {
    fun show()
     fun greet() {
        println("hello")}
    fun dup() {
        println("A dup")
    }
}
interface B{
    fun display()
    fun dup() {
        println("B dup")
    }
}

class C : A, B {
    override fun show() {
        println("Show")
    }

    override fun display() {
        println("display")
    }

    override fun dup() {
        super<A>.dup()
        super<B>.dup()
        println("in Abc")
    }

}

fun main (args : Array<String>) {
    var obj = C()
    obj.greet()
    obj.dup()
}