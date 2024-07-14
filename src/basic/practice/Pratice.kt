package basic.practice

fun main() {

    // try to make a val function
    // Lambda expression
    val num : (Int) -> Unit = {
         // parameter : (Int)
        // can use "it"
        println(it)
     }
    // originally
    val num2: (Int) -> Unit = fun(value : Int) {
        println(value)
    }
    // OR
    val num3: (Int) -> Unit = {
        value ->
        println(value)
    }

    num(7)
    num2(8)
    fun getNum(num1 : Int) : Unit {
        println(num1)
    }
    getNum(7)

    val num4: (Int).(Int) -> Unit = {
        println(this)
        println(it)
    }

    num4(123,1234)



    // put val function in other function, so its a modules

    // High Order Function (use Function as parameter)
    fun getCustomNumBehaviour(baseValue : Int ,customBehaviour : (Int) -> Unit) {
        customBehaviour(baseValue)
    }
    getCustomNumBehaviour(12,num) // directly print it (now print it with baseValue)

    // return new Function
    fun getNewBehaviour(baseValue: Int, customBehaviour : (Int) -> Unit) : (Int) -> Unit {
        // this is inside getNewBehaviour function, thats why we need return {} to make it valid
        return {customBehaviour(99)}
    }

    fun getNewBehaviour2(baseValue: Int, customBehaviour : (Int) -> Unit) : (Int) -> Unit = { // before { should have fun(i : Int) {  implicitly
        // we need a return type that is accept (Int) and return nothing

        // this customBehaviour is match but why wrong? , because after execute (its Unit)(
        customBehaviour(999)
    }

    val bothType : StringAndInt = {
        a,b->
        println("string:$a int:$b")
    }

    val bothType2 : (String,Int) -> Unit = {
        a,b ->
        println("string:$a int:$b")
    }

}

typealias StringAndInt = (String, Int) -> Unit