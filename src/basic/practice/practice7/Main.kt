package basic.practice.practice7

enum class TimeInterval {
    DAY, WEEK, YEAR
}

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int)

operator fun MyDate.plus(timeInterval: TimeInterval): MyDate = addTimeIntervals(timeInterval,1)

class RepeatedTimeIntervals(val timeInterval: TimeInterval, val amount: Int)

operator fun TimeInterval.times(number: Int) =
    RepeatedTimeIntervals(this, number)

operator fun MyDate.plus(repeatedTimeIntervals: RepeatedTimeIntervals) : MyDate = addTimeIntervals(repeatedTimeIntervals.timeInterval, repeatedTimeIntervals.amount)


fun task1(today: MyDate): MyDate {
    return today + TimeInterval.YEAR + TimeInterval.WEEK
}

fun task2(today: MyDate): MyDate {
    return today + TimeInterval.YEAR * 2 + TimeInterval.WEEK * 3 + TimeInterval.DAY * 5
}