/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.model.players.rank

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface RankDAO
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRank(rank: Rank)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRank(rank: List<Rank>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateRank(rank: Rank)

    @Query("SELECT * FROM rank WHERE playerId = :playerId ORDER BY points DESC")
    fun getRankOfPlayer(playerId: String): LiveData<List<Rank>>
}