/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.view

sealed class CalendarItems
{
    data class Item(val tournamentName: String, val detailsTournaments: String, val surfaceTournament: String, val lastWinner: String, val logo: Int) : CalendarItems()
    data class Header(val month: String) : CalendarItems()
}