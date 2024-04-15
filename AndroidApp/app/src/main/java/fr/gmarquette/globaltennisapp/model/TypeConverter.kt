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
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import fr.gmarquette.globaltennisapp.model.tournament.Tournament
import java.io.ByteArrayOutputStream

class TypeConverter
{

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

    @TypeConverter
    fun fromLastWinners(list: MutableList<Tournament.LastWinners>?): String = Gson().toJson(list)

    @TypeConverter
    fun toLastWinners(json: String): MutableList<Tournament.LastWinners> {
        val listType = object : TypeToken<MutableList<Tournament.LastWinners>>() {}.type
        return Gson().fromJson(json, listType)
    }

    @TypeConverter
    fun fromSeeds(list: MutableList<Tournament.Seeds>?): String = Gson().toJson(list)

    @TypeConverter
    fun toSeeds(json: String): MutableList<Tournament.Seeds> {
        val listType = object : TypeToken<MutableList<Tournament.Seeds>>() {}.type
        return Gson().fromJson(json, listType)
    }

    @TypeConverter
    fun fromPrize(list: MutableList<Tournament.Prize>?): String = Gson().toJson(list)

    @TypeConverter
    fun toPrize(json: String): MutableList<Tournament.Prize> {
        val listType = object : TypeToken<MutableList<Tournament.Prize>>() {}.type
        return Gson().fromJson(json, listType)
    }

    @TypeConverter
    fun fromPoints(list: MutableList<Tournament.Points>?): String = Gson().toJson(list)

    @TypeConverter
    fun toPoints(json: String): MutableList<Tournament.Points> {
        val listType = object : TypeToken<MutableList<Tournament.Points>>() {}.type
        return Gson().fromJson(json, listType)
    }

    @TypeConverter
    fun fromList(list: List<String>?): String? = Gson().toJson(list)

    @TypeConverter
    fun toList(string: String?): List<String>? {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson<List<String>>(string, listType)
    }





}