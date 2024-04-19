/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import fr.gmarquette.globaltennisapp.model.matches.Match
import fr.gmarquette.globaltennisapp.model.matches.MatchDAO
import fr.gmarquette.globaltennisapp.model.matches.scores.Score
import fr.gmarquette.globaltennisapp.model.matches.scores.ScoreDAO
import fr.gmarquette.globaltennisapp.model.players.Player
import fr.gmarquette.globaltennisapp.model.players.PlayerDAO
import fr.gmarquette.globaltennisapp.model.players.injuries.Injuries
import fr.gmarquette.globaltennisapp.model.players.injuries.InjuriesDAO
import fr.gmarquette.globaltennisapp.model.players.rank.Rank
import fr.gmarquette.globaltennisapp.model.players.rank.RankDAO
import fr.gmarquette.globaltennisapp.model.tournament.Tournament
import fr.gmarquette.globaltennisapp.model.tournament.TournamentDAO
import fr.gmarquette.globaltennisapp.model.tournament.lastwinners.LastWinners
import fr.gmarquette.globaltennisapp.model.tournament.lastwinners.LastWinnersDAO
import fr.gmarquette.globaltennisapp.model.tournament.points.Points
import fr.gmarquette.globaltennisapp.model.tournament.points.PointsDAO
import fr.gmarquette.globaltennisapp.model.tournament.prize.Prize
import fr.gmarquette.globaltennisapp.model.tournament.prize.PrizeDAO
import fr.gmarquette.globaltennisapp.model.tournament.seeds.Seeds
import fr.gmarquette.globaltennisapp.model.tournament.seeds.SeedsDAO

@Database(
    entities = [Tournament::class, Seeds::class, Prize::class, Points::class, LastWinners::class, Player::class, Injuries::class, Rank::class, Match::class, Score::class],
    version = 1, exportSchema = true)
abstract class ATPDatabase : RoomDatabase() {

    abstract fun tournamentDAO(): TournamentDAO
    abstract fun seedsDAO(): SeedsDAO
    abstract fun prizeDAO(): PrizeDAO
    abstract fun pointsDAO(): PointsDAO
    abstract fun lastWinnersDAO(): LastWinnersDAO
    abstract fun playerDAO(): PlayerDAO
    abstract fun rankDAO(): RankDAO
    abstract fun injuriesDAO(): InjuriesDAO
    abstract fun matchDAO(): MatchDAO
    abstract fun scoreDAO(): ScoreDAO

    companion object {
        @Volatile
        private var INSTANCE: ATPDatabase? = null

        fun getDatabase(context: Context): ATPDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ATPDatabase::class.java,
                    "atp_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}