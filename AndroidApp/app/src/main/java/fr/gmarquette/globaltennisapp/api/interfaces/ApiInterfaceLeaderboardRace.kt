/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.api.interfaces

import fr.gmarquette.globaltennisapp.api.dataclasses.RankingOfficial
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterfaceLeaderboardRace
{

    @GET("atp/rankings/singles/race")
    suspend fun getRankingRace(): Response<List<RankingOfficial>>
}