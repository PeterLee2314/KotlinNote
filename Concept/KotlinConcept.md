### Kotlin
#### Kotlin basic
val (constant , java final)
var (variable)
${object.name} or $var (better for concat)
```
val b : Int = 5
var name : String = ""
val peter = basic.Alien() // not allow to have new reference 
```
Kotlin allow call java code (No setter,getter call needed)
```
//basic.JavaAlien.java
public class basic.JavaAlien {
    private String name ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
// Main.kt
var javaAlien = basic.JavaAlien()
javaAlien.name = "Bobby" // call setter automatically
println("Java basic.Alien Name is : ${javaAlien.name}") // call getter auto
```

Why work? because Kotlin turn everything into class and run by JVM 
Also allow java code to kt code

Kotlin not need class in coding, it will generate class in JVM
Cant decompile from .class to .java (simple java is possible)
Kotlin (;) is optional (kotlin will provide it for you)

#### Kotlin convention
use Int(Kotlin) , not int(java)

#### if else expression
allow if else with =   (skipped result = num1, but simply num1, num2 )
```
var num1 : Int = 4
var num2 : Int = 7
var result : Int = 0;
result = if(num1 > num2) num1 else num2
result = if(xx) else if(xx) else x
```

#### question mark (?) , nullable
value assign null 
```
var strNull : String = null // invalid (Null can not be a value of a non-null type String)
var name: String? = null // valid
println(tittu.name.length) // invalid
println(tittu.name?.length) // valid  ( in java will throw NullPointerException) 
```
nullable, Kotlin help you to handle null Object
```
var bob? = Alien() // invalid
var bob : Alien? = Alien() // valid

```

#### When(Kotlin) vs Switch(Java)
```
when(num) {
    1 -> println("its one")
    2 -> println("its two")
    else -> println("other num")
}

var str = when(num) {
    1 -> "One"
    2 -> "Two"
    else -> "Others"
}
```
#### while & do while & for
downTo, until
var nums = 1 until 16 ([1,15] only)
```
var nums = 1..5  // auto increment (i = 1 to 5)
var nums2 = 16 downTo 1 // 16..1 is not allow
16 downTo 1 == 16 downTo(1) , downTo is a method
//similar as int a : nums
for(a in nums) {

}
// go 2 (1,3,5)
for(a in nums step 2) {
    println(a) // 1 to 5
}

for(a in nums.reversed()){

}

nums.count() // how many loop value [1,15] so its 15
var nums = 'A'.. 'z' // loop from A - Z - z (include specical symbol)
```

#### ArrayList 
withIndex() get index + element
```
var numslist = listOf(1,2,3,4) // Collections
for (i in numslist) {
    println(i)
}
for ((i,e) in numslist.withIndex()) {
    println("$i $e")
}

```

#### Map
```    
var aliens = TreeMap<String,Int>()
aliens["Peter"] = 543
aliens["Bobby"] = 123 // put value
```

#### Function
fun Name(var1 : type , var2 : type) : return type
```
fun peterSpeak(a : Int , b : Int): Int {
    return a + b
}
fun peterSpeak(a : Int , b : Int): Int = a + b // oneline return
fun peterSpeak(a : Int , b : Int): Int = if(a>b) a else b // similar as Java (? :)
```
#### Java call Kotlin function
Java class function , should be static or non static in a class
In Kotlin, there is no define of class needed
Kotlin method by default static
we can change the class name by @file
```
// in java class (call pattern) xxxKt
 System.out.println(MainKt.max(5,8));
 
 // in Main.kt
 @file:JvmName("First") // change to .class Name First
 ```
#### Default & Named Parameter
Double to Int, use ().toInt()
what if i want the interest to be optional, so user can decide put or not
```
@JvmOverloads
fun calcAmount(amt: Int, interest : Double = 0.04): Int {
    return (amt + amt*interest).toInt()
}
 
```
Java dont support this , add @JvmOverloads on kt
```
NameAndDefault.calcAmount(70) // invalid (if kt method no @JvmOverloads annotation)

```
So default parameter = the option parameter (@JvmOverloads)
named parameter = we give name to the method parameter, easy to look
Named parameter
```
var finalAmt = calcAmount(amt = 50, interest = 0,03) , amt and interest have to match the parameter (can in any sequence)
var finalAmt = calcAmount(interest = 0,03, amt = 50) // valid  (not exist in Java)
```

String to Int
```
var str : String = "4"
var str2Num : Int = str.toInt()
```

#### Try , Catch in Kotlin
try can be expression (try as an expression)
```try {

}catch (e : Exception) {

} finally {
    
}
Expression
    var numCatch : Int = try {
        str.toInt()
    }catch (e : NumberFormatException) {
        0
    }
```


#### Extension function
With extension function, we dont need a static method but simply use current Object to execute method
Extensions Kotlin provides the ability to extend a class or an interface with new functionality 
without having to inherit from the class or use design patterns such as Decorator.
```
fun Alien.plus(a : Alien) : Alien {
var newAlien = Alien()
newAlien.skills = this.skills + " " + a.skills
return newAlien
}
```

#### Infix & Operator overloading
Infix : a1.plus(a2) => a1 plus a2, more natural
Infix only accept 1 parameter
eg infix fun Alien.plus(a : Alien) : Alien ...

Operator overloading : not usually use it in project, but in case
turn (plus) to (+), call function plus if between two alien object
limited set of Operators can see this overloading feature
eg i in nums, 1..5 , add operator in front
eg operator infix fun Alien.plus(a : Alien) : Alien {

#### Tail Recursion (tailrec)
execute Recursion at the same time
store the execution inside the recursion rather than calculate it during return
// Recursion , return a * func(a-1)
// Tail recursion return func(a-1, a*result) , result is the parameter

In tail recursion, no pending task in Stack, but compiler is not optimize to do that for you
optimize it by yourself by (tailrec)

#### Kotlin Constructor
```
class Alien constructor(var n : String){
    var name : String = n
}

without consturctor also work (but when public, private any modifier , it still need)
class Alien (var n : String){
    var name : String = n
}

default value by var n : String = "bob"
```

#### Primary and Secondary Constructor
write block if want to do something
Primary
```
var name : String = ""
init {
    println("object created")
    name = n
}
```

Secondary (assign value other than primary constructor)
If we want secondary, primary must exist first , (directly or indirectly)
```
constructor(age : Int, name : String) {

}

call primary and secondary constructor 
constructor(age : Int, name : String) : this(name) {

}

```
Meaning it first load the secondary constructor, then by "this(name)" it pass the name variable to the primary constructor
from class Alien (name : String)


#### Inheritance
Alien extends Human
```
class Alien() : Human {

}
```
make class Final, because other shouldn't extend it
by default in Kotlin, all class is Final
use Open

#### open 
you are allow this class can be inherited by other class
``` 
open class Human {
    fun think() {
        println("Think")
    }
}
class Alien() : Human() {

}
```


#### override and open
method that be overrided, have to use open keyword

``` 
open class Human {
    open fun think() {
        println("Think")
    }
}

class Alien() : Human() {
    override fun think() {
        println("Alien think")
    }
}
```
method by default is final too, cant override by default

#### constructor in inheritance
when create Alien object
it execute Human constructor first, then Alien constructor (same as Java)
``` 
open class Human (name : String){
    init {
        println("in Human")
    }
    open fun think() {
        println("Think")
    }
}

class Alien(n : String) : Human(n) {
    init {
        println("in Alien")
    }

    override fun think() {
        println("Alien think")
    }
}
```
#### Abstract
Same as java, also allow define method that is not abstract
when abstract, we no need open, because someone must inheritance that abstract class
``` 
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
```

#### Interface
by default, all method is abstract inside interface
Kotlin also support Default Method (same as Java8) inside interface
Break the system if change existing interface, thats why we allow define method inside interface
Reason why only 1 extends in java is because ambiguous issue, what if have same function in both interfaces?
Override the conflict interface function
```
interface A {
    fun show()
    fun greet() {
        println("hello")}
}
```

Override conflicted method, and allow call the method by the super<T>.method()
```
override fun dup() {
    super<A>.dup()
    super<B>.dup()
    println("in Abc")
} 
```
Differences between Abstract and Interface
In earlier, interface not allow method define, but after Java 8, always go for Interface instead of Abstract class

#### Data class
When we want Kotlin help us to Override, hashCode(), toString(), Equals()
Data class also give copy() method
``` 
    var laptop3 = laptop1.copy(price=2300) // change the price variable value
```
also allow changing value during copy
#### Any 
when set to Any we can change the data type to specific type
```
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Laptop

        if (brand != other.brand) return false
        if (price != other.price) return false

        return true
    }
```

#### javaClass (java : class.getName())

#### Object Keyword (Singleton)
multiple books but only 1 bookshelf
object keyword, Object is already an object, no need create object
``` 
object BookShelf{
    var books = arrayListOf<Book>()
}
```

data class will also make the input automatically stored in class variable, and Kotlin no need setter and getter

``` 
data class Book (var name : String, var price : Int) {

}

object BookShelf{
    var books = arrayListOf<Book>()

    fun showBooks() {
        for(i in books) {
            println(i)
        }
    }
}

fun main(args : Array<String>) {
    BookShelf.books.add(Book("Java",50))
    BookShelf.books.add(Book("Java",80))
    BookShelf.books.add(Book("Kotlin",450))

    BookShelf.showBooks()
}
```

#### Anonymous innerclass
we need object : Interface { } to have Anonymous innerclass, because its not reusable thats why we use "object"
```
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
```

#### companion object
"companion object" keywords to have static
static is not allow in Kotlin
if create function within "companion object" inside class, 
companion object not give you static behaviour, just because object keyword allow method name call "Bookshelf.show()"
If we want to link Java and Kotlin, then we use @JvmStatic 

#### factory design pattern in Kotlin
creation of Object is not easy, need pass parameter, load some connection,etc (complex logic)
reduce user works by Creating Factory Method
use object instead of class , when we want to call method in static
"companion object" wrap method, when we want to call method in static 

We create the A object by factory method (A.create()), so that user dont have to A() when in complicated case
```
    companion object {
        fun create() : A = A()
    }
    fun main(args : Array<String>) {
        var obj = A.create()
    
    } 
```
#### Backtick as Escape Character
some function is not allow to create, eg . in() because its for loop (i in nums) method, use backtick(`) to achieve
it
```
fun `in` {

}
```
to use it, just backtick again

#### User Input , readLine()
```
// Scanner is valid in Kotlin
var sc = Scanner(System.`in`)
println(sc.next())

// In kotlin just readLine(
var num1 = readLine()
println(num1)
```


#### Array
var nums = intArrayOf() // which is int[] nums = {1,2,3,4}
nums[1] = 7  
nums.set(1,99) // change value in index 1 to 99
nums.last() // last value
```
for(i in nums) {
    println(i)
}
nums.forEach { i -> println(i)}
```

var nums2 = IntArray(5) // empty array
var string1 = arrayOfNulls<String>(5) // empty String Array
var string2 : Array<String?> = emptyArray() // valid
var string3 : Array<String?> = arrayOfNulls(4) // valid
var string4 : Array<String> = arrayOf("Mashroom", "Kitkat", "Oreo", "Lolipop") // valid

#### Collection API (Collection, Set, List, Map, SortedSet, and SortedMap)
##### List (Read-Only List, Mutable List)
by default list is Read-Only List, use mutableListOf<Int>() to have mutable list
add value by 
```
// Below are all immutable list
var values : List<Int> = listOf<Int>(8,9,4,2)
var values2 : Collection<Int> = listOf<Int>(8,9,4,2)
var values3 : Iterable<Int> = listOf<Int>(8,9,4,2)
var values4 : Any = listOf<Int>(8,9,4,2)

println(values.get(0)) // get index 0 
println(values.indexOf(9)) // where is 9 located
```

MutableList<Int>
``` 
var values : MutableList<Int> = mutableListOf<Int>(8,9,4,2)
```
Collection forEach
In Kotlin, forEach is start with {}  ,eg 
```
forEach{e -> 
    e = e * 10
    print(e.toString() + " ")
}
```

List of Objects
```
var book1 = Books()
var book2 = Books("Alie")
var book3 = Books("Cookie")

var bookList = mutableListOf<Books>(book1,book2,book3,Books("Monster"))
for(book in bookList) {
    println(book)
}
```

#### Extends vs Implement
class A : B() ,C,D // with() it is extends class

#### Higher Order Function
variable, object is primary thing, 
we use method to call it, but we cant call a method of method
pass a function in a function (function programming)
In function programming, function is first class memeber, treat function as variable, pass them into function

#### forEach() (function programming)
"it" mean iterator, cant change the name, must use "it" in forEach({}) , otherwise use forEach{} OR declare a Consumer variable
values.forEach({ println(it)}) // pass a function println() inside a function"forEach"

#### Consumer 
Consumer is an Interface which is Java Consumer
declare it by
```
var con : Consumer<Int> = object : Consumer<Int>{
    override fun accept(t: Int) {
        println(t)
    }
}
values.forEach(con) // valid forEach({})

values.forEach({ t -> println(t) }) // valid forEach({})
values.forEach(::println) // which exactly same as System.out::println in java  (function reference)

```
::println mean the classname, but skipped 
we can even move the parathesis out , same as  1..5, 1 in 5 , downTo
values.forEach { println(it) }
values.forEach { t -> println(t) }
values.forEach(::println)

#### Filter & Map
which is same as Stream API in Java
```
values.filter {e -> e%2 ==0} // too long
values.filter{it%2==0}.forEach(::println) // better
```

#### forEach in array loop
```
    logs.forEach { log ->
        when (log) {
            "../" -> counter = maxOf(0, counter - 1)
            "./" -> Unit // Do nothing
            else -> counter++
        }
    }
```

#### Unit in when 
Unit is mean do nothing, particularly useful when you want a condition but do nothing in "when" expression

#### max, maxOf, min, minOf
max == maxOf
min == minOf

#### one line function
```
fun start(): String = "OK"
```
equvalent to
```
fun start(): String {
    return "OK"
}
```

#### Default and Named Arguments
fun abc(i : Int = 5) // default
fun abc(i : Int = 5, j : Int = 2, k : String) 
to use Named simply abc("ab") put inside k directly
OR abc(k = "ab", j = 3) // use default i = 5

#### Overloading by default and named argument
fun foo(name: String, number: Int = 42, toUpperCase: Boolean = false) =
(if (toUpperCase) name.uppercase() else name) + number

so all function is valid 
```
fun useFoo() = listOf(
        foo("a"),
        foo("b", number = 1),
        foo("c", toUpperCase = true),
        foo(name = "d", number = 2, toUpperCase = true)
)
```


#### String literals Escaped string("") multi-line(""") 
remove trim prefix by trimMargin() , or remove trim by trimIndent
By default, a pipe symbol | is used as margin prefix, 
but you can choose another character and pass it as a parameter, like trimMargin(">").
```
val text = """
    |Tell me and I forget.
    |Teach me and I remember.
    |Involve me and I learn.
    |(Benjamin Franklin)
    """.trimMargin("#")
    
    output = 
    Tell me and I forget.
    Teach me and I remember.
    Involve me and I learn.
    (Benjamin Franklin)
```

```
val withoutIndent =
    """
            ABC
            123
            456
        """.trimIndent()
println(withoutIndent) // ABC\n123\n456
```

#### $, ${}, index$
to reuse the varaible , by %d -> add index and $ sign -> %1\$d

// Formats a negative number to be enclosed in parentheses, then repeats the same number in a different format (without parentheses) using `argument_index$`.
val negativeNumberInParentheses = String.format("%(d means %1\$d", -31416)
println(negativeNumberInParentheses)
//(31416) means -31416

// Formats an integer, adding leading zeroes to reach a length of seven characters
val integerNumber = String.format("%07d", 31416)
println(integerNumber)
// 0031416

// Formats a floating-point number to display with a + sign and four decimal places
val floatNumber = String.format("%+.4f", 3.141592)
println(floatNumber)
// +3.1416

// Formats two strings to uppercase, each taking one placeholder
val helloString = String.format("%S %S", "hello", "world")
println(helloString)
// HELLO WORLD


##### String template with Regex
```
fun main() {
    val pattern = """\d{2} July \d{4}""".toRegex()
    val date = "25 July 2022"

    if (pattern.matches(date)) {
        println("Valid date format: $date")
    } else {
        println("Invalid date format: $date")
    }

    val modifiedDate = date.replace(pattern, "01 August 2023")
    println("Modified date: $modifiedDate")
}
``` 

#### null safety
To perform a certain operation only for non-null values, you can use the safe call operator together with let
```
val listWithNulls: List<String?> = listOf("Kotlin", null)
for (item in listWithNulls) {
    item?.let { println(it) } // prints Kotlin and ignores null
}
```

Safe calls are useful in chains. For example, Bob is an employee who may be assigned to a department (or not). 
That department may in turn have another employee as a department head. 
To obtain the name of Bob's department head (if there is one), you write the following:
```
bob?.department?.head?.name
```
Such a chain returns null if any of the properties in it is null.
##### Nullable receiver
toString is defined on nullable receiver, it return String "null"
```
val person: Person? = null
logger.debug(person.toString()) // Logs "null", does not throw an exception

```

receive null string and perform operation by (?.toString())
```
var timestamp: Instant? = null
val isoTimestamp = timestamp?.toString() // Returns a String? object which is `null`
if (isoTimestamp == null) {
   // Handle the case where timestamp was `null`
}
```
##### Elvis operator
```
val l: Int = if (b != null) b.length else -1
```
Elvis operator 
give b.length if not null, else -1
```
val l : Int = b?.length ?: -1  
```
##### not-null assertion operator (!!)
convert any value to NON-NULLABLE, so if null, throw EXCEPTION
```
val l = b!!.length
```

##### Safe casts (as?)
Regular casts may result in a ClassCastException if the object is not of the target type.
Another option is to use safe casts that return null if the attempt was not successful:
```
val aInt: Int? = a as? Int
```

##### Conclusion
- Add after declare type, eg Int?, String? , Student?
- Add after variable eg b?.length
- Elvis operator, ?: , if null perform operation after (?:) 
  - eg val name = node.getName() ?: throw IllegalArgumentException("name expected")
- When value is nullable, their method should add ? eg var b : String? , so println(b?.length)
- with safe casts add (?) after (as)
- any null occur will not perform in chain, eg client?.personalInfo?.email
  - mean client class is not null and personalInfo variable class is not null, then get the email
Good example of non null
```
val email = client?.personalInfo?.email
if(email != null && message != null) {
    mailer.sendMessage(email, message)
}

Example 2
data class Address(val street: String, val city: String, val zipCode: String)
data class User(val name: String, val address: Address?)

fun main() {
    val user: User? = User("John Doe", null)

    val city = user?.address?.city
    println("City: $city")

    val zipCodeLength = user?.address?.zipCode?.length
    println("Zip Code Length: $zipCodeLength")
}

```

#### Nothing type
The throw expression has the type Nothing. 
This type has no values and is used to mark code locations that can never be reached.
```
fun fail(message: String): Nothing {
    throw IllegalArgumentException(message)
}
```
Nothing type also happen when dealing with type inference
when without specific type
Nothing?, has exactly one possible value, which is null
```
val x = null           // 'x' has type `Nothing?`
val l = listOf(null)   // 'l' has type `List<Nothing?>
```

#### High order function
A higher-order function is a function that takes functions as parameters, or returns a function.
For example , fold
the fold function accept two parameter (initial, combine)
combine is a FUNCTION which take (R,T) return R
therefore we can use lambda to define the combine function by our self which is
{acc, nextElement -> acc + nextElement} , which in our code will become custom combine function

ALSO
fun <T,R> is for declaring the parameter used in this function
Collection<T> is the type of which T type can use the .fold method
because for example , Collection<Int> which Colletion could be an ArrayList and <Integer> type
```
fun <T, R> Collection<T>.fold(
    initial: R,
    combine: (acc: R, nextElement: T) -> R
): R {
    var accumulator: R = initial
    for (element: T in this) {
        accumulator = combine(accumulator, element)
    }
    return accumulator
}
```

```
val items = listOf(1, 2, 3, 4, 5)

// Lambdas are code blocks enclosed in curly braces.
items.fold(0, { 
    // When a lambda has parameters, they go first, followed by '->'
    acc: Int, i: Int -> 
    print("acc = $acc, i = $i, ") 
    val result = acc + i
    println("result = $result")
    // The last expression in a lambda is considered the return value:
    result
})

// Parameter types in a lambda are optional if they can be inferred:
val joinedToString = items.fold("Elements:", { acc, i -> acc + " " + i })

// Function references can also be used for higher-order function calls:
val product = items.fold(1, Int::times)
```

#### Lambda
collection.any({it % 2 == 0})
is same as
collection.any {it % 2 == 0} 
also valid
collection.any({e -> e % 2 == 0})

##### How to return a function type?
- a lambda expression: { a, b -> a + b }
- an anonymous function: fun(s: String): Int { return s.toIntOrNull() ?: 0 }
- a top-level, local, member, or extension function: ::isOdd, String::toInt,
- a top-level, member, or extension property: List<Int>::size,
- a constructor: ::Regex
- Bound function and property references 
```
val numberRegex = "\\d+".toRegex()
println(numberRegex.matches("29"))

val isNumber = numberRegex::matches
println(isNumber("29"))
```

##### Normal class
Normal class
class MyClass : MySuperClass(), MyInterface, Serializable


IntTransformer implement the function type (Int) -> Int 
when override invoke function, it allow directly call the class with value as function

```
class IntTransformer : (Int) -> Int {
    override operator fun invoke(value: Int): Int {
        // Transformation logic goes here
        // Modify the 'value' and return the transformed result
        return value * 2
    }
}

fun main() {
    val transformer = IntTransformer()

    val result = transformer(5)
    println("Transformed value: $result")
}
```

This is good without write an method
目的
大概是需要一開始實體物這個物件
想要省去一個function call的撰寫