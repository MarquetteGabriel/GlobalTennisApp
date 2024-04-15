/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.model.tournament.seeds

import androidx.lifecycle.LiveData

class SeedsRepository(private val seedsDAO: SeedsDAO)
{
    suspend fun addSeeds(seeds: Seeds) {
        seedsDAO.addSeed(seeds)
    }

    suspend fun addAllSeeds(seeds: List<Seeds>) {
        seedsDAO.addAllSeeds(seeds)
    }

    suspend fun updateSeeds(seeds: Seeds) {
        seedsDAO.updateSeed(seeds)
    }

    fun getSeedsOfTournament(tournamentId: String): LiveData<List<Seeds>> {
        return seedsDAO.getSeedOfTournament(tournamentId)
    }
}