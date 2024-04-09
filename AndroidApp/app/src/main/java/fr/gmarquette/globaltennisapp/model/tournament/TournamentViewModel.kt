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

    fun getTournaments(): LiveData<List<Tournament>> {
        return getTournaments
    }

    fun getTournament(name: String): LiveData<Tournament> {
        return repository.getTournament(name)
    }
}