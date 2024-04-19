/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.model.matches

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface MatchDAO
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMatch(match: Match)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllMatch(match: List<Match>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateMatch(match: Match)

    @Query("SELECT * FROM matches")
    fun getMatchs(): LiveData<List<Match>>

    @Query("SELECT * FROM matches WHERE id = :matchId")
    fun getMatchById(matchId: Int): LiveData<Match>
}