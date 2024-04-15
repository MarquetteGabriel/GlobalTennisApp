/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.model.tournament.seeds

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import fr.gmarquette.globaltennisapp.model.tournament.Tournament

@Entity(tableName = "seeds")
data class Seeds(
    @PrimaryKey(autoGenerate = true) var id: Int,
    val name: String,
    val seed: Int,
    val tournamentId: Int,
    @Ignore val tournament: Tournament? = null)
{
    constructor(name: String, seed: Int, tournamentId: Int) : this(0, name, seed, tournamentId, null)
}