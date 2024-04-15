/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.view.loading

import android.media.AudioManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import fr.gmarquette.globaltennisapp.R
import fr.gmarquette.globaltennisapp.api.ApiObject
import fr.gmarquette.globaltennisapp.model.enums.TournamentType
import fr.gmarquette.globaltennisapp.model.tournament.Tournament
import fr.gmarquette.globaltennisapp.model.tournament.TournamentViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoadingVideoFragment : Fragment() {

    private val mainScope = CoroutineScope(Dispatchers.Main)
    private lateinit var tournamentViewModel: TournamentViewModel
    private val DELAY_CALENDAR: Long = 3500

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.loading_fragment, container, false)
        tournamentViewModel = ViewModelProvider(this)[TournamentViewModel::class.java]

        val videoView = view.findViewById<VideoView>(R.id.videoView)
        val uri = Uri.parse("android.resource://" + requireActivity().packageName + "/" + R.raw.poti_dragon)
        videoView.setAudioFocusRequest(AudioManager.AUDIOFOCUS_NONE)
        videoView.setVideoURI(uri)

        tournamentViewModel.getTournaments().observe(viewLifecycleOwner, object :
            Observer<List<Tournament>> {
            override fun onChanged(value: List<Tournament>) {
                getCalendarATP(value)
                tournamentViewModel.getTournaments().removeObserver(this)
            }
        })

        videoView.setOnCompletionListener {
            Navigation.findNavController(view.rootView.findViewById(R.id.navComponentATP))
                .navigate(LoadingVideoFragmentDirections.actionLoadingVideoFragmentToScoresFragment())
        }

        videoView.start()

        return view
    }

    private fun getCalendarATP(it: List<Tournament>){
        mainScope.launch {
            try {
                delay(DELAY_CALENDAR)
                CoroutineScope(Dispatchers.IO).launch {
                    val response = ApiObject.calendarApi.getCalendarATP()
                    withContext(Dispatchers.Main) {
                        val tournaments = response.body()
                        if (tournaments != null) {
                            for (tournament in tournaments) {
                                var exist = false
                                for (i in it)
                                {
                                    if(tournament.Id.toInt() == i.id)
                                    {
                                        i.name = tournament.Name
                                        i.formattedDate = tournament.FormattedDate
                                        i.location = tournament.Location
                                        i.overviewUrl = tournament.OverviewUrl
                                        i.website = tournament.url_tournament
                                        i.type = convertTypeToCategory(tournament.Type, tournament.Name)
                                        tournamentViewModel.updateTournament(i)
                                        exist = true
                                        break
                                    }
                                }
                                if(!exist)
                                {
                                    val tempTournament = Tournament(tournament)
                                    tournamentViewModel.addTournament(tempTournament)
                                }
                            }
                        }
                    }

                }
            }
            catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    private fun convertTypeToCategory(type: String, name: String): TournamentType
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