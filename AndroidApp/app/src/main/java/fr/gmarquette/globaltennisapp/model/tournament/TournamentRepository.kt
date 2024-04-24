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

    fun getTournamentById(id: String): LiveData<Tournament> {
        return tournamentDAO.getTournamentById(id)
    }
}