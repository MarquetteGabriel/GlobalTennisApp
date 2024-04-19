/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.model.players

import android.graphics.Bitmap
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import fr.gmarquette.globaltennisapp.model.enums.Category
import fr.gmarquette.globaltennisapp.model.enums.Country
import fr.gmarquette.globaltennisapp.model.players.rank.Rank
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "players")
data class Player (
    var firstName: String,
    var lastName: String,
    var age: Int,
    var birthDate: String,
    var birthPlace: String,
    var nationality: Country,
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
    @Ignore @IgnoredOnParcel val rank: Rank,
    // val results: List<Matches>, Not Available Yet
) : Parcelable
{
    @IgnoredOnParcel @PrimaryKey(autoGenerate = true) var id: Int = 0
    @IgnoredOnParcel var picture: Bitmap? = null
    @IgnoredOnParcel private var fullName: String = ""


    init {
        fullName = "$firstName $lastName"
    }
}