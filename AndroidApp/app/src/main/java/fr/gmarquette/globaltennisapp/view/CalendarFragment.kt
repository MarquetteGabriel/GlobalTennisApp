/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.view

import android.os.Bundle
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.gmarquette.globaltennisapp.R
import fr.gmarquette.globaltennisapp.api.ApiObject
import fr.gmarquette.globaltennisapp.databinding.FragmentCalendarBinding
import fr.gmarquette.globaltennisapp.model.tournament.Tournament
import fr.gmarquette.globaltennisapp.model.tournament.TournamentViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CalendarFragment : Fragment()
{

    private lateinit var binding: FragmentCalendarBinding
    private val mainScope = CoroutineScope(Dispatchers.Main)
    private lateinit var tournamentViewModel: TournamentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_calendar, container, false)
        val view = binding.root

        tournamentViewModel = ViewModelProvider(this)[TournamentViewModel::class.java]
        val adapterList = CalendarAdapter {

        }

        getCalendarATP()
        var calendarTournamentList: List<Any> = listOf()

        tournamentViewModel.getTournaments().observe(viewLifecycleOwner) {
            calendarTournamentList = CalendarObject.getItems(tournamentViewModel)
            adapterList.updateList(calendarTournamentList)
        }

        binding.calendarTournamentRecyclerView.apply {
            layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
            adapter = adapterList
        }

        binding.calendarSearchView.addTextChangedListener ( object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                val filteredList = ArrayList<Any>()
                var lastItem = Any()
                for (item in calendarTournamentList) {
                    if (item is CalendarItems.Header) {
                        if(lastItem.javaClass != item.javaClass)
                        {
                            filteredList.add(item)
                            lastItem = item
                        }
                        else
                        {
                            filteredList.remove(lastItem)
                            filteredList.add(item)
                            lastItem = item
                        }
                    }
                    if (item is CalendarItems.Item) {
                        if (item.tournamentName.lowercase().contains(text.toString().lowercase())) {
                            filteredList.add(item)
                            lastItem = item
                        }
                    }
                }

                if (filteredList.isNotEmpty() && filteredList.last() is CalendarItems.Header)
                {
                    filteredList.removeAt(filteredList.size - 1)
                }

                adapterList.updateList(filteredList)
                binding.calendarTournamentRecyclerView.scrollToPosition(0)
            }

            override fun afterTextChanged(s: android.text.Editable?) {
            }
        })

        return view
    }

    private fun getCalendarATP(){
        mainScope.launch {
            try {
                delay(7000)
                CoroutineScope(Dispatchers.IO).launch {
                    val response = ApiObject.calendarApi.getCalendarATP()
                    withContext(Dispatchers.Main) {
                        val tournaments = response.body()
                        if (tournaments != null) {
                            // Mettez à jour l'interface utilisateur avec les données récupérées
                            for (tournament in tournaments) {
                                val tempTournament = Tournament(tournament)
                                tournamentViewModel.addOrUpdateTournament(tempTournament)
                            }
                        } else {
                            // Gérez les erreurs
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