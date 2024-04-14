/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.api.dataclasses

data class RankingOfficial(
    val rank: Int,
    val name: String,
    val points: Int,
    val diff: Int,
    val overview_url: String,
    val flag_url: String
)
