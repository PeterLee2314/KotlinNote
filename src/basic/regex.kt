package basic

fun main(args : Array<String>) {
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