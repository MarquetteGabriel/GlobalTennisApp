/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.model.matches

import androidx.room.Entity

@Entity(tableName = "matches")
data class Matches
    (
    val awayFlag: AwayFlag,
    val awayPlayer: String,
    val awayPoint: String,
    val awayScores: AwayScores,
    val awayTiebreakScores: AwayTiebreakScores,
    val firstToServe: String,
    val homeFlag: HomeFlag,
    val homePlayer: String,
    val homePoint: String,
    val homeScores: HomeScores,
    val homeTiebreakScores: HomeTiebreakScores,
    val id: Int,
    val startTimestamp: Int,
    val status: String,
    val tournament: String,
    val tournamentCategory: String
    )
{
    var awaySeed = ""
    var homeSeed = ""

    init {
        getSeeds()
    }

    fun isLive(status: String): Boolean {
        return status == "inprogress"
    }

    private fun getSeeds()
    {
        // TODO: Implement this method
        this.awaySeed = ""
        this.homeSeed = ""
    }

    fun getResult(): String
    {
        var homeSets = 0
        var awaySets = 0

        homeSets += if (homeScores.one.toInt() > awayScores.one.toInt()) 1 else 0
        awaySets += if (homeScores.one.toInt() < awayScores.one.toInt()) 1 else 0

        homeSets += if (homeScores.two.toInt() > awayScores.two.toInt()) 1 else 0
        awaySets += if (homeScores.two.toInt() < awayScores.two.toInt()) 1 else 0

        homeSets += if (homeScores.three.toInt() > awayScores.three.toInt()) 1 else 0
        awaySets += if (homeScores.three.toInt() < awayScores.three.toInt()) 1 else 0

        homeSets += if (homeScores.four.toInt() > awayScores.four.toInt()) 1 else 0
        awaySets += if (homeScores.four.toInt() < awayScores.four.toInt()) 1 else 0

        homeSets += if (homeScores.five.toInt() > awayScores.five.toInt()) 1 else 0
        awaySets += if (homeScores.five.toInt() < awayScores.five.toInt()) 1 else 0

        return if (homeSets > awaySets) {
            "home"
        } else {
            "away"
        }
    }


    data class AwayFlag(val alpha2: String, val alpha3: String, val name: String)
    data class AwayScores(val one: String, val two: String, val three: String, val four: String, val five: String)
    data class AwayTiebreakScores(val one: String, val two: String, val three: String, val four: String, val five: String)
    data class HomeFlag(val alpha2: String, val alpha3: String, val name: String)
    data class HomeScores(val one: String, val two: String, val three: String, val four: String, val five: String)
    data class HomeTiebreakScores(val one: String, val two: String, val three: String, val four: String, val five: String)
}