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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.gmarquette.globaltennisapp.R
import fr.gmarquette.globaltennisapp.databinding.FragmentCalendarBinding

class CalendarFragment : Fragment()
{

    private lateinit var binding: FragmentCalendarBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_calendar, container, false)
        val view = binding.root
        var calendarTournamentList: List<Any> = listOf()

        val adapterList = CalendarAdapter {}

        binding.calendarTournamentRecyclerView.apply {
            layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
            adapter = adapterList
        }

        binding.calendarSearchView.addTextChangedListener ( object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                val filteredList = ArrayList<Any>()
                for (item in calendarTournamentList) {
                    if (item is CalendarItems.Item) {
                        if (item.tournamentName.lowercase().contains(text.toString().lowercase())) {
                            filteredList.add(item)
                        }
                    }
                    if (item is CalendarItems.Header) {
                        filteredList.add(item)
                    }
                }

                adapterList.updateList(filteredList)
            }

            override fun afterTextChanged(s: android.text.Editable?) {
            }
        })

        return view
    }
}