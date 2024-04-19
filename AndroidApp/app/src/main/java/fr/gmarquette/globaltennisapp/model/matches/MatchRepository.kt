/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.model.matches

import androidx.lifecycle.LiveData

class MatchRepository(private val matchDAO: MatchDAO)
{

    val getMatchs: LiveData<List<Match>> = matchDAO.getMatchs()

    suspend fun addMatch(match: Match) {
        matchDAO.addMatch(match)
    }

    suspend fun addAllMatch(match: List<Match>) {
        matchDAO.addAllMatch(match)
    }

    suspend fun updateMatch(match: Match) {
        matchDAO.updateMatch(match)
    }

    fun getMatchById(matchId: Int): LiveData<Match> {
        return matchDAO.getMatchById(matchId)
    }
}