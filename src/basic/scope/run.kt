package basic.scope

fun main(args : Array<String>) {
    val name = "Bob"
    run {
      val name = "Peter"
      println("call my name $name")
    }
    println("call my name $name")

    //also valid to execute method
    //can also store it on variable
    run {
        val telephone = Telephone()
        telephone.whoCallMe = "English"
        telephone    // <--  telephone 被帶到下一個 Chain
    }.callMe("Softest part of heart")    // <-- 這裡可以執行 `Telephone` Class 的方法
    // the above example , if store it, it will only store the telephone class too

    val bob = run {
        val telephone = Telephone()
        telephone.whoCallMe = "English"
        telephone.fromWhere = "Sagittarius"
        telephone    // <--  telephone 被帶到下一個 Chain
    }
    bob.callMe("Hello")


}
class Telephone() {
    var whoCallMe :String? = null;
    var fromWhere :String? = null;
    fun callMe(myName: String) : Unit{
        println("$whoCallMe ! Call me $myName !!");
    }
}
