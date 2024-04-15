/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.api.dataclasses

data class RankingOfficial(
    val rank: String,
    val name: String,
    val points: String,
    val diff: String,
    val overview_url: String,
    val flag_url: String
)
