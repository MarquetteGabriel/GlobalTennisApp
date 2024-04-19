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
import fr.gmarquette.globaltennisapp.model.tournament.lastwinners.LastWinners
import fr.gmarquette.globaltennisapp.model.tournament.lastwinners.LastWinnersRepository
import fr.gmarquette.globaltennisapp.model.tournament.points.Points
import fr.gmarquette.globaltennisapp.model.tournament.points.PointsRepository
import fr.gmarquette.globaltennisapp.model.tournament.prize.Prize
import fr.gmarquette.globaltennisapp.model.tournament.prize.PrizeRepository
import fr.gmarquette.globaltennisapp.model.tournament.seeds.Seeds
import fr.gmarquette.globaltennisapp.model.tournament.seeds.SeedsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TournamentViewModel(application: Application): AndroidViewModel(application) {

    private val getTournaments: LiveData<List<Tournament>>
    private val tournamentRepository: TournamentRepository
    private val lastWinnersRepository: LastWinnersRepository
    private val seedsRepository: SeedsRepository
    private val prizeRepository: PrizeRepository
    private val pointsRepository: PointsRepository

    init {
        val database = ATPDatabase.getDatabase(application)
        tournamentRepository = TournamentRepository(database.tournamentDAO())
        lastWinnersRepository = LastWinnersRepository(database.lastWinnersDAO())
        seedsRepository = SeedsRepository(database.seedsDAO())
        prizeRepository = PrizeRepository(database.prizeDAO())
        pointsRepository = PointsRepository(database.pointsDAO())
        getTournaments = tournamentRepository.getTournaments
    }

    fun addTournament(tournament: Tournament) {
        viewModelScope.launch(Dispatchers.IO) {
            tournamentRepository.addTournament(tournament)
        }
    }

    fun updateTournament(tournament: Tournament) {
        viewModelScope.launch(Dispatchers.IO) {
            tournamentRepository.updateTournament(tournament)
        }
    }

    fun addLastWinners(lastWinners: LastWinners) {
        viewModelScope.launch(Dispatchers.IO) {
            lastWinnersRepository.addLastWinner(lastWinners)
        }
    }

    fun addAllLastWinners(lastWinners: List<LastWinners>) {
        viewModelScope.launch(Dispatchers.IO) {
            lastWinnersRepository.addAllLastWinner(lastWinners)
        }
    }

    fun updateLastWinners(lastWinners: LastWinners) {
        viewModelScope.launch(Dispatchers.IO) {
            lastWinnersRepository.updateLastWinners(lastWinners)
        }
    }

    fun addSeeds(seeds: Seeds) {
        viewModelScope.launch(Dispatchers.IO) {
            seedsRepository.addSeeds(seeds)
        }
    }

    fun addAllSeeds(seeds: List<Seeds>) {
        viewModelScope.launch(Dispatchers.IO) {
            seedsRepository.addAllSeeds(seeds)
        }
    }

    fun updateSeeds(seeds: Seeds) {
        viewModelScope.launch(Dispatchers.IO) {
            seedsRepository.updateSeeds(seeds)
        }
    }

    fun addPrize(prize: Prize) {
        viewModelScope.launch(Dispatchers.IO) {
            prizeRepository.addPrize(prize)
        }
    }

    fun addAllPrize(prize: List<Prize>) {
        viewModelScope.launch(Dispatchers.IO) {
            prizeRepository.addAllPrize(prize)
        }
    }

    fun updatePrize(prize: Prize) {
        viewModelScope.launch(Dispatchers.IO) {
            prizeRepository.updatePrize(prize)
        }
    }

    fun addPoints(points: Points) {
        viewModelScope.launch(Dispatchers.IO) {
            pointsRepository.addPoints(points)
        }
    }

    fun addAllPoints(points: List<Points>) {
        viewModelScope.launch(Dispatchers.IO) {
            pointsRepository.addAllPoints(points)
        }
    }

    fun updatePoints(points: Points) {
        viewModelScope.launch(Dispatchers.IO) {
            pointsRepository.updatePoints(points)
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
        return tournamentRepository.getTournamentByName(name)
    }
    
    fun getLastWinnersOfTournament(tournamentId: String): LiveData<List<LastWinners>> {
        return lastWinnersRepository.getLastWinnersOfTournament(tournamentId)
    }

    fun getSeedsOfTournament(tournamentId: String): LiveData<List<Seeds>> {
        return seedsRepository.getSeedsOfTournament(tournamentId)
    }

    fun getPrizeOfTournament(tournamentId: String): LiveData<List<Prize>> {
        return prizeRepository.getPrizeOfTournament(tournamentId)
    }

    fun getPointsOfTournament(tournamentId: String): LiveData<List<Points>> {
        return pointsRepository.getPointsOfTournament(tournamentId)
    }
}