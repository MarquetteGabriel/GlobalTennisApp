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

    @Query("SELECT * FROM tournaments WHERE id = :id")
    fun getTournamentById(id: String): LiveData<Tournament>

}
