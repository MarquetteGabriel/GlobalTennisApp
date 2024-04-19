/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.model.ranks.rank

import androidx.lifecycle.LiveData
import fr.gmarquette.globaltennisapp.model.players.rank.Rank
import fr.gmarquette.globaltennisapp.model.players.rank.RankDAO

class RankRepository(private val rankDAO: RankDAO)
{
    suspend fun addRank(rank: Rank) {
        rankDAO.addRank(rank)
    }

    suspend fun addAllRank(rank: List<Rank>) {
        rankDAO.addAllRank(rank)
    }

    suspend fun updateRank(rank: Rank) {
        rankDAO.updateRank(rank)
    }

    fun getRankOfPlayer(playerId: String): LiveData<List<Rank>> {
        return rankDAO.getRankOfPlayer(playerId)
    }
}