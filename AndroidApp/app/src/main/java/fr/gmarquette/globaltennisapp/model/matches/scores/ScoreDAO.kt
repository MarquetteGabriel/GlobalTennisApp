/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.model.matches.scores

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ScoreDAO
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addScore(score: Score)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllScore(score: List<Score>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateScore(score: Score)

    @Query("SELECT * FROM scores WHERE matchId = :matchId")
    fun getScoreOfMatch(matchId: String): LiveData<Score>
}