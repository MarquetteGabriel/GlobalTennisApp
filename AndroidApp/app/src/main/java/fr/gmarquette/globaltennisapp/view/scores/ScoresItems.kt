/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.view.scores

import fr.gmarquette.globaltennisapp.model.matches.Match

sealed class ScoresItems
{
    data class Item(val match: Match) : ScoresItems()
    data class Header(val tournamentName: String) : ScoresItems()
}