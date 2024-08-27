package basic.practice

fun main() {
    var t1 = test {
        false
    }

    var t2 = object : test {
        override fun predicate() : Boolean {
            return false
        }
    }

}
fun interface test {
    fun predicate() : Boolean
}
fun interface IntPredicate {
    fun accept(i: Int): Boolean
}