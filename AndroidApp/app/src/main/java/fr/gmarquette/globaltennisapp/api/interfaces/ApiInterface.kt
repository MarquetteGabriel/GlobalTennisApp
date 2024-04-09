/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.api.interfaces

import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface
{
    @GET("atp/")
    suspend fun getHome(): Response<Home>

    data class Home(val message: String)
}