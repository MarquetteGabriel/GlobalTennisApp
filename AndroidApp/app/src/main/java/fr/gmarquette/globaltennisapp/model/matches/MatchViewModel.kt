/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.model.matches

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import fr.gmarquette.globaltennisapp.model.ATPDatabase
import fr.gmarquette.globaltennisapp.model.matches.scores.Score
import fr.gmarquette.globaltennisapp.model.scores.score.ScoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MatchViewModel(application: Application): AndroidViewModel(application) {

    private val getMatchs: LiveData<List<Match>>
    private val matchRepository: MatchRepository
    private val scoreRepository: ScoreRepository

    init {
        val database = ATPDatabase.getDatabase(application)
        matchRepository = MatchRepository(database.matchDAO())
        scoreRepository = ScoreRepository(database.scoreDAO())
        getMatchs = matchRepository.getMatchs
    }

    fun addMatch(match: Match) {
        viewModelScope.launch(Dispatchers.IO) {
            matchRepository.addMatch(match)
        }
    }

    fun updateMatch(match: Match) {
        viewModelScope.launch(Dispatchers.IO) {
            matchRepository.updateMatch(match)
        }
    }

    fun addScore(score: Score) {
        viewModelScope.launch(Dispatchers.IO) {
            scoreRepository.addScore(score)
        }
    }

    fun addAllScore(score: List<Score>) {
        viewModelScope.launch(Dispatchers.IO) {
            scoreRepository.addAllScore(score)
        }
    }

    fun updateScore(score: Score) {
        viewModelScope.launch(Dispatchers.IO) {
            scoreRepository.updateScore(score)
        }
    }

    fun addOrUpdateMatch(match: Match) {
        viewModelScope.launch(Dispatchers.IO) {
            val matchExist = getMatchById(match.id).value
            if(matchExist == null) {
                addMatch(match)
            } else {
                updateMatch(match)
            }
        }
    }

    fun getMatchById(matchId: Int): LiveData<Match> {
        return matchRepository.getMatchById(matchId)
    }

    fun getMatchs(): LiveData<List<Match>> {
        return getMatchs
    }
    
    fun getScoreOfMatch(matchId: String): LiveData<Score> {
        return scoreRepository.getScoreOfMatch(matchId)
    }
}