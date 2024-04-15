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

@Entity(tableName = "points")
data class Points(
    @PrimaryKey(autoGenerate = true) var id: Int,
    val round: String,
    val points: Int,
    val tournamentId: Int,
    @Ignore val tournament: Tournament? = null)
{
    constructor(round: String, points: Int, tournamentId: Int) : this(0, round, points, tournamentId, null)
}
