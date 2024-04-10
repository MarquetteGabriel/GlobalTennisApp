/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.api.dataclasses


data class TournamentResponse(
    val tournamentInfo: TournamentInfo,
    val pastWinners: List<PastWinners>,
    val seeds: List<Seeds>,
    val prizeAndPoints: List<PrizeAndPoints>
)
{
    data class TournamentInfo(
        val DoubleDrawSize: Int,
        val IndoorOutdoor: String,
        val Location: String,
        val Name: String,
        val SinglesDrawSize: Int,
        val Surface: String,

    )

    data class PastWinners(
        val FirstName: String,
        val LastName: String,
        val Year: Int
    )

    data class Seeds(
        val PlayerName: String,
        val Seed: Int
    )

    data class PrizeAndPoints(
        val Points: Int,
        val PrizeMoney: Int,
        val Round: String
    )
}

