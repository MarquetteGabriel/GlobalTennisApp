/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.model.players

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import fr.gmarquette.globaltennisapp.model.ATPDatabase
import fr.gmarquette.globaltennisapp.model.players.injuries.Injuries
import fr.gmarquette.globaltennisapp.model.players.injuries.InjuriesRepository
import fr.gmarquette.globaltennisapp.model.players.rank.Rank
import fr.gmarquette.globaltennisapp.model.players.rank.RankRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlayerViewModel(application: Application): AndroidViewModel(application) {

    private val getPlayers: LiveData<List<Player>>
    private val playerRepository: PlayerRepository
    private val injuriesRepository: InjuriesRepository
    private val rankRepository: RankRepository

    init {
        val database = ATPDatabase.getDatabase(application)
        playerRepository = PlayerRepository(database.playerDAO())
        injuriesRepository = InjuriesRepository(database.injuriesDAO())
        rankRepository = RankRepository(database.rankDAO())
        getPlayers = playerRepository.getPlayers
    }

    fun addPlayer(player: Player) {
        viewModelScope.launch(Dispatchers.IO) {
            playerRepository.addPlayer(player)
        }
    }

    fun updatePlayer(player: Player) {
        viewModelScope.launch(Dispatchers.IO) {
            playerRepository.updatePlayer(player)
        }
    }

    fun addInjuries(injuries: Injuries) {
        viewModelScope.launch(Dispatchers.IO) {
            injuriesRepository.addInjuries(injuries)
        }
    }

    fun addAllInjuries(injuries: List<Injuries>) {
        viewModelScope.launch(Dispatchers.IO) {
            injuriesRepository.addAllInjuries(injuries)
        }
    }

    fun updateInjuries(injuries: Injuries) {
        viewModelScope.launch(Dispatchers.IO) {
            injuriesRepository.updateInjuries(injuries)
        }
    }

    fun addRank(rank: Rank) {
        viewModelScope.launch(Dispatchers.IO) {
            rankRepository.addRank(rank)
        }
    }

    fun addAllRank(rank: List<Rank>) {
        viewModelScope.launch(Dispatchers.IO) {
            rankRepository.addAllRank(rank)
        }
    }

    fun updateRank(rank: Rank) {
        viewModelScope.launch(Dispatchers.IO) {
            rankRepository.updateRank(rank)
        }
    }

    fun addOrUpdatePlayer(player: Player) {
        viewModelScope.launch(Dispatchers.IO) {
            val playerExist = getPlayerById(player.id).value
            if(playerExist == null) {
                addPlayer(player)
            } else {
                updatePlayer(player)
            }
        }
    }

    fun getPlayerById(playerId: Int): LiveData<Player> {
        return playerRepository.getPlayerById(playerId)
    }

    fun getPlayers(): LiveData<List<Player>> {
        return getPlayers
    }
    
    fun getInjuriesOfPlayer(playerId: String): LiveData<List<Injuries>> {
        return injuriesRepository.getInjuriesOfPlayer(playerId)
    }

    fun getRankOfPlayer(playerId: String): LiveData<List<Rank>> {
        return rankRepository.getRankOfPlayer(playerId)
    }
}
