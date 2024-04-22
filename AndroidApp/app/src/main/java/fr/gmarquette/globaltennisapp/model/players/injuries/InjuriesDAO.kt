/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.model.players.injuries

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface InjuriesDAO
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addInjuries(injuries: Injuries)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllInjuries(injuries: List<Injuries>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateInjuries(injuries: Injuries)

    @Query("SELECT * FROM injuries WHERE playerId = :playerId ORDER BY date DESC")
    fun getInjuriesOfPlayer(playerId: String): LiveData<List<Injuries>>
}