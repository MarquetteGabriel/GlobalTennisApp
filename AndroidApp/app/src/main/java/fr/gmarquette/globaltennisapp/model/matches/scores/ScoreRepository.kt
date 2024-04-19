/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.model.scores.score

import androidx.lifecycle.LiveData
import fr.gmarquette.globaltennisapp.model.matches.scores.Score
import fr.gmarquette.globaltennisapp.model.matches.scores.ScoreDAO

class ScoreRepository(private val scoreDAO: ScoreDAO)
{
    suspend fun addScore(score: Score) {
        scoreDAO.addScore(score)
    }

    suspend fun addAllScore(score: List<Score>) {
        scoreDAO.addAllScore(score)
    }

    suspend fun updateScore(score: Score) {
        scoreDAO.updateScore(score)
    }

    fun getScoreOfMatch(matchId: String): LiveData<Score> {
        return scoreDAO.getScoreOfMatch(matchId)
    }
}