/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.model.enums

import fr.gmarquette.globaltennisapp.R

enum class TournamentType (val description: String, val logo: Int) {
    JO(R.string.olympics.toString(), R.drawable.olympics),
    ATP_FINALS(R.string.atp_finals.toString(), R.drawable.atp_finals),
    ATP_GRAND_CHELEM(R.string.atp_grand_slam.toString(),0),
    ATP_1000(R.string.atp_1000.toString(), R.drawable.atp_1000),
    ATP_500(R.string.atp_500.toString() , R.drawable.atp_500),
    ATP_250(R.string.atp_250.toString(), R.drawable.atp_250),
    WTA_FINALS(R.string.wta_finals.toString(), R.drawable.wta_finals),
    WTA_GRAND_CHELEM(R.string.wta_grand_slam.toString(), 0),
    WTA_1000(R.string.wta_1000.toString(), R.drawable.wta_1000),
    WTA_500(R.string.wta_500.toString(), R.drawable.wta_500),
    WTA_250(R.string.wta_250.toString(), R.drawable.wta_250),
    WTA_125(R.string.wta_125.toString(), 0),
    DAVIS_CUP(R.string.davis_cup.toString(), R.drawable.davis_cup),
    UNITED_CUP(R.string.united_cup.toString(), R.drawable.united_cup),
    ROLLAND_GARROS(R.string.RG.toString(), R.drawable.rolland_garros),
    WIMBLEDON(R.string.WIMBLEDON.toString(), R.drawable.wimbledon),
    US_OPEN(R.string.US_open.toString(), R.drawable.us_open),
    AUSTRALIAN_OPEN(R.string.AO.toString(), R.drawable.australian_open),
    ATP_FINALS_NEXT_GEN(R.string.atp_finals_next_gen.toString(), 0),
    LAVER_CUP(R.string.laver_cup.toString(), 0),
}