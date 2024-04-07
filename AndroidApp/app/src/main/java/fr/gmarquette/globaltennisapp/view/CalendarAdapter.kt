/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.gmarquette.globaltennisapp.CalendarObject.TYPE_HEADER
import fr.gmarquette.globaltennisapp.CalendarObject.TYPE_ITEM
import fr.gmarquette.globaltennisapp.databinding.CalendarHeaderBinding
import fr.gmarquette.globaltennisapp.databinding.CalendarItemBinding

class CalendarAdapter(private val clickListener: (CalendarItems.Item) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class TournamentViewHolder(private val tournamentBinding: CalendarItemBinding, clickAtPosition: (Int) -> Unit
    ) : RecyclerView.ViewHolder(tournamentBinding.root) {

        init {
            itemView.setOnClickListener {
                clickAtPosition(adapterPosition)
            }
        }

        fun bind(tournament: CalendarItems.Item) {
            tournamentBinding.tournament = tournament
            tournamentBinding.logoTournament.setImageResource(tournament.logo)
            tournamentBinding.executePendingBindings()
        }
    }

    class HeaderViewHolder(private val calendarHeaderBinding: CalendarHeaderBinding) : RecyclerView.ViewHolder(calendarHeaderBinding.root)  {
        fun bind(header: CalendarItems.Header) {
            calendarHeaderBinding.header = header.month
            calendarHeaderBinding.executePendingBindings()
        }
    }

    private val itemList = arrayListOf<Any>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> HeaderViewHolder(CalendarHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            TYPE_ITEM -> TournamentViewHolder(CalendarItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))  {
                clickListener(itemList[it] as CalendarItems.Item)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }

    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> holder.bind(itemList[position] as CalendarItems.Header)
            is TournamentViewHolder -> holder.bind(itemList[position] as CalendarItems.Item)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (itemList[position]) {
            is CalendarItems.Header -> TYPE_HEADER
            is CalendarItems.Item -> TYPE_ITEM
            else -> throw IllegalArgumentException("Invalid type of data $position")
        }
    }

    fun updateList(updatedList: List<Any>) {
        itemList.clear()
        itemList.addAll(updatedList)
        notifyDataSetChanged()
    }
}
