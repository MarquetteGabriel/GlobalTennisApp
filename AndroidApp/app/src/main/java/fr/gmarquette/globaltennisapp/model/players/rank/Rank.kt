/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.model.players.rank

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import fr.gmarquette.globaltennisapp.model.players.Player

@Entity(tableName = "rank")
data class Rank(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var currentRank: Int,
    var previousRank: Int,
    var points: Int,
    var bestRank: Int,
    val playerId: Int,
    @Ignore val players: Player? = null
)
{
    constructor(currentRank: Int, previousRank: Int, points: Int, bestRank: Int, playerId: Int) : this(0, currentRank, previousRank, points, bestRank, playerId,null)
}
