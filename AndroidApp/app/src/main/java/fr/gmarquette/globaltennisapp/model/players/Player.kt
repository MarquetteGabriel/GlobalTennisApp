/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.model.players

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import fr.gmarquette.globaltennisapp.model.enums.Category
import fr.gmarquette.globaltennisapp.model.enums.Country
import fr.gmarquette.globaltennisapp.model.matches.Match
import fr.gmarquette.globaltennisapp.model.players.injuries.Injuries
import fr.gmarquette.globaltennisapp.model.players.rank.Rank
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "players")
data class Player (
    @IgnoredOnParcel @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var firstName: String,
    var lastName: String,
    var age: Int,
    var birthDate: String,
    var birthPlace: String,
    var nationality: Country,
    var height : String,
    var weight : String,
    var coach: String,
    var turnedPro: Int,
    @Ignore @IgnoredOnParcel val singlesTitles: List<String>? = null,
    @Ignore @IgnoredOnParcel val doublesTitles: List<String>? = null,
    var plays: String,
    var backhand: String,
    @Ignore @IgnoredOnParcel val injuries: List<Injuries>? = null,
    var category: Category,
    var picture: String,
    @Ignore @IgnoredOnParcel val rank: Rank? = null,
    @Ignore @IgnoredOnParcel val results: List<Match>? = null
) : Parcelable
{
    @Ignore @IgnoredOnParcel private val fullName: String = "$firstName $lastName"

    constructor(firstName: String, lastName: String, age: Int, birthDate: String, birthPlace: String) : this(0, firstName, lastName, age, birthDate, birthPlace, Country.FRANCE, "", "", "", 0, emptyList(), emptyList(), "", "", null, Category.ATP, "", null, null)
}