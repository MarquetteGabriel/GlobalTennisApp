/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.view.rankings

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.gmarquette.globaltennisapp.R
import fr.gmarquette.globaltennisapp.model.players.Players

class LeaderboardAdapter(private val playerList: List<Players>, private var context: Context,
                                private val clickListener: (Players) -> Unit) : RecyclerView.Adapter<LeaderboardAdapter.ViewHolder>()
{

    class ViewHolder(itemView: View, clickAtPosition: (Int) -> Unit) : RecyclerView.ViewHolder(itemView) {
        val playerRank: TextView = itemView.findViewById(R.id.leaderboardItemRank)
        val playerUpdate: TextView = itemView.findViewById(R.id.leaderboardItemUpdate)
        val playerFlag: ImageView = itemView.findViewById(R.id.leaderboardItemFlag)
        val playerName: TextView = itemView.findViewById(R.id.leaderboardItemName)
        val playerPoints: TextView = itemView.findViewById(R.id.leaderboardItemPoints)

        init {
            itemView.setOnClickListener {
                clickAtPosition(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rankings_item, parent, false))
        {
            clickListener(playerList[it])
        }
    }

    override fun getItemCount(): Int {
        return playerList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val player = playerList[position]

        holder.playerRank.text = player.rank.currentRank.toString()
        // holder.playerUpdate.text = player.rank.getRankUpdate(player.rank.currentRank, player.rank.previousRank)
        // FlagsAPI.getFlag(player.codeID.lowercase(), holder.playerFlag, context)
        holder.playerName.text = player.fullName
        holder.playerPoints.text = player.rank.points.toString()

        holder.itemView.setOnClickListener {
            clickListener(playerList[position])
        }
    }
}