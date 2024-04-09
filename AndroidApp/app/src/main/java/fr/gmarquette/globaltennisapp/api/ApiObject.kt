/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.api

import fr.gmarquette.globaltennisapp.api.interfaces.ApiInterface
import fr.gmarquette.globaltennisapp.api.interfaces.ApiInterfaceCalendar
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiObject
{
    private const val BASE_URL = "http://127.0.0.1:2607/api/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val homeApi: ApiInterface = retrofit.create(ApiInterface::class.java)
    val calendarApi: ApiInterfaceCalendar = retrofit.create(ApiInterfaceCalendar::class.java)
}