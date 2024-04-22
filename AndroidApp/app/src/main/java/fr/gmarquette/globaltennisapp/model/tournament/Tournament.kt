/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.model.tournament

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import fr.gmarquette.globaltennisapp.api.dataclasses.CalendarATP
import fr.gmarquette.globaltennisapp.model.enums.TournamentType
import fr.gmarquette.globaltennisapp.model.tournament.lastwinners.LastWinners
import fr.gmarquette.globaltennisapp.model.tournament.points.Points
import fr.gmarquette.globaltennisapp.model.tournament.prize.Prize
import fr.gmarquette.globaltennisapp.model.tournament.seeds.Seeds
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Parcelize
@Entity(tableName = "tournaments")
data class Tournament (

    @PrimaryKey(autoGenerate = true) var id: Int,
    var name: String,
    var location: String,
    var formattedDate: String,
    var type: TournamentType,
    var overviewUrl: String,
    var website: String,
    var tournamentMonth: Int?,
    @Ignore @IgnoredOnParcel var date: TournamentDate? = null,
    var surface: String?,
    var indoorOutdoor: String?,
    var singleDrawSize: Int?,
    var picturePath: String?,
    // val picture: Bitmap?,
    var prizeMoney: Int?,
    @Ignore @IgnoredOnParcel var lastWinners: List<LastWinners>? = null,
    @Ignore @IgnoredOnParcel var seeds: List<Seeds>? = null,
    @Ignore @IgnoredOnParcel var prizes: List<Prize>? = null,
    @Ignore @IgnoredOnParcel var points: List<Points>? = null
) : Parcelable
{
    constructor(id: Int, name: String, location: String, formattedDate: String, type: TournamentType, overviewUrl: String, website: String, tournamentMonth: Int) : this(
        id, name, location, formattedDate, type, overviewUrl, website, 0, null, null, null, null, null, null, null, null, null)
    constructor(calendar: CalendarATP): this(
        calendar.Id.toInt(),
        calendar.Name,
        calendar.Location,
        calendar.FormattedDate,
        convertTypeToCategory(calendar.Type, calendar.Name),
        calendar.OverviewUrl,
        calendar.url_tournament,
        0, // tournamentMonth
        null, // date
        null, // surface
        null, // indoorOutdoor
        null, // singleDrawSize
        null, // picture
        null,  // prizeMoney
        null, // lastWinners
        null, // seeds
        null, // prizes
        null // points
    ) {
        this.date = convertFormattedDate(calendar.FormattedDate)
        this.tournamentMonth = this.date?.let {
            setTournamentMonth(
                it.startMonth.lowercase().replaceFirstChar(Char::uppercase),
                it.endMonth.lowercase().replaceFirstChar(Char::uppercase)
            )
        }
    }



    companion object
    {
        fun convertTypeToCategory(type: String, name: String): TournamentType
        {
            return if (type.contains("250")) TournamentType.ATP_250
            else if (type.contains("500")) TournamentType.ATP_500
            else if (type.contains("1000")) TournamentType.ATP_1000
            else if (type.contains("GS")) {
                if(name.contains("Australian")) TournamentType.AUSTRALIAN_OPEN
                else if(name.contains("Roland Garros")) TournamentType.ROLLAND_GARROS
                else if(name.contains("Wimbledon")) TournamentType.WIMBLEDON
                else if(name.contains("US Open")) TournamentType.US_OPEN
                else TournamentType.ATP_GRAND_CHELEM }
            else if (type.contains("WC")) TournamentType.ATP_FINALS
            else if (type.contains("XXI")) TournamentType.ATP_FINALS_NEXT_GEN
            else if (type.contains("DCR")) TournamentType.DAVIS_CUP
            else if (type.contains("UC")) TournamentType.UNITED_CUP
            else if (type.contains("LVR")) TournamentType.LAVER_CUP
            else TournamentType.ATP_GRAND_CHELEM
        }
    }

    fun setTournamentMonth(startMonth: String, endMonth: String) : Int
    {
        return if (startMonth.contains("December") && endMonth.contains("January")) 0
        else if(startMonth.contains("January")) 1
        else if(startMonth.contains("February")) 2
        else if(startMonth.contains("March")) 3
        else if(startMonth.contains("April")) 4
        else if(startMonth.contains("May")) 5
        else if(startMonth.contains("June")) 6
        else if(startMonth.contains("July")) 7
        else if(startMonth.contains("August")) 8
        else if(startMonth.contains("September")) 9
        else if(startMonth.contains("October")) 10
        else if(startMonth.contains("November")) 11
        else 12
    }

    fun convertFormattedDate(date: String): TournamentDate
    {
        val dateFormatter = DateTimeFormatter.ofPattern("d MMMM, yyyy", Locale.ENGLISH)
        val dates = date.split(" - ")
        val endDate = LocalDate.parse(dates[1], dateFormatter)
        val startDate = if(dates[0].length <= 2) {
            LocalDate.parse(dates[0] + " " + endDate.month.toString().lowercase().replaceFirstChar(Char::uppercase) + ", " + endDate.year.toString(), dateFormatter)
        } else if(!dates[0].contains(",")) {
            LocalDate.parse(dates[0] + ", " + endDate.year.toString(), dateFormatter)
        } else {
            LocalDate.parse(dates[0], dateFormatter)
        }

        return TournamentDate(
            startDate.dayOfMonth.toString(), startDate.month.toString(), startDate.year.toString(),
            endDate.dayOfMonth.toString(), endDate.month.toString(), endDate.year.toString())
    }

    data class TournamentDate(val startDay: String, val startMonth: String, val startYear: String, val endDay: String, val endMonth: String, val endYear: String)
}