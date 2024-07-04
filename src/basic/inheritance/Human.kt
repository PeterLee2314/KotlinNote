package basic.inheritance

abstract class Human (){
    abstract fun think()
    fun talk(){
        println("Talk")
    }
}

class MarsAlien() : Human() {
    override fun think() {
        println("MarsAlien think")
    }
}

class Alien() : Human() {
    override fun think() {
        println("Alien think")
    }
}
fun main(args : Array<String>) {
    var bob = Alien()
    bob.think()
    var bob2 : Human = Alien() // this == Human human = new Alien() , create reference of human and object of alien
    var bob3 = MarsAlien()
    bob3.think()
}