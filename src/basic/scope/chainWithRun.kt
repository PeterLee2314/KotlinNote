package basic.scope
fun main(args : Array<String>) {
// data class Laptop(maker, model, system)
    val laptopA = Laptop("Dell", "XPS 13 9343c", "Windows 8.1")
    val laptopB = Laptop("Lenovo", "T420s", "Windows 7")
    val laptopC = Laptop("MSI", "GS65 Stealth", "Windows 10")
    printWindowsCodeName(laptopA)
    printWindowsCodeName(laptopB)
    printWindowsCodeName(laptopC)
}
class Laptop(var maker: String,var model: String, var system: String) {

}
fun printWindowsCodeName(laptop: Laptop?) {
    val codename = laptop?.run {
        // `this` is Laptop.
        // `this` can ignore when use fields and methods
        system.split(" ")    // <-- pass to next chain
    }?.run {
        // `this` is the split strings. a List<String>
        val result = when (this.last()) {
            "7" -> "Blackcomb"
            "8" -> "Milestone"
            "8.1" -> "Blue"
            "10" -> "Threshold"
            else -> "Windows 9"
        }
        result    //  <-- pass value back
    }

    println("${laptop?.system} codename is $codename")
}

