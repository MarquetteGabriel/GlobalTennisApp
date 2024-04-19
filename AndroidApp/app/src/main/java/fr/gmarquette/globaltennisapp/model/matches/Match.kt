/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.model.matches

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import fr.gmarquette.globaltennisapp.model.enums.MatchStatus
import fr.gmarquette.globaltennisapp.model.matches.scores.Score
import fr.gmarquette.globaltennisapp.model.players.Player
import fr.gmarquette.globaltennisapp.model.tournament.Tournament
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "matches")
data class Match
(
    @PrimaryKey(autoGenerate = true) val id: Int,

    val homePlayerId: Int,
    @Ignore @IgnoredOnParcel val homePlayer: Player? = null,

    val awayPlayerId: Int,
    @Ignore @IgnoredOnParcel val awayPlayer: Player? = null,

    val tournamentId: Int,
    @Ignore @IgnoredOnParcel val tournament: Tournament? = null,

    val firstToServe: String,
    val status: MatchStatus,
    val startTimestamp: Int,

    val homeSeed: Int,
    val awaySeed: Int,

    val homePoint: String,
    val awayPoint: String,

    @Ignore @IgnoredOnParcel val score: Score? = null

) : Parcelable
{

    fun getResult(): String
    {
        var homeSets = 0
        var awaySets = 0

        homeSets += if (score!!.homeOne.toInt() > score.awayOne.toInt()) 1 else 0
        awaySets += if (score.homeOne.toInt() < score.awayOne.toInt()) 1 else 0

        homeSets += if (score.homeTwo.toInt() > score.awayTwo.toInt()) 1 else 0
        awaySets += if (score.homeTwo.toInt() < score.awayTwo.toInt()) 1 else 0

        homeSets += if (score.homeThree.toInt() > score.awayThree.toInt()) 1 else 0
        awaySets += if (score.homeThree.toInt() < score.awayThree.toInt()) 1 else 0

        homeSets += if (score.homeFour.toInt() > score.awayFour.toInt()) 1 else 0
        awaySets += if (score.homeFour.toInt() < score.awayFour.toInt()) 1 else 0

        homeSets += if (score.homeFive.toInt() > score.awayFive.toInt()) 1 else 0
        awaySets += if (score.homeFive.toInt() < score.awayFive.toInt()) 1 else 0

        return if (homeSets > awaySets) {
            "home"
        } else {
            "away"
        }
    }
}