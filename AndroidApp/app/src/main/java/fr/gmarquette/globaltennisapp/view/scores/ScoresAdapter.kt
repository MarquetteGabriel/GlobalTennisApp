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
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import fr.gmarquette.globaltennisapp.R
import fr.gmarquette.globaltennisapp.databinding.CalendarHeaderBinding
import fr.gmarquette.globaltennisapp.databinding.ScoresResultItemBinding
import fr.gmarquette.globaltennisapp.model.enums.MatchStatus
import fr.gmarquette.globaltennisapp.view.scores.ScoresObject.MATCH_HEADER
import fr.gmarquette.globaltennisapp.view.scores.ScoresObject.MATCH_ITEM

private fun TextView.setColorIfCondition(condition: Boolean, color: Int) {
    setTextColor(if (condition) ContextCompat.getColor(context, color) else currentTextColor)
}

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
            val score = matches.match.score!!

            val player1Texts = listOf(matchesBinding.set1player1Text, matchesBinding.set2player1Text,
                matchesBinding.set3player1Text, matchesBinding.set4player1Text, matchesBinding.set5player1Text)

            val player2Texts = listOf(matchesBinding.set1player2Text, matchesBinding.set2player2Text,
                matchesBinding.set3player2Text, matchesBinding.set4player2Text, matchesBinding.set5player2Text)

            val tiebreakPlayer1Texts = listOf(matchesBinding.tiebreak1player1, matchesBinding.tiebreak2player1,
                matchesBinding.tiebreak3player1, matchesBinding.tiebreak4player1, matchesBinding.tiebreak5player1)

            val tiebreakPlayer2Texts = listOf(matchesBinding.tiebreak1player2, matchesBinding.tiebreak2player2,
                matchesBinding.tiebreak3player2, matchesBinding.tiebreak4player2, matchesBinding.tiebreak5player2)

            for (i in 0 until 5) {
                player1Texts[i].setColorIfCondition(score.awayOne.toIntOrNull() == 6 && score.homeOne.toIntOrNull() != 7, R.color.loose)
                player2Texts[i].setColorIfCondition(score.homeOne.toIntOrNull() == 6 && score.awayOne.toIntOrNull() != 7, R.color.loose)

                if (score.homeOne.toIntOrNull() == 7) {
                    player2Texts[i].setColorIfCondition(true, R.color.loose)
                    tiebreakPlayer2Texts[i].setColorIfCondition(true, R.color.loose)
                }

                if (score.awayOne.toIntOrNull() == 7) {
                    player1Texts[i].setColorIfCondition(true, R.color.loose)
                    tiebreakPlayer1Texts[i].setColorIfCondition(true, R.color.loose)
                }
            }

            if (matches.match.status != MatchStatus.LIVE) {
                val result = matches.match.getResult()
                val playerText = if (result == "home") matchesBinding.root.findViewById<TextView>(R.id.player1) else matchesBinding.root.findViewById(R.id.player2)
                val serieText = if (result == "home") matchesBinding.root.findViewById<TextView>(R.id.serie1) else matchesBinding.root.findViewById(R.id.serie2)

                playerText.setTextColor(ContextCompat.getColor(context, R.color.loose))
                serieText.setTextColor(ContextCompat.getColor(context, R.color.loose_tete_de_serie))
            }

            val homePlayerFlag = matchesBinding.root.findViewById<ImageView>(R.id.flag1)
            val awayPlayerFlag = matchesBinding.root.findViewById<ImageView>(R.id.flag2)

            homePlayerFlag.setImageResource(matches.match.homePlayer!!.nationality.logo)
            awayPlayerFlag.setImageResource(matches.match.awayPlayer!!.nationality.logo)

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