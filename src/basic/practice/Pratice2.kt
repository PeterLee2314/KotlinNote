package basic.practice

fun main() {
    // make a function that anonymous function
    temp(6, func = {fir ->
        println(fir)
    })

    temp(6) {
        fir ->
        println(fir)
    }
    val sayHi : (Int) -> Unit = { a ->
        println(a)
    }
    temp(6, func = sayHi) // work


    temp2("Hello", func = fun(s: String): Int { return s.toIntOrNull() ?: 0 })
    temp2("Hello",  fun(s: String): Int { return s.toIntOrNull() ?: 0 })

    temp2("Hello") {
        s : String ->
        s.toIntOrNull() ?: 0  // with ?: 0 , it will know the type is Int
    }
    val forTemp2 : (String) -> Int = {
        println(it)
        15
    }

    temp2("Hello", forTemp2)
    temp2("Hello", func=forTemp2)


}

fun temp(number : Int, func : (first:Int) -> Unit) : Unit {
    func(number)
}
fun temp2(str : String, func : (first:String) -> Int) : Unit {
    func(str)
}


