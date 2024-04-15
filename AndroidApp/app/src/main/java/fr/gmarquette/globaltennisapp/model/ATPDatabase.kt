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
    entities = [Tournament::class, Seeds::class, Prize::class, Points::class, LastWinners::class],
    version = 1, exportSchema = true)
abstract class ATPDatabase : RoomDatabase() {

    abstract fun tournamentDAO(): TournamentDAO
    abstract fun seedsDAO(): SeedsDAO
    abstract fun prizeDAO(): PrizeDAO
    abstract fun pointsDAO(): PointsDAO
    abstract fun lastWinnersDAO(): LastWinnersDAO

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