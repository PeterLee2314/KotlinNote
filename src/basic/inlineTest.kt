package basic

fun foo(code: String.(Int) -> Unit){
    "test".code(5)
}

fun createHello(hello: (String) -> Unit) = {

}

fun main(){
    foo {
        println(this)
        println(it)
    }
}