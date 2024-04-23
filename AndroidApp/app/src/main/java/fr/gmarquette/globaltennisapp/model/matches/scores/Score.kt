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
    val homeOne: String,
    val homeTwo: String,
    val homeThree: String,
    val homeFour: String,
    val homeFive: String,
    val awayOne: String,
    val awayTwo: String,
    val awayThree: String,
    val awayFour: String,
    val awayFive: String,

    val homeTieBreakOne: String,
    val homeTieBreakTwo: String,
    val homeTieBreakThree: String,
    val homeTieBreakFour: String,
    val homeTieBreakFive: String,
    val awayTieBreakOne: String,
    val awayTieBreakTwo: String,
    val awayTieBreakThree: String,
    val awayTieBreakFour: String,
    val awayTieBreakFive: String,

    val matchId: Int,
    @Ignore val matches: Match? = null,
)
{
    constructor(homeOne: String, homeTwo: String, homeThree: String,
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
