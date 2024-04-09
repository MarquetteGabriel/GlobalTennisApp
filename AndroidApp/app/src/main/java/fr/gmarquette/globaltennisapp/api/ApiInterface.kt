package fr.gmarquette.globaltennisapp.api

import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface
{
    @GET("api/")
    suspend fun getHome(): Response<Home>

    data class Home(val message: String)
}