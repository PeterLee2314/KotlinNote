#### All classes' parent
Any ,  Every Kotlin class has Any as a superclass.
and have function like , equals, hashCode, toString

#### Operator
operator in general is required whenever you wish to be able to use a function as if it were an operator, 
since operator usages are simply compiled to function calls (except on primitive types, etc.)
That is, foo += bar, for example, is equivalent to foo.plusAssign(bar), foo[bar] = baz is equivalent to foo.set(bar, baz), etc .
```
data class Point(val x: Int, val y: Int) {
    operator fun plus(other: Point): Point {
        return Point(x + other.x, y + other.y)
    }
}

fun main() {
    val point1 = Point(1, 2)
    val point2 = Point(3, 4)
    val sum = point1 + point2

    println("Sum of points: (${sum.x}, ${sum.y})")
}
```
Sum of points: (4, 6)

#### Bitwise operations
val x = (1 shl 2) and 0x000FF000

shl(bits) – signed shift left

shr(bits) – signed shift right

ushr(bits) – unsigned shift right

and(bits) – bitwise AND

or(bits) – bitwise OR

xor(bits) – bitwise XOR

inv() – bitwise inversion

#### Floating-point comparsion
the behavior is different for operands that are not statically typed as floating-point numbers. 
For example, Any, Comparable<...>, or Collection<T> types. 
In this case, the operations use the equals and compareTo implementations for Float and Double
So println(listOf(Double.NaN) == listOf(Double.NaN)) // true
while println(Double.NaN == Double.NaN)                 // false
// Operand statically typed as floating-point number
println(0.0 == -0.0)                              // true
// Operand NOT statically typed as floating-point number
// So -0.0 is less than 0.0
println(listOf(0.0) == listOf(-0.0))              // false

For example, Any, Comparable<...>, or Collection<T> types :
- NaN is considered equal to itself
- NaN is considered greater than any other element including POSITIVE_INFINITY
- -0.0 is considered less than 0.0

#### Arrays
functions, such as arrayOf(), arrayOfNulls() or emptyArray()
to print it use , joinToString()
- val simpleArray = arrayOf(1, 2, 3)
- val nullArray: Array<Int?> = arrayOfNulls(3)
- var exampleArray = emptyArray<String>()
- var exampleArray: Array<String> = emptyArray()
- val initArray = Array<Int>(3) { 0 } // [0,0,0]
- val asc = Array(5) { i -> (i * i).toString() }
  - Creates an Array<String> with values ["0", "1", "4", "9", "16"]
##### Nested Arrays
to print it use, contentDeepToString()
val twoDArray = Array(2) { Array<Int>(2) { 0 } }
val threeDArray = Array(3) { Array(3) { Array<Int>(3) { 0 } } }
Arrays in Kotlin are invariant. This means that Kotlin doesn't allow you to assign an Array<String> to an Array<Any> 
to prevent a possible runtime failure. Instead, you can use Array<out Any>


#### lateinit (only work with class type, not primitive)
lateinit var favouriteMovie : String
initalize the varaible late
Why lateinit? because it will throw null pointer exception at runtime not compile time
eg press a button and suddenly crash because of null pointer exception


#### run, let, with, also, apply (Scope Function) T.run, T.let, T.also, T.apply , No T.with
Scope Function -> last line as return xxx (implicitly) or pass as next chain
run : 想像成一個獨立出來的 Scope ， run 會把最後一行的東西回傳或是帶到下一個 chain
with: 一般常常作為初始化時使用  with(T) 之中的傳入值可以以 this (稱作 identifier) 在 scope 中取用，不用打出 this也沒關係

但很多使用狀況變數可能是可為空的變數，如此一來 with的 scope 中就必須要宣告 「?」或「!!」來取用該物件的方法 (Method)
T.run : T.run 也能像 with 一樣來做初始化，而且 extension function 有個好處是可以在使用時就進行 「?」 或 「!!」 的宣告 。
另外，T 能夠以 this 的形式在 scope 內取用。
但實作上 T.run 有可能需要取用外層變數或方法，但 this 已經被變數 T 佔用。例如，取得 Activity 則要這樣處理：
```
Because we want to attach the current MainActivity Class
presenter?.run {
    attachView(this@MainActivity)
    addLifecycleOwner(this@MainActivity)
}
```
有沒有其他的做法，可以讓 identifier 不是 this呢？ -> use "let"

T.let / let : 又或者可以寫成 T.let，也是一個 extension function。T 在 scope 內則是用 it 來存取而不是 this。也可以依照需求改成其他的名字，增加可讀性。
與 run 相同，會將最後一行帶到下一個 chain 或是回傳。
eg key?.let {... use "it" to use key, "this" to refer the current class} , or change "it" to custom name by "customName ->"

T.also / also : 剩下的 also和 apply 決大部分也是使用於初始化物件。前文提到：這幾種 Standard Library Function 其實可以互相替換，選擇合適的場景使用即可。

run,let vs also,apply 
run,let return last line,  also/apply return the whole as "this" OR pass to next chain

T.run, T.let (T is as a parameter, only last line if we pass T then can pass as chain or return)
T.also (T is as "this", so it will return T after "also" modification)
有點像是 builder pattern ，做完一次設定後又將自己回傳回去。另外， also在 scope 內可以透過 it 來存取 T本身。
``` 
val drink = FiftyLan().also {
    it.setSugarLevel(FiftyLan.SugarLevel.Half) // after this also, return
}.also {
    it.setIceLevel(FiftyLan.IceLevel.Few) // after this also, return 
}.also {
    it.要多帶我們一杯紅茶拿鐵嗎好喝喔 = false //return
}.also {
    it.plasticBag = true // return after this also
} // Everytime a also, it will return, and then further modify if other "5 scope function exist"

drink.printResult()
// It will return FiftyLan() with modififed boolean
```
T.apply (Similar as T.also, except use "this" to access T , T.also use "it" to access)

T.run (use this "T")
T.let (use "it" for "T" , "this' for class)
with( this or implicit)
T.also (use "it")
T.apply (use "this")

#### name shadowed
valid to have same name in inside scope and outside scope
```
val s : String = "bob"
if(...) {
  val s : String = "Peter" 
}
```
extension function 有個好處是可以在使用時就進行 「?」 或 「!!」 的宣告 eg T.run, let 
eg goodSmartPhone?.run {...} ?: "error" , T?.let {...} ?: "error"

#### Conclusion of Scope Function
這些 Standard Library 提供的 Function 其實大同小異。用的時候除了語意以外，還有什麼選擇方式？
1. 要傳遞最後一行，還是傳遞自己？
2. 是否需要 extension function 先判斷可為空的變數？
3. Scope 內想透過 this或 it 存取 T？
   考量可以是：是否需要存取外層的變數，identifier 是否可以依需求自由命名
先判斷 1. ，假設情境需要傳遞自己，即有 apply 或 also 可以選擇。再用 3. 判斷目前情境適合哪一個： apply 透過this 而 also透過 it 來存取傳入變數。

如果需要傳遞最後一行，有四個選項： run、 T.run、 with 和 let 。先用 2. 判斷是否需要預先判斷可為空變數。

不需要先判斷，那麼就剩： run和 with 。 with可以在 scope 內透過 this 存取傳入變數； run 沒有任何傳入變數，但可以將最後一行傳遞出去。
需要先判斷，即為 T.run和 let 。再用 3. 判斷哪個適合目前的情境： T.run 透過 this ，let 則是透過 it 來存取傳入的變數。

#### String to char to ASCII
```
class Solution {
    fun scoreOfString(s: String): Int {
        // toByte().toInt()
        // char.code
        var res = 0
        for(c in 1..s.length-1) {
            // res += abs(s[c] - s[c-1])
            res += abs(s[c].code - s[c-1].code)
        }
        return res
    }
}
```

#### Destructing (with data class)
data class Student(val name: String, val age: Int)
val student1 = Student("Jack", 10)
by student1.val, it can have shortcut to replace as 
val (name, age) = student1

#### Secondary Constructor
As mentioned above, Kotlin may have one or more secondary constructors. 
Secondary constructors allow initialization of variables and allow to provide some logic to the class as well. 
They are prefixed with the constructor keyword.
we can use parent constructor by : "super" or other constructor by "this"
make sure the parent is public(open)
class Person (val _name: String) , that (xxx) is the primary constructor which is implicit
class Add constructor(val a: Int, val b: Int) is explicit
without val/val inside constructor, it will consider temp variable
we can do code implementation by init{}

execute secondary then pass it to primary
constructor(name: String) : this(name, "Bobby", 0) // because the creation is User("Alex") with no other parameter provided, so give default
value
```
class Add {
    // calling another secondary using this
    constructor(a: Int,b:Int) : this(a,b,7) {
        var sumOfTwo = a + b
        println("The sum of two numbers 5 and 6 is: $sumOfTwo")
    }
     
    // this executes first
    constructor(a: Int, b: Int,c: Int) {
        var sumOfThree = a + b + c
        println("The sum of three numbers 5,6 and 7 is: $sumOfThree")
    }
}

open class Parent {
    constructor (emp_id: Int, emp_name: String, emp_salary: Double) {
        var id: Int = emp_id
        var name: String = emp_name
        var salary : Double = emp_salary
        println("Employee id is: $id")
        println("Employee name: $name")
        println("Employee salary: $salary")
        println()
    }
}
class Child : Parent {
    constructor (emp_id : Int, emp_name: String):super(emp_id,emp_name,500000.55){
        var id: Int = emp_id
        var name: String = emp_name
        println("Employee id is: $id")
        println("Employee name: $name")
    }
}
```


#### getter , setter , without getter,setter its private bydefault
```
default getter and setter (auto generated)
get() {
    return field
}
get() = field //also valid
set(value) {
    field = value
}

if set(value){
    firstName = value // its recursion error, because any changing value of variable will invoke setter, which we should use "field"
}

// Custom getter,setter (not suggest, except need special things to do)
without var, is val
fun setFirstName(newValue: String) {

}

fun getFirstName() : String{
    return this.firstName
}
```

#### Companion Object (Static in java)
val calculator = Calculator()
calculator.sum(5,10) // need to call object to call it

call that function without create object
By Companion Object (just Calculator.sum(5,10))
```
companion object {
    public const val MAX = 99999
    fun sum(a:Int, b:Int) : Int {
        return a + b
    }
}
```


#### Private Constructor for Singleton (object)
Make by "object" keyword
```
object Database {
    init {
        println("Database Created")
    }
}

// to call it
println(Database) // same hash code
println(Database) // same hash code
```

Long one
```
class Database private constructor() {
    companion object {
        private var instance: Database? = null
        
        fun getInstance() : Database {
            if(instance == null) {
                instance = Database()
            }
            return instance
        }
    }
}
```

#### Lazy initialization
create object is expensive, use lazy initialization, only create it when need it
with lazy, init{} will not trigger because the object is not initializated, only initialized when use that object
```
main fun{
    val user2 by lazy {
        User(...) //create object
    }
|
class User() {
    init {
        //create everytime if created object
        priln("created")
    }
}
```

#### Enum (java just enum className)
we can pass parameter and make it as property, similar as constructor but already defined
for dir in Direction.values() 
    println(dir) // get enum Name eg NORTH (same as dir.name)
    println(dir.direction) // get direction
val direction = Direction.valueOf("NORTH") // check enum is it exist , if exist use it 

```
enum class Direction(var direction : String) {
    NORTH("north"),
    SOUTH("south"),
    EAST("east"),
    WEST("west"); // provide semicolon when have method
    
    fun printData() {
        println("direction is $direction")
    }
}
```

#### Inner class (when 2 class very close relationship)
Main
```
    val listView = ListView(arrayOf("Name1", "Name2", "Name3"))
    listView.ListViewItem().displayItem(0) // get Name1
    
    // to create it (similar as Java)
    listViewItemXX : listView.ListViewItem() = ...
    OR
    listViewItem = listView.ListViewItem()
```

Class
```
class ListView(val items: Array<String>) {
    inner class ListViewItem() {
        fun displayItem(position: Int){
            println(items[position])
        }
    }
}
```

#### class (private, public)
In essence, the "public" keyword is used to provide access to elements
while the "open" keyword is used to allow for inheritance and extension of elements in Kotlin
kotlin中的public與java中的意義同，但是java僅用public修飾時表示可存取預設可被繼承，kotlin僅用public修飾時表示可存取但預設不可被繼承。
Modifier	            Class member	                  Top-level declaration
--------------------------------------------------------------------------------
public (default)	    Visible everywhere	              Visible everywhere
internal	            Visible in a module	              Visible in a module
protected	            Visible in subclasses	          ---
private	                Visible in a class	              Visible in a file


public, internal, private and protected is all valid for class,val,var,method

With default setter, getter, we can do (private set) to make setter private
eg 
public var: bar : Int = 5 private set // Setter Only visible in the current file BUT bar is visible everywhere

In Kotlin, an outer class does not see private members of its inner classes.
So subclass dk parent private
```
open class Outer {
    private val a = 1
    protected open val b = 2
    internal open val c = 3
    val d = 4  // public by default

    protected class Nested {
        public val e: Int = 5
    }
}

class Subclass : Outer() {
    // a is not visible
    // b, c and d are visible
    // Nested and e are visible

    override val b = 5   // 'b' is protected
    override val c = 7   // 'c' is internal
}
class Unrelated(o: Outer) {
    // o.a, o.b are not visible
    // o.c and o.d are visible (same module)
    // Outer.Nested is not visible, and Nested::e is not visible either
}
```

#### Inheritance parameter in class
if have constructor eg the class have val and var parameter
the child if use parent property, so no need to use var/val eg (name , color)
Car (name:String , color:String val engines: Int, val doors : Int) : Vehicle("abc", "red")
// we pass name and color to parent
// OR we can override it by
override var name : String = "Tesla" // so the parent Vehicle name parameter will be override

#### how to overwrite method?
override the method by "open fun" , similar as "open class"
```
// child class to override method (1. add something on top of it , 2. completely override it
override fun move() {
    flying() // extra thing    
    super.move() // override and maintain parent move 
}
fun flying() {
    .... 
}
// OR

```

#### sealed class
Sealed Class (if user define / define it in main) OR Enum class (if pre-define)
enum not allow property in enum but by constructor only with value
```
enum class Result(val message : String) {
    Error("error") // valid
    Error(val message:String) // invalid
}
```

for Sealed class
"when" syntax will have "is"
```
sealed class Result(val message : String) {
    class Success(message : String) : Result(message)
    class Error(message : String) : Result(message)
}
fun getData(result : Result) {
    when(result) {
        is result.Success -> ...
        is result.Error -> 
    }
}

fun main() {
    val success = Result.Success("SUCCESS") // this is same as enum, however enum object is class but not to put data
    //it is not possible to change the value of an enum constant at runtime in Kotlin.
    
    getData(success)
}
```

we can have sealed class inside sealed class
the when either cover Error OR cover RecoverableError and NonRecoverableError OR use "else"
eg 
sealed Error(message : String) : Result(message) {
    class RecoverableError (...) : Error
    class NonRecoverableError (...) : Error
}