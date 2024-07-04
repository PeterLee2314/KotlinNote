package basic

fun main(args : Array<String>){
   var finalAmt = calcAmount(50)
    println(finalAmt)
}
@JvmOverloads
fun calcAmount(amt: Int, interest : Double = 0.04): Int {
    return (amt + amt*interest).toInt()
}
