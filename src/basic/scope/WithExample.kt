package basic.scope

fun main(args : Array<String>) {
    val ppl : Telephone= Telephone()
    with(ppl) {
        this.fromWhere = "HK"
        whoCallMe = "Bobby"
    }
    ppl.callMe("John")

    val nullPpl : Telephone? = null
    // with ? check , prevent null object
    with(nullPpl) {
        this?.fromWhere = "Hoh"
        this?.whoCallMe = "TOm"
    }
    // also valid (because pass nullPpl with ?, so include no need null check)
    nullPpl?.run {
        this.fromWhere = "Hoh"
        this.whoCallMe = "TOm"
    }
}