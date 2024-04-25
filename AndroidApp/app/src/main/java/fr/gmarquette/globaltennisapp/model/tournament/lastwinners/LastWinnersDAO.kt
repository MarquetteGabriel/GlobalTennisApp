/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.model.tournament.lastwinners

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface LastWinnersDAO
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLastWinners(lastWinners: LastWinners)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllLastWinners(lastWinners: List<LastWinners>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateLastWinners(lastWinners: LastWinners)

    @Query("SELECT * FROM last_winners WHERE tournamentId = :tournamentId ORDER BY year DESC")
    suspend fun getLastWinnersOfTournament(tournamentId: String): List<LastWinners>
}