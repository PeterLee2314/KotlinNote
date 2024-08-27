package basic.practice.practice7

import java.util.Calendar

fun MyDate.addTimeIntervals(timeInterval: TimeInterval, amount: Int) : MyDate {
    val c = Calendar.getInstance()
    c.set(year + if(timeInterval == TimeInterval.YEAR) amount else 0, month, dayOfMonth)
    var timeInMills = c.timeInMillis
    val oneDay = 24 * 60 * 60 * 1000L
    timeInMills += amount * when (timeInterval) {
        TimeInterval.DAY -> oneDay
        TimeInterval.WEEK -> 7 * oneDay
        TimeInterval.YEAR -> 0L
    }
    val result = Calendar.getInstance()
    result.timeInMillis = timeInMills
    return MyDate(result.get(Calendar.YEAR), result.get(Calendar.MONTH), result.get(Calendar.DATE))
}