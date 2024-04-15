/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.model.tournament.prize

import androidx.lifecycle.LiveData

class PrizeRepository(private val prizeDAO: PrizeDAO)
{
    suspend fun addPrize(prize: Prize) {
        prizeDAO.addPrize(prize)
    }

    suspend fun addAllPrize(prize: List<Prize>) {
        prizeDAO.addAllPrize(prize)
    }

    suspend fun updatePrize(prize: Prize) {
        prizeDAO.updatePrize(prize)
    }

    fun getPrizeOfTournament(tournamentId: String): LiveData<List<Prize>> {
        return prizeDAO.getPrizeOfTournament(tournamentId)
    }
}