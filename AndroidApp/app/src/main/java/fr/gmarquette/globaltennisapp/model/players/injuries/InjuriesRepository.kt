/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.model.players.injuries

import androidx.lifecycle.LiveData
import fr.gmarquette.globaltennisapp.model.players.injuries.Injuries
import fr.gmarquette.globaltennisapp.model.players.injuries.InjuriesDAO

class InjuriesRepository(private val injuriesDAO: InjuriesDAO)
{
    suspend fun addInjuries(injuries: Injuries) {
        injuriesDAO.addInjuries(injuries)
    }

    suspend fun addAllInjuries(injuries: List<Injuries>) {
        injuriesDAO.addAllInjuries(injuries)
    }

    suspend fun updateInjuries(injuries: Injuries) {
        injuriesDAO.updateInjuries(injuries)
    }

    fun getInjuriesOfPlayer(playerId: String): LiveData<List<Injuries>> {
        return injuriesDAO.getInjuriesOfPlayer(playerId)
    }
}