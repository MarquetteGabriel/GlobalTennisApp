/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.model.matches

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

    fun isLive(status: String): Boolean {
        return status == "inprogress"
    }

    fun getResult(): String
    {
        var homeSets = 0
        var awaySets = 0

        homeSets += if (homeScores.one > awayScores.one) 1 else 0
        awaySets += if (homeScores.one < awayScores.one) 1 else 0

        homeSets += if (homeScores.two > awayScores.two) 1 else 0
        awaySets += if (homeScores.two < awayScores.two) 1 else 0

        homeSets += if (homeScores.three > awayScores.three) 1 else 0
        awaySets += if (homeScores.three < awayScores.three) 1 else 0

        homeSets += if (homeScores.four > awayScores.four) 1 else 0
        awaySets += if (homeScores.four < awayScores.four) 1 else 0

        homeSets += if (homeScores.five > awayScores.five) 1 else 0
        awaySets += if (homeScores.five < awayScores.five) 1 else 0

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