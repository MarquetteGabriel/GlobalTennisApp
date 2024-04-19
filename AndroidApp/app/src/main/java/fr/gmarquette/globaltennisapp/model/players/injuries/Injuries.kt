/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.model.players.injuries

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import fr.gmarquette.globaltennisapp.model.players.Player

@Entity(tableName = "injuries")
data class Injuries(
    @PrimaryKey(autoGenerate = true) var id: Int,
    val injury: String,
    val date: String,
    val recoveryDate: String,
    val playerId: Int,
    @Ignore val player: Player? = null
)
{
    constructor(injury: String, date: String, recoveryDate: String, playerId: Int) : this(0, injury, date, recoveryDate, playerId, null)
}
