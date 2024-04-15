/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.model.tournament.prize

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface PrizeDAO
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPrize(prize: Prize)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllPrize(prize: List<Prize>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updatePrize(prize: Prize)

    @Query("SELECT * FROM prizes WHERE tournamentId = :tournamentId ORDER BY prize DESC")
    fun getPrizeOfTournament(tournamentId: String): LiveData<List<Prize>>
}