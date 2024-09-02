package br.edu.ifsp.dmo.diario.util

import android.util.Log
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object DateConversor {

    public fun dateFormatToDateObject(date: String, time: String): Date{

        val dateTimeFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        val dateTimeString = "$date $time"
        var combinedDate: Date? = Calendar.getInstance().time

        try{
            combinedDate = dateTimeFormat.parse(dateTimeString)
        } catch (e : Exception){
            Log.e("DiaryEntryDate", "Date ${combinedDate.toString()}", e)
        }

        val dateFinal: Date = combinedDate ?: Date()

        return dateFinal
    }

    fun dateFormatToDateString(date: Date): String{
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        return dateFormat.format(date)
    }

    fun dateFormatToTimeString(date: Date): String{
        val timeFormat = SimpleDateFormat("HH:mm")
        return timeFormat.format(date)
    }
}