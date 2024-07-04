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