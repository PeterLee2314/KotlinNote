package basic

public class a {

}

fun main(args : Array<String>) {
    var nums = intArrayOf(1,2,3,4)
    nums[1] = 7
    nums.set(1,99)
    for(i in nums) {
        println(i)
    }
    nums.forEach { i -> println(i)}

    var nums2 = IntArray(5) // empty array

    var string1 = arrayOfNulls<String>(5) // empty String Array
    var string2 : Array<String?> = emptyArray() // valid
    var string3 : Array<String?> = arrayOfNulls(4)
    var string4 : Array<String> = arrayOf("Mashroom", "Kitkat", "Oreo", "Lolipop")

}