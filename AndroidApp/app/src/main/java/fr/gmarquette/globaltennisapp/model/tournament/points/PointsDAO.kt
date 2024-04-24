/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.model.tournament.points

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface PointsDAO
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPoints(points: Points)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllPoints(points: List<Points>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updatePoints(points: Points)

    @Query("SELECT * FROM points WHERE tournamentId = :tournamentId ORDER BY points DESC")
    fun getPointsOfTournament(tournamentId: String): LiveData<List<Points>>

    @Query("SELECT * FROM points WHERE tournamentId = :tournamentId AND round = :round")
    fun getPointsOfRound(tournamentId: String, round: Int): LiveData<List<Points>>

}