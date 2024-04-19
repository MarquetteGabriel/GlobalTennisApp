/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.model.players

import androidx.lifecycle.LiveData

class PlayerRepository(private val playerDAO: PlayerDAO)
{

    val getPlayers: LiveData<List<Player>> = playerDAO.getPlayers()

    suspend fun addPlayer(player: Player) {
        playerDAO.addPlayer(player)
    }

    suspend fun addAllPlayer(player: List<Player>) {
        playerDAO.addAllPlayer(player)
    }

    suspend fun updatePlayer(player: Player) {
        playerDAO.updatePlayer(player)
    }

    fun getPlayerById(playerId: Int): LiveData<Player> {
        return playerDAO.getPlayerById(playerId)
    }
}