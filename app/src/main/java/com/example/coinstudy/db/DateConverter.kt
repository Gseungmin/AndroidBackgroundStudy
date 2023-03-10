package com.example.coinstudy.db

import androidx.room.TypeConverter
import java.util.*

class DateConverter {

    @TypeConverter
    fun fromTimeStamp(value: Long) : Date {
        return Date(value)
    }

    @TypeConverter
    fun toTimeStamp(date: Date) : Long {
        return date.time
    }
}