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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlayerViewModel(application: Application): AndroidViewModel(application) {

    private val getPlayers: LiveData<List<Player>>
    private val repository: PlayerRepository

    init {
        val playerDAO = ATPDatabase.getDatabase(application).playerDAO()
        repository = PlayerRepository(playerDAO)
        getPlayers = repository.getPlayers
    }

    fun addPlayer(player: Player) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPlayer(player)
        }
    }

    fun updatePlayer(player: Player) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updatePlayer(player)
        }
    }

    fun addOrUpdatePlayers(player: Player) {
        viewModelScope.launch(Dispatchers.IO) {
            val playerExist = getPlayerByName(player.fullName).value
            if(playerExist == null) {
                addPlayer(player)
            } else {
                updatePlayer(player)
            }
        }
    }

    fun getPlayers(): LiveData<List<Player>> {
        return getPlayers
    }

    fun getPlayerByName(name: String): LiveData<Player> {
        return repository.getPlayerByName(name)
    }
}