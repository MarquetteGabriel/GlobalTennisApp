/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.model.tournament

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import fr.gmarquette.globaltennisapp.model.ATPDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TournamentViewModel(application: Application): AndroidViewModel(application) {

    private val getTournaments: LiveData<List<Tournament>>
    private val repository: TournamentRepository

    init {
        val tournamentDAO = ATPDatabase.getDatabase(application).tournamentDAO()
        repository = TournamentRepository(tournamentDAO)
        getTournaments = repository.getTournaments
    }

    fun addTournament(tournament: Tournament) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTournament(tournament)
        }
    }

    fun updateTournament(tournament: Tournament) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTournament(tournament)
        }
    }

    fun addOrUpdateTournament(tournament: Tournament) {
        viewModelScope.launch(Dispatchers.IO) {
            val tournamentExist = getTournamentByName(tournament.name).value
            if(tournamentExist == null) {
                addTournament(tournament)
            } else {
                updateTournament(tournament)
            }
        }
    }

    fun getTournaments(): LiveData<List<Tournament>> {
        return getTournaments
    }

    fun getTournamentByName(name: String): LiveData<Tournament> {
        return repository.getTournamentByName(name)
    }

    fun getLastWinnersOfTournament(tournamentId: String): List<LastWinners> {
        return repository.getLastWinnersOfTournament(tournamentId)
    }

    fun getSeedsOfTournament(tournamentId: String): List<Seeds> {
        return repository.getSeedsOfTournament(tournamentId)
    }

    fun getPrizeOfTournament(tournamentId: String): List<Prize> {
        return repository.getPrizeOfTournament(tournamentId)
    }

    fun getPointsOfTournament(tournamentId: String): List<Points> {
        return repository.getPointsOfTournament(tournamentId)
    }
}