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

#### sealed class (cant instanitate ,but instantiate with subclass)
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

####  "Function Types" or "Function Literals with Receiver."
declare a function inside parameter
This allows you to define behavior or actions that will be executed inside the compare function but are provided from outside 
as a lambda expression. 
The lambda expression acts as a callback function that gets invoked inside the compare function.

When the compare function is called, it adds a and b and passes the result to the sayHello callback. 
In this case, the lambda expression println("answer is $it") is executed, and the value of it represents the sum of a and b.
```
    val sayHi : (Int) -> Unit = {
        println(it)
    }
OR
    val sayHi : (Int) -> Unit = { e->
        println(e)
    }
OR IF MORE THAN 1 Parameter (need declare name)
    val sayHi : (Int, Int) -> Unit = { a,b ->
        println(a)
        println(b)
    }

```


predefine and outside declare (function parameter must be last)
```
fun main(args : Array<String>) {
    compare(2,3) {
        // override sayHello
        println("answer is $it")
    }
}
fun compare(a : Int, b: Int, sayHello : (Int) -> Unit) {
    sayHello(a+b)
}
```


outside:
```
fun compare(a: String, b: String): Boolean = a.length < b.length
```
inside:
```
val funcMultiply = {a: Int, b: Int -> a*b}
```
#### Extension Function (make new function)
Kotlin provides the ability to extend a class with new functionality without
having to inherit from the class
eg
```
fun Int.increment(): Int {
    return this+1
}
fun Boolean.invert(): Boolean {
    return xor(true) // a xor true = !a, by the conditional inversion property of xor.
}
```

#### Domain Specific Language (DSL)
Kotlin DSL lets you define a specific "language" using an extension
function under the hood. Useful for Builders, UI Layouts, and other
situations that have a standard structure.
```
fun doSomething(config: DoSomethingConfig.() -> Unit) {
	...
}

doSomething {
	configVar1 = ...
    configVar2 = ...
    if(condition) configVar3 = ...
}
```
####  "Function Types" or "Function Literals with Receiver." 
Higher order functions are structures that getting function
parameters and return functions.
```
fun hello(operator: () -> Unit) {
    operator()
}
```

Last line is the return type
More customized function
the function inside behaviour is set by outside 
sayHello is customizable, which you can make any changes of it to produce different result
```
fun compare(a : Int, b: Int, sayHello : (Int) -> Unit) {
    sayHello(a+b)
}
```
#### What is receiver? (with Receiver, no need to use "it")
Any block of code in Kotlin may have a type (or even multiple types) as a receiver 
So outside that block of code, have a "receiver" 
receiver can be function or properties
Available in that block of code WITHOUT qualifying IT 沒有限定它。
eg (Int) -> Long {it.toLong()} // work , but name shadowed happended in future development
Q: what if in future, we need to use it again? because of nested block of DSL html{ it.body {...}}}
```
// Int as Parameter, so use it
    val intToLong : (Int) -> Long = {
        it.toLong()
    }
// Int as Receiver
    val intToLong : Int.() -> Long = {
        toLong()
    }
// Int as Receiver with String as parameter prodcuing a Long
    Int.(String) -> Long  
    
Int.() -> Long  // taking an integer as receiver producing a long
String.(Long) -> String // taking a string as receiver and long as parameter producing a string
GUI.() -> Unit // taking a GUI as receiver and producing nothing
```

#### inline / noinline / crossinline
在重構(Refactor)裡有個技巧就叫inline。例如我們在main呼叫了hello這個function。
```
fun main() {
   hello()
}

fun hello(){
   println("hello")
}
```
你可以在hello上按右鍵 → Refactor → Inline function，就會把hello這個function收回去變成：
```
fun main() {
    println("hello")
}
```
在一個function前面加上inline，表示在編譯到Java程式碼時會把這個function收回去。
你就會看到main裡面並沒有再去呼叫hello這個function了，而是直接執行你原本在hello function裡做的事。
```

public static final void main() {
   int $i$f$hello = false;
   String var1 = "hello";
   boolean var2 = false;
   System.out.println(var1);
}

public static final void hello() {
   int $i$f$hello = 0;
   String var1 = "hello";
   boolean var2 = false;
   System.out.println(var1);
}
```
但如果是一般的 function 這樣寫，IDE會警告：

Expected performance impact from inlining is insignificant. Inlining works best for functions with parameters of functional types.

意思就是除了在 functional type 裡使用，其實沒有太大的好處。建議你不要這樣使用。

那麼在 lambda 使用的好處是什麼呢？我們來看下面這段程式碼，寫了一個high order function 叫 hello。
```
fun hello(operator: () -> Unit) {
    operator()
}
```

在一個inline function，如果有其中一個function參數不想被 inline，則在前面加上noinline。

下方程式碼因為hello有使用inline，在第4行我們加了 return，會造成如下方程式碼的2個地方(第6行、第13行)不會再往下執行，這跟一般對於return的定義會不太一樣。
```
fun main() {
    hello {
        println("A")
        return
    }
    //這裡以下不會被執行到
    println("main_end")
}

inline fun hello(operator: () -> Unit) {
    println("hello")
    operator()
    //這裡以下不會被執行到
    println("hello2")
}
```
我們可以加上crossinline，這樣就會強迫你只能使用return@hello的寫法，如果直接使用return，IDE就會跳出錯誤來跟你說不能這樣寫。
```
fun main() {
    hello {
        println("A")
	//如果只寫return，會跳出error
        return@hello
    }
    //這裡以下不會被執行到
    println("main_end")
}

inline fun hello(crossinline operator: () -> Unit) {
    println("hello")
    operator()
    //這裡以下不會被執行到
    println("hello2")
}
```
#### this Expression
Qualified this "this@A"
Implicit this "this"

Qualified this, can access "class" , "extension function", "labeled function literal with receivers"
by "this@label"
```
class A { // implicit label @A
    inner class B { // implicit label @B
        fun Int.foo() { // implicit label @foo
            val a = this@A // A's this
            val b = this@B // B's this

            val c = this // foo()'s receiver, an Int
            val c1 = this@foo // foo()'s receiver, an Int

            val funLit = lambda@ fun String.() {
                val d = this // funLit's receiver, a String
            }

            val funLit2 = { s: String ->
                // foo()'s receiver, since enclosing lambda expression
                // doesn't have any receiver
                val d1 = this
            }
        }
    }
}
```

Implicit this (with this.xxx() , it use class function)
```
fun printLine() { println("Top-level function") }

class A {
    fun printLine() { println("Member function") }

    fun invokePrintLine(omitThis: Boolean = false)  { 
        if (omitThis) printLine()
        else this.printLine()
    }
}

A().invokePrintLine() // Member function
A().invokePrintLine(omitThis = true) // Top-level function
```
#### receiver(this) receiver parameter(it)
lamdba (code: String.(Int) -> Unit)
Here the code lambda has a reciever (of type String) and a single parameter (of type Int). So you can mix this and it

```
fun foo(code: String.(Int) -> Unit){
    "test".code(5)
}

fun main(){
    foo {
        println(this) // test (that String)
        println(it) //  5 (that Int)
    }
}
```
#### Extension functions (extend from the class and make new function)
Kotlin provides the ability to extend a class or an interface with new functionality without having to 
inherit from the class or use design patterns such as Decorator. 
This is done via special declarations called extensions.

To declare an extension function, prefix its name with a receiver type, which refers to the type being extended
eg we want the "MutableList" class to have a function "swap"
MutableList<T>.swap(...) (this == MutalbeList , parameter = ... = it)

The this keyword inside an extension function corresponds to the receiver object (the one that is passed before the dot). 
Now, you can call such a function(that "swap" function) on any MutableList<Int>
```
fun MutableList<Int>.swap(index1: Int, index2: Int) {
    val tmp = this[index1] // 'this' corresponds to the list
    this[index1] = this[index2]
    this[index2] = tmp
}
```
Extensions are resolved statically
We get answer "Shape" not "Rectangle"
because "the function called only depend on the parameter type which is s : Shape"
```
open class Shape
class Rectangle: Shape()

fun Shape.getName() = "Shape"
fun Rectangle.getName() = "Rectangle"

fun printClassName(s: Shape) {
    println(s.getName())
}

printClassName(Rectangle())
```
If a class has a member function, and an extension function is defined which has the same receiver type,
the same name, and is applicable to given arguments, the member always wins. For example:
it printed "Class method", which original class always win
```
class Example {
    fun printFunctionType() { println("Class method") }
}

fun Example.printFunctionType() { println("Extension function") }

Example().printFunctionType()
```
BUT, Its okay to have overloading
which fun Example.printFunctionType(i : Int) { println("Extension function $i") } // have accept (Int) , its valid
##### Nullable receiver
Its okay to use Any as Receiver , and Null as Receiver too, but in case compiler error, we need null check
```
fun Any?.toString(): String {
    if (this == null) return "null"
    // After the null check, 'this' is autocast to a non-nullable type, so the toString() below
    // resolves to the member function of the Any class
    return toString()
}
```

#### Getter and Setters
Full syntax for declaring a "var" property is as follows:
```
var <propertyName>[: <PropertyType>] [= <property_initializer>]
    [<getter>]
    [<setter>]
```
val only have getter, no setter
```
can omit the property type if it can inferred from the getter
class Rectangle() {
    val width: Int = 100
    val height: Int = 50
    val area: Int // property type is optional since it can be inferred from the getter's return type
        get() = this.width * this.height
    val area2 get() = this.width * this.height

}
```

##### @Inject (its Dependency Injection)

If you need to annotate an accessor or change its visibility,
but you don't want to change the default implementation, 
you can define the accessor without defining its body:
```
// make setter private, but public getter, rather than totally private var
// impossible to make property getter visiblity Stronger than setter, just use private,protected var directly
var setterVisibility: String = "abc"
    private set // the setter is private and has the default implementation

var setterWithAnnotation: Any? = null
    @Inject set // annotate the setter with Inject (this will inject the object reference automatically if exists)
```

The initializer, getter, and setter are optional. 
The property type is optional if it can be inferred from the initializer or the getter's return type, as shown below:

#### Accessor
interface Accessor<out V>
Represents a property accessor, which is a get or set method declared alongside the property.
A backing field will be generated for a property if it uses the default implementation of at least one of the accessors (1),
or if a custom accessor references it through the field identifier.
(1)
```
// This is the default implementation (implcitly)
var myProperty: Int = 0
    get() = field // we have field here, because we just use the default implementation
    set(value) {
        field = value
    }
// If we want to use it, just put "field" inside it, it will detect
var myProperty: String = "Hello"
    get() {
        println("Getting property value")
        return field
    }
    set(value) {
        println("Setting property value")
        field = value
    }
// Otherwise, it will not generate "field"
val isEmpty: Boolean
    get() = this.size == 0  // we didnt use field so will not generate it
```
#### IN KOTLIN whenever you refer to the property, you're calling the accessor method, not accessing the field directly.
#### Backing fields (field == the variable itself)
properties (variable inside class) , parameter (variable declared inside fun())
hold its value in memory, originally we simply put value inside the field, now we have Condition 
to fill only when the value is > 0
```
    var age2 = 0 // the init value will not trigger the setter
        set(value) {
            if (value > 0) {
                field = value
            }
        }
```

field will not even generated, because we not even use it
there would be no point in generating a field, because it would never be used!
Kotlin recognises this and generates only the accessor method(s).
```
val myProperty: Int
    get() = someOtherObject.someOtherProperty
    
val myProperty: Int = 13
    get() = field + 15 // answer = 13 + 15 = 28 
```

#### Backing properties (think as var's parameter  OR shadowing other property)
the "table" val is having properties "_table" and use that "_table" to perform operation
```
private var _table: Map<String, Int>? = null
public val table: Map<String, Int>
    get() {
        if (_table == null) {
            _table = HashMap() // Type parameters are inferred
        }
        return _table ?: throw AssertionError("Set to null by another thread")
    }
```

there's no reason to do Backing properties when we want a private setter property:
```
private var _word = "test"
val word: String   //simply making word as a getter for _word, so the setter cant make changes to _word
   get() = _word
   
Because you can just do this:

var word = "test"
    private set 
```
#### Extension properties (Only able to use when getter, setter implicitly provided)
Since extensions do not actually insert members into classes,
there's no efficient way for an extension property to have a backing field.
This is why initializers are not allowed for extension properties. 
Their behavior can only be defined by explicitly providing getters/setters.

rather than fun, we make variable
eg var, val
```
val <T> List<T>.newSize: Int
    get() = size - 1
```

#### typealias
```
   val bothType : StringAndInt = {
        a,b->
        println("string:$a int:$b")
    }
    // Equivalent
    val bothType2 : (String,Int) -> Unit = {
        a,b ->
        println("string:$a int:$b")
    }

typealias StringAndInt = (String, Int) -> Unit
```
####  lambda expression (->) & anonymous function ({})
a lambda expression: { a, b -> a + b },
an anonymous function: fun(s: String): Int { return s.toIntOrNull() ?: 0 }
eg map {} , we dont need ()

#### Int and Int?
Int? cant compare with Int 
eg var target : Int? = null
if(target > 5) //invalid Type mismatch.

#### When (in, is, enum)
is: check is it that TYPE
in : is that INT or within range

```
// use is (match the type)
fun hasPrefix(x: Any) = when(x) {
    is String -> x.startsWith("prefix")
    else -> false
}
// more than 1 value is match
when (x) {
    0, 1 -> print("x == 0 or x == 1")
    else -> print("otherwise")
}
when (x) {
    s.toInt() -> print("s encodes x")
    else -> print("s does not encode x")
}

// capture return subject
fun Request.getBody() =
    when (val response = executeRequest()) {
        is Success -> response.body
        is HttpError -> throw HttpException(response.status)
    }
```
#### Expression
An expression consists of variables, operators, methods calls etc that produce a single value.
A variable declaration can not be an expression (var a = 100)
Assigning a value is not an expression (b = 15)
A class declaration is not an expression (class XYZ {….})
In Kotlin every function returns a value atleast Unit, so every function is an expression.
Expression :
var mul = a * b
sumOf(a,b)
if,else
##### Kotlin if expression –
In Java, "if" is a statement but, in Kotlin "if" is an expression. 
It is called an expression because it compares the values of a and b and returns the maximum value. 
Therefore, in Kotlin there is no ternary operator (a>b)?a:b because it is replaced by the if expression.
```
var a = if (b > c) b else c
var a = if (b > c) b else if( b > d) d else c
```
##### Label (for break, continue, return)
loop , place "label" at front , eg loop@ for...
lambda expression, place "label" at back , eg forEach abc@ {...} run abc@{...}
```
// only print one Hi 
loop@ for (i in 1..100) {
    println("Hi")
    for (j in 1..100) {
        if (...) break@loop
    }
}
// 10 Hi (just by default break its okay because we want to break the j)
for (i in 1..10) {
    println("Hi")
    loop@ for (j in 1..10) {
        break@loop
    }
}
```

##### Return
forEach {lambda expression}
inline (whole listOf...)
only when its Lambda expression AND inline, then can make "label"
```
fun foo() {
    listOf(1, 2, 3, 4, 5).forEach lit@{
        if (it == 3) return@lit // local return to the caller of the lambda - the forEach loop
        print(it)
    }
    print(" done with explicit label")
}
output: 1245 done with explicit label

//Without lit@, it will only output 12
```
use Implicit label ("@forEach") , because we only return from the forEach
```
if (it == 3) return@forEach
```
OR use anonymous function , instead of Lambda expression from forEach{} to forEach(fun() {})
then just use "return" directly
```
fun foo() {
    listOf(1, 2, 3, 4, 5).forEach(fun(value: Int) {
        if (value == 3) return  // local return to the caller of the anonymous function - the forEach loop
        print(value)
    })
    print(" done with anonymous function")
}
```

As we cover the loop with run (lambda expression) , so the return@loop will break the loop@
```
fun foo() {
    run loop@{
        listOf(1, 2, 3, 4, 5).forEach {
            if (it == 3) return@loop // non-local return from the lambda passed to run
            print(it)
        }
    }
    print(" done with nested loop")
}
//Output : 12 done with nested loop
```
##### return with value return@a 1
return@a 1, simply mean return to "label" "@a" with value 1
```
return@a 1
```
#### Exception (throw)
Almost the same as Java, except Java :try catch is statement, but Kotlin :try catch is expression
```
val a: Int? = try { input.toInt() } catch (e: NumberFormatException) { null }
```
###### Compiler Time (Checked Exception)
But Kotlin DONT HAVE CHECKED Exception , because too many repeated try,catch block
So, when calling Kotlin code from Java,Swift OR Objective-C, use @Throws
##### Nothing Type
The throw expression has the type Nothing.
This type has no values and is used to mark code locations that can never be reached. 
In your own code, you can use Nothing to mark a function that never returns:

```
val s = person.name ?: throw IllegalArgumentException("Name required")

fun fail(message: String): Nothing {
    throw IllegalArgumentException(message)
}
```
Nothing only have null value, if you deal with type inference (give type to var/val)
```
val x = null           // 'x' has type `Nothing?`
val l = listOf(null)   // 'l' has type `List<Nothing?>
```
#### Class constructor and init
Order is matter in class , so if there is some execution before init, will perform it first
```
class InitOrderDemo(name: String) {
    val firstProperty = "First property: $name".also(::println)
    
    init {
        println("First initializer block that prints $name")
    }
}
output : 
First property: hello
First initializer block that prints hello

if reverse it 
class InitOrderDemo(name: String) {
    init {
        println("First initializer block that prints $name")
    }
    val firstProperty = "First property: $name".also(::println)
}
output:
First initializer block that prints hello
First property: hello
```
If the constructor has annotations or visibility modifiers, the constructor keyword is required and the modifiers go before it:
``` 
class Customer public @Inject constructor(name: String) { /*...*/ }
```
##### Override
A member marked override is itself open, so it may be overridden in subclasses. If you want to prohibit re-overriding, use final:
```
open class Rectangle() : Shape() {
    final override fun draw() { /*...*/ }
}
```
You can also override a val property with a var property, but not vice versa.
This is allowed because a val property essentially declares a get method, and overriding it as a var additionally declares a set method in the derived class.

You can use override as part of the property declaration
```
interface Shape {
    val vertexCount: Int
}

class Rectangle(override val vertexCount: Int = 4) : Shape // Always has 4 vertices

class Polygon : Shape {
    override var vertexCount: Int = 0  // Can be set to any number later
}
```
If two draw() exist, to override it use super<className>.functionName()
```
open class Rectangle {
    open fun draw() { /* ... */ }
}

interface Polygon {
    fun draw() { /* ... */ } // interface members are 'open' by default
}

class Square() : Rectangle(), Polygon {
    // The compiler requires draw() to be overridden:
    override fun draw() {
        super<Rectangle>.draw() // call to Rectangle.draw()
        super<Polygon>.draw() // call to Polygon.draw()
    }
}
```

#### Class and parent execution order
```
open class Base(val name: String) {

    init { println("Initializing a base class") }  // 1 

    open val size: Int = 
        name.length.also { println("Initializing size in the base class: $it") }  // 2 
}

class Derived(
    name: String,
    val lastName: String,
) : Base(name.replaceFirstChar { it.uppercase() }.also { println("Argument for the base class: $it") }) {

    init { println("Initializing a derived class") } // 3

    override val size: Int =
        (super.size + lastName.length).also { println("Initializing size in the derived class: $it") } // 4
}
/*
Output:
Constructing the derived class("hello", "world")
Argument for the base class: Hello
Initializing a base class
Initializing size in the base class: 5
Initializing a derived class
Initializing size in the derived class: 10
*/
```
##### Child class call / Innter Class call
Child use super()
```
    override fun draw() {
        super.draw()
        println("Filling the rectangle")
    }
```
Inner class use super@className.functionName()
```
class FilledRectangle: Rectangle() {
    override fun draw() {
        val filler = Filler()
        filler.drawAndFill()
    }

    inner class Filler {
        fun fill() { println("Filling") }
        fun drawAndFill() {
            super@FilledRectangle.draw() // Calls Rectangle's implementation of draw()
            fill()
            println("Drawn a filled rectangle with color ${super@FilledRectangle.borderColor}") // Uses Rectangle's implementation of borderColor's get()
        }
    }
}
```
#### lateinit 
lateinit really useful when DI or Test setup , so we want a non-null properties but not yet give value
to check is it already declared 
```
if (foo::bar.isInitialized) {
    println(foo.bar)
}
```
#### lazy
only execute the lazy block when textView needed to execute 
```
    private val textView: TextView by lazy {
        println("this is lazy function block")
        findViewById(R.id.textView) as TextView
    }
```
如果呼叫了textView兩次，那還是只會呼叫一次lazy的function block一次，一但進行初始化後就不會再進行第二次初始化了：

#### not null lambda function
var initializer : (() -> T)? = initializer

#### block body vs last line return
fun <T> execute() : C<T> // is a block body , because of the ": C<T>" part , so last line need "return" keyword
fun<T> execute() {
    C<T>() // no need return keyword
}

#### <in T> vs <out T> vs <T>
<out T> denotes that T is covariant.
<in T> denotes that T is contravariant.
<T> generics
##### <out T>
It seems plausible that a sequence of giraffes can be treated as a sequence of animals
"Covariance" of an interface means that "if there is an implicit reference conversion from Giraffe to Animal 
then there is also an implicit reference conversion from I<Giraffe> to I<Animal>.
so Class<Type> is valid witth conversion of others

#### Functional Interface (fun interface)
interface Printer {
fun print()
}

fun Printer(block: () -> Unit): Printer = object : Printer { override fun print() = block() }  // this is performed implicitly
// return Printer
// with putting an object with Printer type and declared {override fun print() = block() } 

so 
```
fun interface Printer {
    fun print()
}

documentsStorage.addPrinter(::Printer)  // this will add the Printer instance
// ::Printer is the function reference which refers to the constructor of the Printer interface 

```
You can also simply rewrite the above using a type alias for a functional type:

``` 
typealias IntPredicate = (i: Int) -> Boolean

val isEven: IntPredicate = { it % 2 == 0 }

fun main() {
println("Is 7 even? - ${isEven(7)}")
}
```
However, functional interfaces and type aliases serve different purposes.
Type aliases are just names for existing types – they don't create a new type, while functional interfaces do
```
typealias Name = String // Name can be used as type ,but  its not a new type call Name
 val addition: MathOperation = MathOperation { a, b -> a + b } // while FI of MathOperation is a new type"MathOperation"
```
Type aliases can have only one member, while functional interfaces can have multiple non-abstract members and one abstract member. 
Functional interfaces can also implement and extend other interfaces.
#### dispatch receiver and an extension receiver
An instance of a class in which the extension is declared is called a dispatch receiver (use instance)
an instance of the receiver type of the extension method is called an extension receiver. (use class name)
```
make Extension Function
fun Host.printConnectionString() {
     //there actually using "this" to call Host properties
}
host.printConnectionString() // "host" is the Dispatch receiver

Extension receiver // the implicit "this" is the Extension receiver

```
How to identify Connection's function & Extension receiver ?
By this@Connection for the current class function, and directly use the function in extension function
```
class Connection {
    fun Host.getConnectionString() {
        toString()         // calls Host.toString()
        this@Connection.toString()  // calls Connection.toString()
    }
}
```
You can make Extension Function open, (no modification on the original class)
```
open class Base { }

class Derived : Base() { }

open class BaseCaller {
    open fun Base.printFunctionInfo() {
        println("Base extension function in BaseCaller")
    }

    open fun Derived.printFunctionInfo() {
        println("Derived extension function in BaseCaller")
    }

    fun call(b: Base) {
        b.printFunctionInfo()   // call the extension function
    }
}

class DerivedCaller: BaseCaller() {
    override fun Base.printFunctionInfo() {
        println("Base extension function in DerivedCaller")
    }

    override fun Derived.printFunctionInfo() {
        println("Derived extension function in DerivedCaller")
    }
}

fun main() {
    BaseCaller().call(Base())   // "Base extension function in BaseCaller"
    DerivedCaller().call(Base())  // "Base extension function in DerivedCaller" - dispatch receiver is resolved virtually
    DerivedCaller().call(Derived())  // "Base extension function in DerivedCaller" - extension receiver is resolved statically
}
```

#### Data classes
data class User(val name: String, val age: Int)
With the following function
.equals()/.hashCode() pair.

.toString() of the form "User(name=John, age=42)".

.componentN() functions corresponding to the properties in their order of declaration.

.copy() function (see below).

Data classes can't be abstract, open, sealed, or inner
The primary constructor must have at least one parameter.
All primary constructor parameters must be marked as val or var.

On the JVM, if the generated class needs to have a parameterless constructor, default values for the properties have to be specified
data class User(val name: String = "", val age: Int = 0)

Equals will only compare the properties inside () of the data class 
```
data class Person(val name: String) {
    var age: Int = 0  // this will not be compared by equals() or ==   ALSO not copy 
}
val person1 = Person("John")
val person2 = Person("John")
person1.age = 10
person2.age = 20

println("person1 == person2: ${person1.equals(person2)}") // true
println("person1 == person2: ${person1 == (person2)}") // true
// person1 == person2: true
```
#### sealed classes , sealed interface
Sealed classes and interfaces provide controlled inheritance of your class hierarchies.
sealed class and its direct subclass will not appear outside
once a module with a sealed interface is compiled, no new implementations can be created.
Direct subclasses are classes that immediately inherit from their superclass.
Indirect subclasses are classes that inherit from more than one level down from their superclass.

##### Declare a sealed class or interface
```
// Create a sealed interface
sealed interface Error

// Create a sealed class that implements sealed interface Error
sealed class IOError(): Error

// Define subclasses that extend sealed class 'IOError'
class FileReadError(val file: File): IOError()
class DatabaseError(val source: DataSource): IOError()

// Create a singleton object implementing the 'Error' sealed interface
object RuntimeError : Error
```
The above code, we ensure no one can implementing or extending them in the client code
library authors can be sure that they know all the possible error types and that other error types can't appear later.
```
sealed class Error(val message: String) {
    class NetworkError : Error("Network failure")
    class DatabaseError : Error("Database cannot be reached")
    class UnknownError : Error("An unknown error has occurred")
}

fun main() {
    val errors = listOf(Error.NetworkError(), Error.DatabaseError(), Error.UnknownError())
    errors.forEach { println(it.message) }
}
// Network failure 
// Database cannot be reached 
// An unknown error has occurred
```
A sealed class itself is always an abstract class, and as a result, can't be instantiated directly. 
So if we want sealed class, we need to use "object keyword" , eg object BaseError : Error()

Better solution, use Enum instead of String 
```
enum class ErrorSeverity { MINOR, MAJOR, CRITICAL }

sealed class Error(val severity: ErrorSeverity) {
    class FileReadError(val file: File): Error(ErrorSeverity.MAJOR)
    class DatabaseError(val source: DataSource): Error(ErrorSeverity.CRITICAL)
    object RuntimeError : Error(ErrorSeverity.CRITICAL)
    // Additional error types can be added here
}
```
##### Sealed constructor visilibility (proected, private)
```
sealed class IOError {
    // A sealed class constructor has protected visibility by default. It's visible inside this class and its subclasses
    constructor() { /*...*/ }

    // Private constructor, visible inside this class only.
    // Using a private constructor in a sealed class allows for even stricter control over instantiation, enabling specific initialization procedures within the class.
    private constructor(description: String): this() { /*...*/ }

    // This will raise an error because public and internal constructors are not allowed in sealed classes
    // public constructor(code: Int): this() {}
}
```
##### Sealed interface + Enum
enum classes can't extend a sealed class, or any other class. However, they can implement sealed interfaces:

```
sealed interface Error

// enum class extending the sealed interface Error
enum class ErrorType : Error {
    FILE_ERROR, DATABASE_ERROR
}
```
#### nested class vs Inner class vs subclass
nested classes cannot access the data or functions of their superclasses. 
Because they are kept in the background as static final.
```
class Outer {

    val companyName: String = "Huawei"
    private val companySecret: String = "Secret"

    fun getSomething(): String = ""

    class NestedClass {

        fun printSomething() {

            println("From Nested Class")
            
        }

    }

}
```

Inner class :  we can access the values and functions of our outer class in inner class.
```
class Outer2 {

    val companyName: String = "Huawei"
    private val companySecret: String = "Secret"

    fun getSomething(): String = ""

    inner class InnerClass {

        fun printSomething() {
            
            println("From Inner Class")

        }

    }

}
```

subclass (basically just a child class extend Outer2) Therefore ,it can put outside the class
```
// we put inside, which useful when the Parent is "sealed"
class Outer2 {
    class InnerClass : Outer2
}
Equivalent to
class Outer2 {
   
}

class InnerClass : Outer2 {

}

```

#### Local Inner Class
Local inner classes:
Local inner classes are declared inside a block of code, such as a function or a method,
and can access both local variables and members of the enclosing class. Here’s an example:
```
fun outerFunction() {
    val outerMember: Int = 1

    class Inner {
        fun print() {
            println("This is a local inner class with access to outerMember: $outerMember.")
        }
    }

    val inner = Inner()
    inner.print() // This is a local inner class with access to outerMember: 1.
}
```

#### Generics
generic types in Java are invariant, meaning that List<String> is not a subtype of List<Object>
```
// Java
List<String> strs = new ArrayList<String>();

// Java reports a type mismatch here at compile-time.
List<Object> objs = strs;

objs.add(1) // this is valid, but will have error
```
Also not valid in this case 
Collection<Object>.addAll(Collection<String>) ,
What if that Collection<Object> is Integer? , Invalid!!
```
void copyAll(Collection<Object> to, Collection<String> from) {
    to.addAll(from);
}
```
Solution: (the use of subtypes and supertypes)
Then how about we allow to add all the subtypes or supertypes of the target
```
interface Collection<E> ... {
    void addAll(Collection<? extends E> items);
}

void <T> copyAll(Collection<T> to, Collection<? extends T> from) {
    to.addAll(from);
}
```
##### wildcard type argument  VS type parameter <T>
type parameter define what is the type during runtime, however not allow to use other type (no bounding)
wildcard type argument allow supertype(super), subtype(extend) during runtime, allow to have up-bounding(extend) low-bounding(super)
```
eg <? Extend E> // valid if (?) of that parameter is subtype of E
eg <? Super E> // valid if (?) of that parameter is supertype of E

extend (subtype) , super(supertype)
Then why Java can support it? because <? extend E> <? super String>

eg <? extends E>  (You can read E's item not write it)
```
Collection<String> is a subtype of Collection<? extends Object>. 
In other words, the wildcard with an extends-bound (upper bound) makes the type covariant.

##### Covariant <? extend Object>, only allow to retrieve(read) elements from that Collection (Read only)
supertype if store that subtype , only allow to read it not add it
```
Collection<String> strings = new ArrayList<>();
strings.add("Hello");

Collection<? extends Object> objects = strings;
Object element = objects.iterator().next();
objects.add(new Object());  // This line will cause a compilation error
```
because <? extends Object> that ? could be any like Integer, String, Double, etc, for sure you can't add anything
but read it (Covariant)

##### Contravariant  (assign a general type to a variable of a more specific type) (Write-only)
reversely, if you can put items into the collection, it's okay to take a collection of Object "s" and put String "s" into it
```
List<Object> objects = new ArrayList<>();
objects.add(new Object());

List<? super String> strings = objects;
strings.add("Hello");
```
because <? super String>, you limited the subtype could only be parent of String, eg Object
so ? is impossible to be Integer, Double,etc . Because its the parent, it guarantee the parent ability is subset of String, like toString(), toHashCode()
its like a Subset
contravariance applies to adding elements to the collection. You can put elements into a List<? super String>, 
but you cannot retrieve elements from it and assume they are of type String. (cant read Object)
The type system prevents you from making such assumptions to maintain type safety.

#####  PECS stands for Producer-Extends, Consumer-Super.
Type safe != Immutable
If you use a producer-object, say, List<? extends Foo>, you are not allowed to call add() or set() on this object,
but this does not mean that it is immutable: for example, nothing prevents you from calling clear() to remove all the items from the list, 
since clear() does not take any parameters at all.
The only thing guaranteed by wildcards (or other types of variance) is type safety. Immutability is a completely different story.
#### declaration-site variance:
you can annotate the type parameter T of Source to make sure that it is only returned (produced) from members of Source<T>, 
and never consumed. To do this, use the out modifier:

##### Out (Covariant, used for Producer, READ ONLY) , In (Contravariant, used for Consumer, Write ONLY) , T (Invariant, both read/write)
out T, mean the T can only used for Producer purpose, return (READ ONLY)
```
interface Source<out T> {
    fun nextT(): T
}

fun demo(strs: Source<String>) {
    val objects: Source<Any> = strs // This is OK, since T is an out-parameter
    // ...
}
```
in T, mean the T can only used for parameter , which the T can store supertype of T <? super T>
, because Double is Number's super, we can store reference of instance of Number in a variable type of  Double
```
interface Comparable<in T> {
    operator fun compareTo(other: T): Int
}

fun demo(x: Comparable<Number>) {
    x.compareTo(1.0) // 1.0 has type Double, which is a subtype of Number
    // Thus, you can assign x to a variable of type Comparable<Double>
    val y: Comparable<Double> = x // OK!
}
```


#### Consumer & Producer
"consumer" and "producer" refer to the usage of a type parameter <T> within a class or interface.
Consumer: Type parameter <T> is used as the parameter type of a function or method parameter  , eg fun abc(T) , 
Producer : fun abc() { ... return T} , as the return type

#### Conclusion (Subtype,SuperType relationship) VS (Generic Type )
What is SubType, What is Super Type?
Consider a class hierarchy with a Vehicle class as the supertype and Car and Motorcycle classes as subtypes.
Car and Motorcycle are subtypes of Vehicle
Vehicle is supertype (have common properties and behaviour)
// supertype = subtype , eg Any = String // upcast
// subtype = supertype  eg Double = (Dobule)Number // downcast
##### Without Generic Type, everything valid
```
var animal: Animal? = null

animal = Bug()      // 沒問題

/*****************************/

fun getAnimal(): Animal {
    return Bug()    // 沒問題
}

/*****************************/

val bug: Bug = Animal() // 如果這個指派合法，表示這個語言支援逆變


```
// Corgi ≤ Dog ≤ Animal

open class Animal()
open class Dog() : Animal()
class Corgi() : Dog()
若我們用一個 Dog 實體，指派給這三個類別，其狀況如下，只有指派給 Corgi 時會出事
val animal: Animal = Dog()  // covariance  supertype = subtype
val dog   : Dog    = Dog() type = type
val corgi : Corgi  = Dog()  // Error!  , subtype = supertype // Invalid need casting manually
但若是使用了 Genetic Type ，就只能指派給完全一樣的類別
```
val animals: ArrayList<Animal> = ArrayList<Dog>()  // Error!
val dogs   : ArrayList<Dog>    = ArrayList<Dog>()
val corgis : ArrayList<Corgi>  = ArrayList<Dog>()  // Error!
```
但是，因為 val animal: Animal = dog() 是成立的，所以我們也會希望 val ancenstors: ArrayList<Animal> = ArrayList<Dog>() 能成立，也能夠成立，也就是 維持繼承關係
```
val animals: ArrayList<Animal> = ArrayList<Dog>()  // 想要這個也合法
val dogs   : ArrayList<Dog>    = ArrayList<Dog>()
val Corgis : ArrayList<Corgi>  = ArrayList<Dog>()  // Error!


```
假設這裡有一個支援 Covariance 的類別 DogHandler，此時 Kotlin 會限制 DogHandler 只能實作 getter
``` 
interface Handler<out T> {
    fun getInstance(): T?
}

class DogHandler : Handler<Dog> {
    private var dog: Dog? = null

    override fun getInstance(): Dog? {
        return dog
    }
}
```
Why only getter? , because now val animalHandler: Handler<Animal> = DogHandler() is with type "Dog"
```
val animalHandler: Handler<Animal> = DogHandler()

// scenario 1
val dog = Dog()
animalHandler.setInstance(dog)     // 可以

// scenario 2
val alien = Alien()
animalHandler.setInstance(alien)   //  No
```
那 Getter 不會有問題嗎
這裡的 animalHandler，指向的其實是一個 Handler<Dog> 的 instance
在呼叫 animalHandler.getInstance() 的時候，其實是呼叫了 Handler<Dog>.getInstance()，所以其實是拿到一個 Dog instance
但是，Kotlin 是支援 covariance 的，拿到的 instance 既是 Dog ，也同時是 Animal，所以沒有問題
換句話說，animalHandler 其實也不知道實際拿到的 instance 是誰，但可以確定是得到一個 Animal 的 instance

##### Generic Type with Contravariance
```
interface Handler<in T>{
    fun setInstance(instance: T)    
}


class DogHandler: Handler<Dog> {
    private var dog: Dog? = null

    override fun setInstance(instance: Dog) {
        this.dog = instance
    }

}
```

```
val animalHandler: Handler<Animal> = DogHandler() // Error!
val dogHandler   : Handler<Dog>    = DogHandler()
val corgiHandler  : Handler<Corgi> = DogHandler() // OK!

val corgi = Corgi()
corgiHandler.setInstance(corgi)  // 沒有問題
```
如果 Getter 還在會發生什麼事
```
val corgiHandler : Handler<Corgi> = DogHandler()
val corgi = corgiHandler.getInstance()
```
corgiHandler 指向的是 Handler<Dog>
corgiHandler 呼叫 getInstance() 實際上是呼叫到 Handler<Dog>.getInstance()，所以你只會拿到 Dog 物件
但依據 covariance (繼承關係) ，Corgi ≤ Dog，這個指派是不合法的
##### Important 在呼叫 add(或其他 setter) 的時候，只能塞該類別及其子類。就跟沒有使用 in / out 的時候一樣
```
class Handler<T> { }     // 沒有使用 Variance 的情境

val dogHandler: Handler<Dog> = Handler<Dog>()
dogHandler.add(Animal()) // Type mismatch!
dogHandler.add(Dog())    // OK
dogHandler.add(Corgi())  // OK

/******************************************/

class Handler<in T> { }   // 使用 Contravariance

val dogHandler: Handler<Dog> = Handler<Dog>()
dogHandler.add(Animal())  // Type mismatch!
dogHandler.add(Dog())     // OK
dogHandler.add(Corgi())   // OK 
```
他實際影響的是 List<T1> 與 List<T2> 這兩個 Complex Type 的繼承關係，也就是這兩個類別的指派(assignment) 是否合法
```
class Handler<T> { }

val dogHandler: Handler<Dog> = Handler<Animal>()  // Type mismatch!
val dogHandler: Handler<Dog> = Handler<Corgi>()   // Type mismatch!

/******************************************/

class Handler<in T> { }

val dogHandler: Handler<Dog> = Handler<Animal>() // 變合法了

/******************************************/

class Handler<out T> { }

val dogHandler: Handler<Dog> = Handler<Corgi>() // 變合法了
```
##### For Generic Type Soruce<T> 
Type safety refers to a programming language's ability to prevent or detect certain types of errors at compile-time,
thereby reducing the likelihood of runtime errors.
subtype, supertype no longer work because of Generics
So tackle it by Declaration-site Variance
eg interface Source<out T> (covariant)
- <? extend T> : meaning an Object type (supertype) could possibly itself or its subtype, so the Compiler will understand the type of that Instance
- <? super T>: meaning an Object type(subtype) could possibly itself(eg Double) of its supertype(eg Number)
- <out T> : <? extend T> + T as producer(read-only)  subtype assign to supertype
- <in T> : <? super T> + T as consumer (write-only)   supertype assign to subtype

The <out T> , its read-only, can only return T, because it accept T's subtype , eg if T is Any, it accept (Number,String,Int...)
Its valid to use T ,eg val a = T , or fun abc() : T, However not allow for setter
- <In T> valid to setter only eg fun abc(t : T)
```
open class Track
open class Audio : Track()
class Song : Audio()
```
for <out T>
```
interface Retailer<out T> {
    fun sell(): T
}

class TrackRetailer : Retailer<Track> {
    override fun sell(): Track {
        println("Sell track")
        return Track()
    }
}

class AudioRetailer : Retailer<Audio> {
    override fun sell(): Audio {
        println("Sell audio")
        return Audio()
    }
}

class SongRetailer : Retailer<Song> {
    override fun sell(): Song {
        println("Sell song")
        return Song()
    }
}
    val retailer1: Retailer<Track> = TrackRetailer()
    val retailer2: Retailer<Track> = AudioRetailer()// It can compile because of out
    val retailer3: Retailer<Track> = SongRetailer()// It can compile because of out
```

for <in T>
```
interface Consumer<in T> {
    fun buy(t:T)
}

class TrackConsumer : Consumer<Track> {
override fun buy(t: Track) {
println("Buy track")
}
}

class AudioConsumer : Consumer<Audio> {
override fun buy(t: Audio) {
println("Buy audio")
}
}


class SongConsumer : Consumer<Song> {
override fun buy(t: Song) {
println("Buy Song")
}
}

    val consumer1: Consumer<Song> = TrackConsumer()
    val consumer2: Consumer<Song> = AudioConsumer()
    val consumer3: Consumer<Song> = SongConsumer()
```
#### Star-projections (*)
Sometimes you want to say that you know nothing about the type argument, but you still want to use it in a safe way.

For Foo<out T : TUpper>, where T is a covariant type parameter with the upper bound TUpper, Foo<*> is equivalent to Foo<out TUpper>. 
This means that when the T is unknown you can safely read values of TUpper from Foo<*>.

For Foo<in T>, where T is a contravariant type parameter, Foo<*> is equivalent to Foo<in Nothing>. 
This means there is nothing you can write to Foo<*> in a safe way when T is unknown.

For Foo<T : TUpper>, where T is an invariant type parameter with the upper bound TUpper, 
Foo<*> is equivalent to Foo<out TUpper> for reading values and to Foo<in Nothing> for writing values.

For example, if the type is declared as interface Function<in T, out U> you could use the following star-projections:
Function<*, String> means Function<in Nothing, String>.
Function<Int, *> means Function<Int, out Any?>.
Function<*, *> means Function<in Nothing, out Any?>.

#### Generic function
```
fun <T> singletonList(item: T): List<T> {
    // ...
}

fun <T> T.basicToString(): String { // extension function
    // ...
}
```
To call a generic function, specify the type arguments at the call site after the name of the function:

val l = singletonList<Int>(1)
Type arguments can be omitted if they can be inferred from the context, so the following example works as well:

val l = singletonList(1)


#### Generic constraints
contraints the value that pass 
the type T must be subtype of Comparable<T>
```
fun <T : Comparable<T>> sort(list: List<T>) {  ... }
```
sort(listOf(1, 2, 3)) // OK. Int is a subtype of Comparable<Int>
sort(listOf(HashMap<Int, String>())) // Error: HashMap<Int, String> is not a subtype of Comparable<HashMap<Int, String>>
#### If more than 1 constraints (where)
```
fun <T> copyWhenGreater(list: List<T>, threshold: T): List<String>
    where T : CharSequence,
          T : Comparable<T> {
    return list.filter { it > threshold }.map { it.toString() }
}
```
T type must implement both CharSequence and Comparable.

#### Definitely non-nullable types (Same as Java @NotNull)
decalre generics follow with & Any
eg T & Any // non-nullable
A definitely non-nullable type must have a nullable upper bound.
The most common use case for declaring definitely non-nullable types is when you want to override a Java method that 
contains @NotNull as an argument. For example, consider the load() method:
```
//Java code
import org.jetbrains.annotations.*;

public interface Game<T> {
    public T save(T x) {}
    @NotNull
    public T load(@NotNull T x) {}
}
```
To override the load() method in Kotlin successfully, you need T1 to be declared as definitely non-nullable:

```
interface ArcadeGame<T1> : Game<T1> {
    override fun save(x: T1): T1
    // T1 is definitely non-nullable
    override fun load(x: T1 & Any): T1 & Any
}
```
#### Underscore operator for type arguments
Use it to automatically infer a type of the argument when other types are explicitly specified
```
abstract class SomeClass<T> {
    abstract fun execute() : T
}

class SomeImplementation : SomeClass<String>() {
    override fun execute(): String = "Test"
}

class OtherImplementation : SomeClass<Int>() {
    override fun execute(): Int = 42
}

object Runner {
    inline fun <reified S: SomeClass<T>, T> run() : T {
        return S::class.java.getDeclaredConstructor().newInstance().execute()
    }
}

fun main() {
    // T is inferred as String because SomeImplementation derives from SomeClass<String>
    val s = Runner.run<SomeImplementation, _>()
    assert(s == "Test")

    // T is inferred as Int because OtherImplementation derives from SomeClass<Int>
    val n = Runner.run<OtherImplementation, _>()
    assert(n == 42)
}
```

this is
as
by
Flow<List<String>>
flow { emit(myData)}


