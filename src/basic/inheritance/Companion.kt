package basic.inheritance

class Com
{
    companion object
    {
        @JvmStatic
        fun show()
        {
            println("Hello")
        }
    }
}


fun main (args : Array<String>) {
    Com.show()
}