/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.model.matches

import fr.gmarquette.globaltennisapp.model.enums.MatchStatus
import fr.gmarquette.globaltennisapp.model.matches.scores.Score
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class MatchTest {

    @Test
    fun getResultHome() {

        val score = Score("6", "2", "7", "4", "6", "6", "", "", "8", "", "", "6", 0)
        val match = Match(1, 2, 3, "home", MatchStatus.LIVE, 0, 5, 6, "0", "0")
        match.score = score
        Assertions.assertEquals("home", match.getResult())
    }

    @Test
    fun getResultAway() {

        val score = Score("6", "2", "6", "4", "6", "7", "", "", "0", "", "", "7", 0)
        val match = Match(1, 2, 3, "home", MatchStatus.LIVE, 0, 5, 6, "0", "0")
        match.score = score
        Assertions.assertEquals("away", match.getResult())
    }

    @Test
    fun getResultErrorTieBreak() {

        val score = Score("6", "2", "6", "4", "6", "7", "", "", "0", "", "", "8", 0)
        val match = Match(1, 2, 3, "home", MatchStatus.LIVE, 0, 5, 6, "0", "0")
        match.score = score
        Assertions.assertEquals("home", match.getResult())
    }
}