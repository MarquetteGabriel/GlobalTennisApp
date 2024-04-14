/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.model.players

import android.graphics.Bitmap
import androidx.room.PrimaryKey
import fr.gmarquette.globaltennisapp.model.enums.Category

data class Players (
    var firstName: String,
    var lastName: String,
    var age: Int,
    var birthDate: String,
    var birthPlace: String,
    var nationality: String,
    var height : String,
    var weight : String,
    var coach: String,
    val turnedPro: Int,
    val singlesTitles: List<String>,
    val doublesTitles: List<String>,
    val plays: String,
    val backhand: String,
    val injuries: List<String>,
    var category: Category,
    val rank: Rank,
    // val results: List<Matches>, Not Available Yet
)
{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var picture: Bitmap? = null
    var fullName: String = ""


    init {
        fullName = "$firstName $lastName"
    }
    data class Rank(
        var currentRank: Int,
        var previousRank: Int,
        var points: Int,
        var bestRank: Int,
    )
}