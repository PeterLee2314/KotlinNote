package basic.practice

fun main() {

    var pObj = P()
    pObj.test {
        println("Hello Its pObj making the lambda expression")  // pass the func to B , not yet execute
    }

    // Output
    /*
    I am created inside execute() from B  , because B<T> extend C<T>(), so its first go to parent by default and trigger "init"
    Execute at B // Now we back to class B, and trigger "init"
    Hello Its pObj making the lambda expression
    I am created inside execute() from B // again we create C<T>() so trigger "init" again

     */
}

class P {
    public fun <T> test(func : () -> T) : C<T> = B(func)  // B(func)'s func come from Custom lambda
}
class B<T>(var func: () -> T) : C<T>(), D<T> {
// B<out T> so its a class with type T
    init {
        execute()
    }
    private fun execute(){
        println("Execute at B")
        func()
        // create a C<T>
        C<T>()
    }


}

open class C<out T> {
    init{
        println("I am created inside execute() from B")
    }
}

interface D<out T> {

}
// if class  C<T>() , if interface C<T>

