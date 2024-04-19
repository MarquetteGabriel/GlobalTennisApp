/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.model.enums

import fr.gmarquette.globaltennisapp.R

enum class MatchStatus(val description: String)
{
    NOT_SCHEDULED(R.string.not_scheduled.toString()),
    SCHEDULED(R.string.scheduled.toString()),
    LIVE(R.string.live.toString()),
    FINISHED(R.string.finished.toString()),
    CANCELLED(R.string.cancelled.toString()),
    INTERRUPT(R.string.interrupt.toString()),

}