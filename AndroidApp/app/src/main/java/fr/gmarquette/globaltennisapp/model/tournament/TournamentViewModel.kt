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

    fun addOrUpdateAllLastWinners(lastWinners: List<LastWinners>) {
        viewModelScope.launch(Dispatchers.IO) {
            lastWinners.forEach {
                val lastWinnerExist = tournamentRepository.getTournamentById(it.tournamentId.toString()).value
                if(lastWinnerExist == null) {
                    lastWinnersRepository.addLastWinner(it)
                } else {
                    lastWinnersRepository.updateLastWinners(it)
                }
            }
        }
    }

    fun addOrUpdateAllSeeds(seeds: List<Seeds>) {
        viewModelScope.launch(Dispatchers.IO) {
            seeds.forEach {
                val seedExist = tournamentRepository.getTournamentById(it.tournamentId.toString()).value
                if(seedExist == null) {
                    seedsRepository.addSeeds(it)
                } else {
                    seedsRepository.updateSeeds(it)
                }
            }
        }
    }

    fun addOrUpdateAllPrize(prize: List<Prize>) {
        viewModelScope.launch(Dispatchers.IO) {
            prize.forEach {
                val prizeExist = tournamentRepository.getTournamentById(it.tournamentId.toString()).value
                if(prizeExist == null) {
                    prizeRepository.addPrize(it)
                } else {
                    prizeRepository.updatePrize(it)
                }
            }
        }
    }
    fun addOrUpdateAllPoints(points: List<Points>) {
        viewModelScope.launch(Dispatchers.IO) {
            points.forEach {
                val pointsOfRound = pointsRepository.getPointsOfTournament(it.tournamentId.toString()).value
                if(pointsOfRound.isNullOrEmpty()) {
                    pointsRepository.addPoints(it)
                } else {
                    val pointExist = pointsOfRound.find { point -> point.round == it.round }
                    if(pointExist == null) {
                        pointsRepository.addPoints(it)
                    } else {
                        pointsRepository.updatePoints(it)
                    }
                }
            }
        }
    }

    fun getTournaments(): LiveData<List<Tournament>> {
        return getTournaments
    }

    fun getTournamentByName(name: String): LiveData<Tournament> {
        return tournamentRepository.getTournamentByName(name)
    }
}