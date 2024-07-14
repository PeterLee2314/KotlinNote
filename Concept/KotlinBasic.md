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

####
this is
as
by
Flow<List<String>>
flow { emit(myData)}


