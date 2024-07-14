package basic.properties

fun main() {
    var baby : People = Baby("Bobby")
}
class Rectangle() {
    val width: Int = 100
    val height: Int = 50
    val area: Int // property type is optional since it can be inferred from the getter's return type
        get() = this.width * this.height
    val area2 get() = this.width * this.height

}

open class People(var name: String) {
    var age = 12
        private set  // so its not allow to set but get from other

    var age2 = -1
        set(value) {
            if (value > 0) {
                field = value
            }else {
                field = 100
            }
        }
    val age3: Int = 15
        get() = field + 15
}

class Baby (name : String) : People(name) {
    // we have age from "People"
    init {
        println("$name $age")
        age2 = -3
        println(age2)
        println(age3)
    }
}