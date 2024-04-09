/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.api.interfaces

import fr.gmarquette.globaltennisapp.api.dataclasses.CalendarATP
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterfaceCalendar
{

    @GET("atp/tournaments")
    suspend fun getCalendarATP(): Response<List<CalendarATP>>


}