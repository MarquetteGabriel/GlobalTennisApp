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

interface ApiInterfaceLeaderboardLive
{

    @GET("atp/rankings/singles/live/0-100")
    suspend fun getRanking100(): Response<List<RankingOfficial>>

    @GET("atp/rankings/singles/live/101-200")
    suspend fun getRanking200(): Response<List<RankingOfficial>>

    @GET("atp/rankings/singles/live/201-300")
    suspend fun getRanking300(): Response<List<RankingOfficial>>

    @GET("atp/rankings/singles/live/301-400")
    suspend fun getRanking400(): Response<List<RankingOfficial>>

    @GET("atp/rankings/singles/live/401-500")
    suspend fun getRanking500(): Response<List<RankingOfficial>>

    @GET("atp/rankings/singles/live/501-600")
    suspend fun getRanking600(): Response<List<RankingOfficial>>

    @GET("atp/rankings/singles/live/601-700")
    suspend fun getRanking700(): Response<List<RankingOfficial>>

    @GET("atp/rankings/singles/live/701-800")
    suspend fun getRanking800(): Response<List<RankingOfficial>>

    @GET("atp/rankings/singles/live/801-900")
    suspend fun getRanking900(): Response<List<RankingOfficial>>

    @GET("atp/rankings/singles/live/901-1000")
    suspend fun getRanking1000(): Response<List<RankingOfficial>>

    @GET("atp/rankings/singles/live/1001-1100")
    suspend fun getRanking1100(): Response<List<RankingOfficial>>

    @GET("atp/rankings/singles/live/1101-1200")
    suspend fun getRanking1200(): Response<List<RankingOfficial>>

    @GET("atp/rankings/singles/live/1201-1300")
    suspend fun getRanking1300(): Response<List<RankingOfficial>>

    @GET("atp/rankings/singles/live/1301-1400")
    suspend fun getRanking1400(): Response<List<RankingOfficial>>

    @GET("atp/rankings/singles/live/1401-1500")
    suspend fun getRanking1500(): Response<List<RankingOfficial>>

    @GET("atp/rankings/singles/live/1501-5000")
    suspend fun getRanking5000(): Response<List<RankingOfficial>>

}