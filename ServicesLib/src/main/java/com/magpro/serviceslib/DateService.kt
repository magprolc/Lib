package com.magpro.serviceslib

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class DateService {

    fun currentDate(defaultLanguage: Boolean): String {
        return if (defaultLanguage){
            SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        }else{
            SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())
        }
    }

    fun currentDateTime(defaultLanguage: Boolean): String{
        return if (defaultLanguage){
            SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        }else{
            SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(Date())
        }
    }

    fun dayName(days: List<String>): String {
        val calendar = Calendar.getInstance()
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
        return when (dayOfWeek) {
            Calendar.SUNDAY -> days[0]
            Calendar.MONDAY -> days[1]
            Calendar.TUESDAY -> days[2]
            Calendar.WEDNESDAY -> days[3]
            Calendar.THURSDAY -> days[4]
            Calendar.FRIDAY -> days[5]
            Calendar.SATURDAY -> days[6]
            else -> throw IllegalArgumentException("Invalid day of week")
        }
    }

    fun dateTimeStampToInt(): Int = (System.currentTimeMillis() / 1000).toInt()

    fun hours12(hour: Int, minute: Int): String {
        val hourStr = if (hour % 12 == 0) 12 else hour % 12
        val minuteStr = if (minute < 10) "0$minute" else minute
        val amPm = if (hour < 12) "AM" else "PM"
        return "$hourStr:$minuteStr $amPm"
    }

    fun addPeriod(amount: Int, days: Boolean, months: Boolean, years: Boolean): String {
        val cal = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        return try {
            cal.time = dateFormat.parse(currentDate(false))!!
            if (days){
                cal.add(Calendar.DAY_OF_WEEK, amount)
            }
            if (months){
                cal.add(Calendar.MONTH, amount)
            }
            if (years){
                cal.add(Calendar.YEAR, amount)
            }
            dateFormat.format(cal.time)
        } catch (e: ParseException) {
            currentDate(false)
        }
    }

    @Suppress("SimpleDateFormat")
    fun compareDates(firstDate: String?, secondDate: String?): Boolean {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val first = sdf.parse(firstDate!!)
        val second = sdf.parse(secondDate!!)
        return !second.after(first)
    }

}