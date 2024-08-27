package basic.inheritance

interface Human2 {
    fun think()
}

fun main (args : Array<String>) {
    var programmer : Human2 = object : Human2
                            {
                                override fun think() {
                                    println("think")
                                }
                            }
}