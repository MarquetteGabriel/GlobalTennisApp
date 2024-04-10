/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.view.calendar

import fr.gmarquette.globaltennisapp.model.tournament.TournamentViewModel

object CalendarObject {

        const val TYPE_HEADER = 0
        const val TYPE_ITEM = 1

        fun getItems(tournamentViewModel: TournamentViewModel): List<Any> {

                val itemList = mutableListOf<Any>()
                var tournamentList = tournamentViewModel.getTournaments().value!!

                tournamentList = tournamentList.sortedWith(
                        compareBy({ it.tournamentMonth },
                                { it.date.startDay.toInt() })
                )

                for (tournament in tournamentList)
                {
                        val headerExist = itemList.any { it is CalendarItems.Header && reverseMonth(it.month) == tournament.tournamentMonth }

                        if(!headerExist)
                        {
                                itemList.add(CalendarItems.Header(getMonth(tournament.tournamentMonth)))
                        }

                        val pastChamps = if(tournament.listLastWinners.isNotEmpty()) {
                                tournament.listLastWinners.first().name
                        } else {
                                "-"
                        }
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

        private fun getMonth(month: Int): String {
                return when (month) {
                        0 -> "December"
                        1 -> "January"
                        2 -> "February"
                        3 -> "March"
                        4 -> "April"
                        5 -> "May"
                        6 -> "June"
                        7 -> "July"
                        8 -> "August"
                        9 -> "September"
                        10 -> "October"
                        11 -> "November"
                        else -> "December"
                }
        }
        private fun reverseMonth(month: String): Int {
                return when (month) {
                        "December" -> 0
                        "January" -> 1
                        "February" -> 2
                        "March" -> 3
                        "April" -> 4
                        "May" -> 5
                        "June" -> 6
                        "July" -> 7
                        "August" -> 8
                        "September" -> 9
                        "October" -> 10
                        "November" -> 11
                        else -> 0
                }
        }
}