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
import fr.gmarquette.globaltennisapp.model.tournament.LastWinners
import fr.gmarquette.globaltennisapp.model.tournament.Points
import fr.gmarquette.globaltennisapp.model.tournament.Prize
import fr.gmarquette.globaltennisapp.model.tournament.Seeds
import fr.gmarquette.globaltennisapp.model.tournament.Tournament
import fr.gmarquette.globaltennisapp.model.tournament.TournamentDAO

@Database(
    entities = [Tournament::class, Seeds::class, Prize::class, Points::class, LastWinners::class],
    version = 1, exportSchema = true)
abstract class ATPDatabase : RoomDatabase() {

    abstract fun tournamentDAO(): TournamentDAO

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