/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.model.tournament

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface TournamentDAO
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTournament(tournament: Tournament)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTournament(tournament: Tournament)

    


    @Query("SELECT * FROM tournaments")
    fun getTournaments(): LiveData<List<Tournament>>

    @Query("SELECT * FROM tournaments WHERE name = :name")
    fun getTournamentByName(name: String): LiveData<Tournament>

    @Query("SELECT * FROM last_winners WHERE tournamentId = :tournamentId ORDER BY year DESC")
    fun getLastWinnersOfTournament(tournamentId: String): List<LastWinners>

    @Query("SELECT * FROM seeds WHERE tournamentId = :tournamentId ORDER BY seed ASC")
    fun getSeedsOfTournament(tournamentId: String): List<Seeds>

    @Query("SELECT * FROM prizes WHERE tournamentId = :tournamentId ORDER BY prize ASC")
    fun getPrizeOfTournament(tournamentId: String): List<Prize>

    @Query("SELECT * FROM points WHERE tournamentId = :tournamentId ORDER BY points ASC")
    fun getPointsOfTournament(tournamentId: String): List<Points>

}
