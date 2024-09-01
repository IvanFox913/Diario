package br.edu.ifsp.dmo.diario.util

import java.text.SimpleDateFormat
import java.util.Date

object DateConversor {

    fun dateFormatToDateString(date: Date): String{
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        return dateFormat.format(date)
    }

    fun dateFormatToTimeString(date: Date): String{
        val timeFormat = SimpleDateFormat("HH:mm")
        return timeFormat.format(date)
    }
}