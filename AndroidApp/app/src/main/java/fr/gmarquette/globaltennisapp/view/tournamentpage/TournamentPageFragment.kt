/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.view.tournamentpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import fr.gmarquette.globaltennisapp.R
import fr.gmarquette.globaltennisapp.api.ApiObject
import fr.gmarquette.globaltennisapp.databinding.FragmentTournamentPageBinding
import fr.gmarquette.globaltennisapp.model.tournament.BitmapUrl
import fr.gmarquette.globaltennisapp.model.tournament.TournamentViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TournamentPageFragment: Fragment()
{

    private val mainScope = CoroutineScope(Dispatchers.Main)
    private lateinit var tournamentViewModel: TournamentViewModel
    private lateinit var binding: FragmentTournamentPageBinding
    private val args: TournamentPageFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTournamentPageBinding.inflate(inflater, container, false)
        val view = binding.root

        tournamentViewModel = ViewModelProvider(this)[TournamentViewModel::class.java]

        getTournamentInformation()

        CoroutineScope(Dispatchers.IO).launch {
            val image = BitmapUrl().getBitmapFromUrl(args.tournament.overviewUrl, requireContext())
            CoroutineScope(Dispatchers.Main).launch {
                binding.tournamentImage.setImageBitmap(image)
            }
        }

        val surface = args.tournament.surface + ", " + args.tournament.indoorOutdoor
        binding.tournamentName.text = args.tournament.name
        view.findViewById<TextView>(R.id.tournamentLevelText).text = args.tournament.type.description
        view.findViewById<TextView>(R.id.tournamentCityText).text = args.tournament.location
        view.findViewById<TextView>(R.id.tournamentDateText).text = args.tournament.formattedDate
        view.findViewById<TextView>(R.id.tournamentDrawSizeText).text = args.tournament.singleDrawSize.toString()
        view.findViewById<TextView>(R.id.tournamentSurfaceText).text = surface
        view.findViewById<TextView>(R.id.tournamentPrizeMoneyText).text = args.tournament.prizeMoney.toString()
        binding.tournamentImage.setImageBitmap(args.tournament.picture)


        return view
    }

    private fun getTournamentInformation()
    {
        mainScope.launch {
            try {
                CoroutineScope(Dispatchers.IO).launch {
                    val response = ApiObject.tournamentPageApi.getTournamentInfo( args.tournament.id.toString())
                    withContext(Dispatchers.Main) {
                        val tournaments = response.body()
                        if (tournaments != null) {
                            val tournamentPage = tournaments
                        }
                    }

                }
            }
            catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}