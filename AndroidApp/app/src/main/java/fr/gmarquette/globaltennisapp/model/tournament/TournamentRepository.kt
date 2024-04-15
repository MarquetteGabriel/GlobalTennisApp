/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.model.tournament

import androidx.lifecycle.LiveData

class TournamentRepository(private val tournamentDAO: TournamentDAO)
{
    val getTournaments: LiveData<List<Tournament>> = tournamentDAO.getTournaments()

    suspend fun addTournament(tournament: Tournament) {
        tournamentDAO.addTournament(tournament)
    }

    suspend fun updateTournament(tournament: Tournament) {
        tournamentDAO.updateTournament(tournament)
    }

    fun getTournamentByName(name: String): LiveData<Tournament> {
        return tournamentDAO.getTournamentByName(name)
    }

    fun getLastWinnersOfTournament(tournamentId: String): List<LastWinners> {
        return tournamentDAO.getLastWinnersOfTournament(tournamentId)
    }

    fun getSeedsOfTournament(tournamentId: String): List<Seeds> {
        return tournamentDAO.getSeedsOfTournament(tournamentId)
    }

    fun getPrizeOfTournament(tournamentId: String): List<Prize> {
        return tournamentDAO.getPrizeOfTournament(tournamentId)
    }

    fun getPointsOfTournament(tournamentId: String): List<Points> {
        return tournamentDAO.getPointsOfTournament(tournamentId)
    }
}