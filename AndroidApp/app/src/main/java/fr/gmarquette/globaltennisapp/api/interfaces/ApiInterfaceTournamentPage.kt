/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.api.interfaces

import fr.gmarquette.globaltennisapp.api.dataclasses.TournamentResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterfaceTournamentPage
{

    @GET("atp/{tournamentId}")
    suspend fun getTournamentInfo(@Path("tournamentId") tournamendId: String): Response<TournamentResponse>

}