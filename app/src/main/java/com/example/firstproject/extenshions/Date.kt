package com.example.firstproject.extenshions

import java.lang.Math.round
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs
import kotlin.math.roundToLong
import kotlin.math.round as round1

const val SECOND = 1000L
const val MINUTE = 60* SECOND
const val HOUR = 60* MINUTE
const val DAY = 24* HOUR
fun Date.format(pattern:String="HH:mm:ss dd.MM.yy"):String{
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
return dateFormat.format(this)
}
fun Date.add(value:Int, units: TimeUnits):Date{
    var time = this.time
time += when(units){
    TimeUnits.SECOND -> value*SECOND
    TimeUnits.MINUTE -> value* MINUTE
    TimeUnits.HOUR -> value*HOUR
    TimeUnits.DAY -> value*DAY
}
    this.time = time
    return this
}
enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY
}

fun Date.humanizeDiff(date: Date = Date()): String? {
    val timeN: List<String> = this.format().split(" ")
    val hhmmss: List<String> = timeN[0].split(":")
    val ddmmyy: List<String> = timeN[1].split(".")
    val dateTime: List<String> = date.format().split(" ")
    val datehhmmss: List<String> = dateTime[0].split(":")
    val dateddmmyy: List<String> = dateTime[1].split(".")
    val diff: Long = (((datehhmmss[0].toInt() - hhmmss[0].toInt()) * HOUR) +
            ((datehhmmss[1].toInt() - hhmmss[1].toInt()) * MINUTE) +
            ((datehhmmss[2].toInt() - hhmmss[2].toInt()) * SECOND)
            +((dateddmmyy[0].toInt() - ddmmyy[0].toInt())* DAY))
    return when {
        abs(diff / HOUR) > 0 -> {
           return when (round1((abs(diff / HOUR)).toFloat()).toInt()) {
                1 -> return "в сети 1 час назад"
                in 2..4 ->  return "в сети ${abs(diff / HOUR)} часа назад "
                in 5..23 -> return "в сети ${abs(diff / HOUR)} часов назад"
               else ->"в сети больше дня назад "
            }
        }
        abs(diff / MINUTE) > 0 -> {
           return when (round1((abs(diff / MINUTE)).toFloat()).toInt()) {
                1 -> return "в сети 1 минуту назад"
                in 2..4 ->  return "в сети ${abs(diff / MINUTE)} минуты назад"
                in 5..59 -> return "в сети ${abs(diff / MINUTE)} минут назад"
               else -> "в сети больше дня назад2"
            }
        }
        else -> {
            return when (round1((abs(diff / SECOND )).toFloat()).toInt()) {
                1 -> "в сети 1 секунду назад"
                in 2..4 ->  "в сети ${abs(diff / SECOND)} секунды назад"
                in 5..59 -> "в сети ${abs(diff / SECOND)} секунд назад"
                else -> "в сети менее секунды назад"
            }
        }
    }

}