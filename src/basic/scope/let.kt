package basic.scope

class TreasureBox {
    private val password = "password"
    private val treasure = "You've got a Windows install USB"
    fun open(key: String?): String {
        val result = key?.let {
            // `it` is the key String.
            // `this` is TreasureBox.

            var treasure = "error"
            if (it == password) {
                treasure = this.treasure
            }
            treasure     // <-- pass value back
        } ?: "error"

        return result
    }
    fun openWithKeyWord(key : String?): String{
        val result = key?.let{
            enteredPassword ->  // changed it to enteredPassword
            var treasure = "error"
            if (enteredPassword == password) {
                treasure = this.treasure
            }
            treasure
        } ?: "error"
        return result
    }
}

fun main(args : Array<String>) {

    val treasureBox = TreasureBox()
    println("Open the box , and ${treasureBox.open(null)}")
    println("Open the box , and ${treasureBox.open("admin")}")
    println("Open the box , and ${treasureBox.open("password")}")
    println("Open the box , and ${treasureBox.openWithKeyWord("password")}")
}