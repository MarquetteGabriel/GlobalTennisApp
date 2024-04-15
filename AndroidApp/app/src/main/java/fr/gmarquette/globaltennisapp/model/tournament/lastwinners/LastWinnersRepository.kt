/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.model.tournament.lastwinners

import androidx.lifecycle.LiveData

class LastWinnersRepository(private val lastWinnersDAO: LastWinnersDAO)
{
    suspend fun addLastWinner(lastWinners: LastWinners) {
        lastWinnersDAO.addLastWinners(lastWinners)
    }

    suspend fun addAllLastWinner(lastWinners: List<LastWinners>) {
        lastWinnersDAO.addAllLastWinners(lastWinners)
    }

    suspend fun updateLastWinners(lastWinners: LastWinners) {
        lastWinnersDAO.updateLastWinners(lastWinners)
    }

    fun getLastWinnersOfTournament(tournamentId: String): LiveData<List<LastWinners>> {
        return lastWinnersDAO.getLastWinnersOfTournament(tournamentId)
    }
}