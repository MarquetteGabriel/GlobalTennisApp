/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.model.tournament

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "last_winners")
data class LastWinners(
    @PrimaryKey(autoGenerate = true) var id: Int,
    val name: String,
    val year: Int,
    val tournamentId: Int,
    @Ignore val tournament: Tournament? = null
)
{
    constructor(name: String, year: Int, tournamentId: Int) : this(0, name, year, tournamentId, null)
}
