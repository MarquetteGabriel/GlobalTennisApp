/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.api.dataclasses

data class CalendarATP(
    val FormattedDate: String,
    val Id: String,
    val Location: String,
    val Name: String,
    val OverviewUrl: String,
    val Type: String,
    val url_tournament: String
)
