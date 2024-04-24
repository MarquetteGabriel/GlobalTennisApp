/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.model.tournament.points

import androidx.lifecycle.LiveData

class PointsRepository(private val pointsDAO: PointsDAO)
{
    suspend fun addPoints(points: Points) {
        pointsDAO.addPoints(points)
    }

    suspend fun addAllPoints(points: List<Points>) {
        pointsDAO.addAllPoints(points)
    }

    suspend fun updatePoints(points: Points) {
        pointsDAO.updatePoints(points)
    }

    fun getPointsOfTournament(tournamentId: String): LiveData<List<Points>> {
        return pointsDAO.getPointsOfTournament(tournamentId)
    }

    fun getPointsOfRound(tournamentId: String, round: Int): LiveData<List<Points>> {
        return pointsDAO.getPointsOfRound(tournamentId, round)
    }
}