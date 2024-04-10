/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.view.tournamentpage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.gmarquette.globaltennisapp.R
import fr.gmarquette.globaltennisapp.model.tournament.Tournament

class TournamentPageAdapter (private val list: List<Any>,
                                    private val clickListener: (Any) -> Unit) : RecyclerView.Adapter<TournamentPageAdapter.ViewHolder>(){

    class ViewHolder(itemView: View, clickAtPosition: (Int) -> Unit) : RecyclerView.ViewHolder(itemView) {
        val playerSeed: TextView = itemView.findViewById(R.id.nameSeed)
        val valueSeed: TextView = itemView.findViewById(R.id.valueSeed)

        init {
            itemView.setOnClickListener {
                clickAtPosition(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.tournament_page_item, parent, false))
        {
            clickListener(list[it])
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val player = list[position]

        if(player is Tournament.Seeds)
        {
            holder.playerSeed.text = player.name
            holder.valueSeed.text = player.seed.toString()
        }
        if (player is Tournament.LastWinners)
        {
            holder.playerSeed.text = player.name
            holder.valueSeed.text = player.year.toString()
        }


        holder.itemView.setOnClickListener {
            clickListener(list[position])
        }
    }

    override fun getItemCount(): Int = list.size
}