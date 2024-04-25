/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.model

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import fr.gmarquette.globaltennisapp.model.tournament.Tournament
import java.io.ByteArrayOutputStream
import java.util.Date

class TypeConverter
{

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun fromBitmap(bitmap: Bitmap): ByteArray {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        return outputStream.toByteArray()
    }

    @TypeConverter
    fun toBitmap(byteArray: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }

    @TypeConverter
    fun fromTournamentDate(value: Tournament.TournamentDate): String
    {
        return "${value.startDay}, ${value.startMonth}, ${value.startYear}, " +
                "${value.endDay}, ${value.endMonth}, ${value.endYear}"
    }

    @TypeConverter
    fun toTournamentDate(value: String): Tournament.TournamentDate
    {
        val parts = value.split(",")
        require(parts.size == 6) { "Invalid format for TournamentDate string" }

        return Tournament.TournamentDate(parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim(), parts[4].trim(), parts[5].trim()
        )
    }
}