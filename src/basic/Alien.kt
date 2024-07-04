package basic



class Alien (var n : String){
    // val constant
    // var variable (specific type by colon : )
    val b = 5
    var name : String = n
    var skills : String? = null

    fun show() {
        println(skills)
    }

    constructor(age : Int = 12, n : String) : this(n) {

    }

}

// define outside function, let this function become part of Alien class
// Extension function
operator infix fun Alien.plus(a : Alien) : Alien {
    var newAlien = Alien("Bobby")
    newAlien.skills = this.skills + " " + a.skills
    return newAlien
}
