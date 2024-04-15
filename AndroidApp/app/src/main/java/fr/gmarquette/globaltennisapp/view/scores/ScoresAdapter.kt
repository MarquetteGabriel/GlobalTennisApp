/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.view.scores

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.gmarquette.globaltennisapp.R
import fr.gmarquette.globaltennisapp.databinding.CalendarHeaderBinding
import fr.gmarquette.globaltennisapp.databinding.ScoresResultItemBinding
import fr.gmarquette.globaltennisapp.view.scores.ScoresObject.MATCH_HEADER
import fr.gmarquette.globaltennisapp.view.scores.ScoresObject.MATCH_ITEM

class ScoresAdapter(private val clickListener: (ScoresItems.Item) -> Unit, private val clickListenerTournament: (ScoresItems.Header) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>()
{

    class MatchesViewHolder(private val matchesBinding: ScoresResultItemBinding, clickAtPosition: (Int) -> Unit
    ) : RecyclerView.ViewHolder(matchesBinding.root) {

        init {
            itemView.setOnClickListener {
                clickAtPosition(adapterPosition)
            }
        }

        fun bind(matches: ScoresItems.Item) {
            val context = matchesBinding.root.context
            matchesBinding.match = matches
            if(matches.match.homeScores.one == "6" && matches.match.awayScores.one != "7")
            {
                matchesBinding.set1player2Text.setTextColor(context.getColor(R.color.loose))
            }
            if(matches.match.homeScores.two == "6" && matches.match.awayScores.two != "7")
            {
                matchesBinding.set2player2Text.setTextColor(context.getColor(R.color.loose))
            }
            if(matches.match.homeScores.three == "6" && matches.match.awayScores.three != "7")
            {
                matchesBinding.set3player2Text.setTextColor(context.getColor(R.color.loose))
            }
            if(matches.match.homeScores.four == "6" && matches.match.awayScores.four != "7")
            {
                matchesBinding.set4player2Text.setTextColor(context.getColor(R.color.loose))
            }
            if(matches.match.homeScores.five == "6" && matches.match.awayScores.five != "7")
            {
                matchesBinding.set5player2Text.setTextColor(context.getColor(R.color.loose))
            }
            if(matches.match.awayScores.one == "6" && matches.match.homeScores.one != "7")
            {
                matchesBinding.set1player1Text.setTextColor(context.getColor(R.color.loose))
            }
            if(matches.match.awayScores.two == "6" && matches.match.homeScores.two != "7")
            {
                matchesBinding.set2player1Text.setTextColor(context.getColor(R.color.loose))
            }
            if(matches.match.awayScores.three == "6" && matches.match.homeScores.three != "7")
            {
                matchesBinding.set3player1Text.setTextColor(context.getColor(R.color.loose))
            }
            if(matches.match.awayScores.four == "6" && matches.match.homeScores.four != "7")
            {
                matchesBinding.set4player1Text.setTextColor(context.getColor(R.color.loose))
            }
            if(matches.match.awayScores.five == "6" && matches.match.homeScores.five != "7")
            {
                matchesBinding.set5player1Text.setTextColor(context.getColor(R.color.loose))

            }
            if(matches.match.homeScores.one == "7")
            {
                matchesBinding.set1player2Text.setTextColor(context.getColor(R.color.loose))
                matchesBinding.tiebreak1player2.setTextColor(context.getColor(R.color.loose))
            }
            if(matches.match.homeScores.two == "7")
            {
                matchesBinding.set2player2Text.setTextColor(context.getColor(R.color.loose))
                matchesBinding.tiebreak2player2.setTextColor(context.getColor(R.color.loose))
            }
            if(matches.match.homeScores.three == "7")
            {
                matchesBinding.set3player2Text.setTextColor(context.getColor(R.color.loose))
                matchesBinding.tiebreak3player2.setTextColor(context.getColor(R.color.loose))
            }
            if(matches.match.homeScores.four == "7")
            {
                matchesBinding.set4player2Text.setTextColor(context.getColor(R.color.loose))
                matchesBinding.tiebreak4player2.setTextColor(context.getColor(R.color.loose))
            }
            if(matches.match.homeScores.five == "7")
            {
                matchesBinding.set5player2Text.setTextColor(context.getColor(R.color.loose))
                matchesBinding.tiebreak5player2.setTextColor(context.getColor(R.color.loose))
            }
            if(matches.match.awayScores.one == "7")
            {
                matchesBinding.set1player1Text.setTextColor(context.getColor(R.color.loose))
                matchesBinding.tiebreak1player1.setTextColor(context.getColor(R.color.loose))
            }
            if(matches.match.awayScores.two == "7")
            {
                matchesBinding.set2player1Text.setTextColor(context.getColor(R.color.loose))
                matchesBinding.tiebreak2player1.setTextColor(context.getColor(R.color.loose))
            }
            if(matches.match.awayScores.three == "7")
            {
                matchesBinding.set3player1Text.setTextColor(context.getColor(R.color.loose))
                matchesBinding.tiebreak3player1.setTextColor(context.getColor(R.color.loose))
            }
            if(matches.match.awayScores.four == "7")
            {
                matchesBinding.set4player1Text.setTextColor(context.getColor(R.color.loose))
                matchesBinding.tiebreak4player1.setTextColor(context.getColor(R.color.loose))
            }
            if(matches.match.awayScores.five == "7")
            {
                matchesBinding.set5player1Text.setTextColor(context.getColor(R.color.loose))
                matchesBinding.tiebreak5player1.setTextColor(context.getColor(R.color.loose))
            }

            if(!matches.match.isLive(matches.match.status))
            {
                if(matches.match.getResult() == "home")
                {
                    matchesBinding.setTextColor(context.getColor(R.color.loose))
                    matchesBinding.serie1.setTextColor(context.getColor(R.color.loose_tete_de_serie))
                }
                else
                {
                    matchesBinding.player2.setTextColor(context.getColor(R.color.loose))
                    matchesBinding.serie2.setTextColor(context.getColor(R.color.loose_tete_de_serie))
                }
            }



            FlagsAPI.getFlag(matches.match.playerOne.nationality, matchesBinding.flag1, context)
            FlagsAPI.getFlag(matches.match.playerTwo.nationality, matchesBinding.flag2, context)
            matchesBinding.executePendingBindings()
        }

    }

    class HeaderViewHolder(private val matchesHeaderBinding: CalendarHeaderBinding, clickAtPosition: (Int) -> Unit) : RecyclerView.ViewHolder(matchesHeaderBinding.root)  {

        init {
            itemView.setOnClickListener {
                clickAtPosition(adapterPosition)
            }
        }

        fun bind(header: ScoresItems.Header) {
            matchesHeaderBinding.header = header.tournamentName
            matchesHeaderBinding.executePendingBindings()
        }
    }

    private val itemList = arrayListOf<Any>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            MATCH_HEADER -> HeaderViewHolder(CalendarHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)) {
                clickListenerTournament(itemList[it] as ScoresItems.Header)}
            MATCH_ITEM -> MatchesViewHolder(ScoresResultItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))  {
                clickListener(itemList[it] as ScoresItems.Item)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> holder.bind(itemList[position] as ScoresItems.Header)
            is MatchesViewHolder -> holder.bind(itemList[position] as ScoresItems.Item)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (itemList[position]) {
            is ScoresItems.Header -> MATCH_HEADER
            is ScoresItems.Item -> MATCH_ITEM
            else -> throw IllegalArgumentException("Invalid type of data $position")
        }
    }

    fun updateList(list: List<Any>) {
        itemList.clear()
        itemList.addAll(list)
        notifyDataSetChanged()
    }
}