/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.view.calendar

import fr.gmarquette.globaltennisapp.model.tournament.TournamentViewModel
import java.util.Calendar
import java.util.Date

object CalendarObject {

        const val TYPE_HEADER = 0
        const val TYPE_ITEM = 1

        fun getItems(tournamentViewModel: TournamentViewModel): List<Any> {

                val itemList = mutableListOf<Any>()
                val tournamentList = tournamentViewModel.getTournaments().value!!
                var currentMonth: String? = null

                for (tournament in tournamentList.sortedBy { it.startDate }) {
                        val month = getMonth(tournament.startDate)
                        if (currentMonth != month) {
                                currentMonth = month
                                itemList.add(CalendarItems.Header(month))
                        }

                        val pastChamps = tournamentViewModel.getLastWinnersOfTournament(tournament.id)
                                ?.takeIf { it.isNotEmpty() }
                                ?.map { "🏆 ${it.year} - ${it.name}" }
                                ?.first()
                                ?: ""

                        itemList.add(
                                CalendarItems.Item(
                                        tournament.name,
                                        tournament.formattedDate + " - " + tournament.location,
                                        tournament.surface + " - " + tournament.indoorOutdoor,
                                        pastChamps,
                                        tournament.type.logo
                                )
                        )
                }

                return itemList
        }

        private fun getMonth(date: Date?): String {
                val calendar = Calendar.getInstance()
                calendar.time = date ?: Date()
                return when (calendar.get(Calendar.MONTH)) {
                        0 -> "January"
                        1 -> "February"
                        2 -> "March"
                        3 -> "April"
                        4 -> "May"
                        5 -> "June"
                        6 -> "July"
                        7 -> "August"
                        8 -> "September"
                        9 -> "October"
                        10 -> "November"
                        11 -> "December"
                        else -> "January"
                }
        }
}