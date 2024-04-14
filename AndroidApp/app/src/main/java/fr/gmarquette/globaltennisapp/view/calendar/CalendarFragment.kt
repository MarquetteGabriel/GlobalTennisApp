/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.view.calendar

import android.os.Bundle
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.gmarquette.globaltennisapp.R
import fr.gmarquette.globaltennisapp.databinding.FragmentCalendarBinding
import fr.gmarquette.globaltennisapp.model.tournament.Tournament
import fr.gmarquette.globaltennisapp.model.tournament.TournamentViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

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
            tournamentViewModel.getTournamentByName(it.tournamentName).observe(viewLifecycleOwner) { tournament ->
                Navigation.findNavController(view.rootView.findViewById(R.id.navComponentATP))
                    .navigate(
                        CalendarFragmentDirections.actionCalendarFragmentToTournamentPageFragment(
                            tournament
                        )
                    )
                tournamentViewModel.getTournamentByName(it.tournamentName).removeObservers(viewLifecycleOwner)
            }
        }


        var calendarTournamentList: List<Any> = listOf()

        tournamentViewModel.getTournaments().observe(viewLifecycleOwner, object : Observer<List<Tournament>> {
            override fun onChanged(value: List<Tournament>) {
                calendarTournamentList = CalendarObject.getItems(tournamentViewModel)
                adapterList.updateList(calendarTournamentList)
                tournamentViewModel.getTournaments().removeObserver(this)
            }
        })

        binding.calendarTournamentRecyclerView.apply {
            layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
            adapter = adapterList
        }

        binding.calendarSearchView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                val filteredList = ArrayList<Any>()
                var lastItem = Any()
                for (item in calendarTournamentList) {
                    if (item is CalendarItems.Header) {
                        lastItem = if (lastItem.javaClass != item.javaClass) {
                            filteredList.add(item)
                            item
                        } else {
                            filteredList.remove(lastItem)
                            filteredList.add(item)
                            item
                        }
                    }
                    if (item is CalendarItems.Item) {
                        if (item.tournamentName.lowercase().contains(text.toString().lowercase())) {
                            filteredList.add(item)
                            lastItem = item
                        }
                    }
                }

                if (filteredList.isNotEmpty() && filteredList.last() is CalendarItems.Header) {
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
}