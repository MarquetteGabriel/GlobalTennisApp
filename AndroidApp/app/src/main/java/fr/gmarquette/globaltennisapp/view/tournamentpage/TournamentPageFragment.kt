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
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.gmarquette.globaltennisapp.R
import fr.gmarquette.globaltennisapp.api.ApiObject
import fr.gmarquette.globaltennisapp.databinding.FragmentTournamentPageBinding
import fr.gmarquette.globaltennisapp.model.BitmapUrl
import fr.gmarquette.globaltennisapp.model.tournament.Tournament
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

        CoroutineScope(Dispatchers.IO).launch {
            val image = BitmapUrl().getBitmapFromUrl(args.tournament.overviewUrl, requireContext())
            CoroutineScope(Dispatchers.Main).launch {
                binding.tournamentImage.setImageBitmap(image)
                args.tournament.picture = image
            }
        }

        getTournamentInformation(args.tournament)

        val surface = args.tournament.surface + ", " + args.tournament.indoorOutdoor
        binding.tournamentName.text = args.tournament.name
        view.findViewById<TextView>(R.id.tournamentLevelText).text = args.tournament.type.description
        view.findViewById<TextView>(R.id.tournamentCityText).text = args.tournament.location
        view.findViewById<TextView>(R.id.tournamentDateText).text = args.tournament.formattedDate
        view.findViewById<TextView>(R.id.tournamentDrawSizeText).text = args.tournament.singleDrawSize.toString()
        view.findViewById<TextView>(R.id.tournamentSurfaceText).text = surface
        view.findViewById<TextView>(R.id.tournamentPrizeMoneyText).text = args.tournament.prizeMoney.toString()
        binding.tournamentImage.setImageBitmap(args.tournament.picture)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewTournament)
        recyclerView.layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)

        binding.overviewTextViewButton.setOnClickListener {
            view.findViewById<View>(R.id.overview_tournament).visibility = View.VISIBLE
            view.findViewById<View>(R.id.recyclerViews_tournament).visibility = View.GONE
            view.findViewById<View>(R.id.draw_tournament).visibility = View.GONE
        }
        binding.seedsTextViewButton.setOnClickListener {
            view.findViewById<View>(R.id.overview_tournament).visibility = View.GONE
            view.findViewById<View>(R.id.recyclerViews_tournament).visibility = View.VISIBLE
            view.findViewById<TextView>(R.id.titleRecyclerView).text = "Seeds"
            recyclerView.adapter = TournamentPageAdapter(args.tournament.listSeeds) {
                Navigation.findNavController(view.rootView.findViewById(R.id.navComponentATP)).navigate(TournamentPageFragmentDirections.actionTournamentPageFragmentToPlayerFragment())
            }
            view.findViewById<View>(R.id.draw_tournament).visibility = View.GONE
        }
        binding.pastChampionsTextViewButton.setOnClickListener{
            view.findViewById<View>(R.id.overview_tournament).visibility = View.GONE
            view.findViewById<View>(R.id.recyclerViews_tournament).visibility = View.VISIBLE
            view.findViewById<TextView>(R.id.titleRecyclerView).text = "Past Champions"
            recyclerView.adapter = TournamentPageAdapter(args.tournament.listLastWinners) {
                Navigation.findNavController(view.rootView.findViewById(R.id.navComponentATP)).navigate(TournamentPageFragmentDirections.actionTournamentPageFragmentToPlayerFragment())
            }
            view.findViewById<View>(R.id.draw_tournament).visibility = View.GONE
        }
        binding.drawsTextViewButton.setOnClickListener {
            view.findViewById<View>(R.id.overview_tournament).visibility = View.GONE
            view.findViewById<View>(R.id.recyclerViews_tournament).visibility = View.GONE
            view.findViewById<View>(R.id.draw_tournament).visibility = View.VISIBLE
        }

        return view
    }

    private fun getTournamentInformation(tournament: Tournament)
    {
        mainScope.launch {
            try {
                CoroutineScope(Dispatchers.IO).launch {
                    val response = ApiObject.tournamentPageApi.getTournamentInfo(args.tournament.id.toString()).body()
                    withContext(Dispatchers.Main) {
                        if (response != null) {
                            tournament.indoorOutdoor = response[0].IndoorOutdoor
                            tournament.location = response[0].Location
                            tournament.singleDrawSize = response[0].SingleDrawSize
                            tournament.surface = response[0].Surface

                            val tempListLastWinners = mutableListOf<Tournament.LastWinners>()
                            val tempListSeeds = mutableListOf<Tournament.Seeds>()
                            val tempListPrize = mutableListOf<Tournament.Prize>()
                            val tempListPoints = mutableListOf<Tournament.Points>()

                            response[0].PastChampions.forEach {
                                tempListLastWinners.add(Tournament.LastWinners(it.FirstName + " " + it.LastName, it.Year))
                            }

                            response[0].TopSeeds.forEach {
                                tempListSeeds.add(Tournament.Seeds(it.PlayerName, it.Seed))
                            }


                            response[0].PrizeAndPoints.forEach{
                                tempListPrize.add(Tournament.Prize(it.Round, it.PrizeMoney))
                                tempListPoints.add(Tournament.Points(it.Round, it.Points))
                            }

                            tournament.listLastWinners = tempListLastWinners
                            tournament.listSeeds = tempListSeeds
                            tournament.listPrize = tempListPrize
                            tournament.listPoints = tempListPoints

                            tournamentViewModel.updateTournament(tournament)
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