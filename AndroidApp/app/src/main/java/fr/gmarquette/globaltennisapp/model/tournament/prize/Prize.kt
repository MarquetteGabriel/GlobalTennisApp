/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.model.tournament.prize

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import fr.gmarquette.globaltennisapp.model.tournament.Tournament

@Entity(tableName = "prizes")
data class Prize(
    @PrimaryKey(autoGenerate = true) var id: Int,
    val round: String,
    val prize: Int,
    val tournamentId: Int,
    @Ignore val tournament: Tournament? = null)
{
    constructor(round: String, prize: Int, tournamentId: Int) : this(0, round, prize, tournamentId, null)
}

