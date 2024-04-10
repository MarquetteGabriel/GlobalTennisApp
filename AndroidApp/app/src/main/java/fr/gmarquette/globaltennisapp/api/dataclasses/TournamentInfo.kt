/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.api.dataclasses

 data class TournamentInfo(
        val DoubleDrawSize: Int,
        val IndoorOutdoor: String,
        val Location: String,
        val Name: String,
        val SingleDrawSize: Int,
        val Surface: String,
        val PastChampions: List<PastWinners>,
        val TopSeeds: List<Seeds>,
        val PrizeAndPoints: List<PrizeAndPoint>

    )
 {

    data class PastWinners(
        val FirstName: String,
        val LastName: String,
        val Year: Int
    )

    data class Seeds(
        val PlayerName: String,
        val Seed: Int
    )

    data class PrizeAndPoint(
        val Points: Int,
        val PrizeMoney: Int,
        val Round: String
    )
}

