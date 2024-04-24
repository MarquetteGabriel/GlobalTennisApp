/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.api.interfaces

import fr.gmarquette.globaltennisapp.model.matches.Match
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterfaceLiveScore
{
    @GET("/atp/live-score")
    suspend fun getLiveScore(): Response<List<Match>>
}