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

    fun getTournament(name: String): LiveData<Tournament> {
        return tournamentDAO.getTournament(name)
    }
}