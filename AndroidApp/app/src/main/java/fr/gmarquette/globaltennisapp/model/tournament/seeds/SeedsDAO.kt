/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.model.tournament.seeds

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface SeedsDAO
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSeed(seeds: Seeds)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllSeeds(seeds: List<Seeds>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateSeed(seeds: Seeds)

    @Query("SELECT * FROM seeds WHERE tournamentId = :tournamentId ORDER BY seed DESC")
    fun getSeedOfTournament(tournamentId: String): LiveData<List<Seeds>>
}