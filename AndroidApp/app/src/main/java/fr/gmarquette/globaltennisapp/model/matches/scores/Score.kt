/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.model.matches.scores

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import fr.gmarquette.globaltennisapp.model.matches.Match

@Entity(tableName = "scores")
data class Score(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var homeOne: String,
    var homeTwo: String,
    var homeThree: String,
    var homeFour: String,
    var homeFive: String,
    var awayOne: String,
    var awayTwo: String,
    var awayThree: String,
    var awayFour: String,
    var awayFive: String,

    var homeTieBreakOne: String,
    var homeTieBreakTwo: String,
    var homeTieBreakThree: String,
    var homeTieBreakFour: String,
    var homeTieBreakFive: String,
    var awayTieBreakOne: String,
    var awayTieBreakTwo: String,
    var awayTieBreakThree: String,
    var awayTieBreakFour: String,
    var awayTieBreakFive: String,

    val matchId: Int,
    @Ignore val matches: Match? = null,
)
{

    @Ignore constructor(homeOne: String, homeTwo: String, homeThree: String,
        awayOne: String, awayTwo: String, awayThree: String,
        homeTieBreakOne: String, homeTieBreakTwo: String, homeTieBreakThree: String,
        awayTieBreakOne: String, awayTieBreakTwo: String, awayTieBreakThree: String,
        matchId: Int) : this (0, homeOne, homeTwo, homeThree, "", "",
        awayOne, awayTwo, awayThree, "", "",
        homeTieBreakOne, homeTieBreakTwo, homeTieBreakThree, "", "",
        awayTieBreakOne, awayTieBreakTwo, awayTieBreakThree, "", "", matchId, null)

    constructor(homeOne: String, homeTwo: String, homeThree: String, homeFour: String, homeFive: String,
        awayOne: String, awayTwo: String, awayThree: String, awayFour: String, awayFive: String,
        homeTieBreakOne: String, homeTieBreakTwo: String, homeTieBreakThree: String, homeTieBreakFour: String, homeTieBreakFive: String,
        awayTieBreakOne: String, awayTieBreakTwo: String, awayTieBreakThree: String, awayTieBreakFour: String, awayTieBreakFive: String,
        matchId: Int) : this (0, homeOne, homeTwo, homeThree, homeFour, homeFive, awayOne, awayTwo, awayThree, awayFour, awayFive, homeTieBreakOne, homeTieBreakTwo, homeTieBreakThree, homeTieBreakFour, homeTieBreakFive, awayTieBreakOne, awayTieBreakTwo, awayTieBreakThree, awayTieBreakFour, awayTieBreakFive, matchId, null)
}
