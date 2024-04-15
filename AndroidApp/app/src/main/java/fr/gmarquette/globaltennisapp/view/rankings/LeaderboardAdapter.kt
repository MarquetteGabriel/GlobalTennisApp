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
import fr.gmarquette.globaltennisapp.api.ApiObject
import fr.gmarquette.globaltennisapp.api.dataclasses.RankingOfficial
import fr.gmarquette.globaltennisapp.model.BitmapUrl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LeaderboardAdapter(
    private val context: Context,
    private val clickListener: (RankingOfficial) -> Unit
) : RecyclerView.Adapter<LeaderboardAdapter.ViewHolder>()
{

    private val mainScope = CoroutineScope(Dispatchers.Main)
    private val playerList: MutableList<RankingOfficial> = mutableListOf()

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

        holder.playerRank.text = player.rank
        holder.playerUpdate.text = player.diff
        if(player.diff.contains("+"))
        {
            holder.playerUpdate.setTextColor(context.getColor(R.color.green))
        }
        else if(player.diff.contains("-"))
        {
            holder.playerUpdate.setTextColor(context.getColor(R.color.red))
        }
        else
        {
            holder.playerUpdate.setTextColor(context.getColor(R.color.default_text))
        }
        CoroutineScope(Dispatchers.IO).launch {
            val bitmap = BitmapUrl().getBitmapFromUrl(player.flag_url, context)

            withContext(Dispatchers.Main) {
                holder.playerFlag.setImageBitmap(bitmap)
            }
        }
        holder.playerName.text = player.name
        holder.playerPoints.text = player.points.toString()

        holder.itemView.setOnClickListener {
            clickListener(playerList[position])
        }
    }

    fun updateRankings(nb: Int, rank: Int)
    {
        mainScope.launch {
            try {
                CoroutineScope(Dispatchers.IO).launch {
                    val response: List<RankingOfficial>?
                    if(rank == 0)
                    {
                        when (nb)
                        {
                            1 -> response = ApiObject.rankingOfficialApi.getRanking200().body()
                            2 -> response = ApiObject.rankingOfficialApi.getRanking300().body()
                            3 -> response = ApiObject.rankingOfficialApi.getRanking400().body()
                            4 -> response = ApiObject.rankingOfficialApi.getRanking500().body()
                            5 -> response = ApiObject.rankingOfficialApi.getRanking600().body()
                            6 -> response = ApiObject.rankingOfficialApi.getRanking700().body()
                            7 -> response = ApiObject.rankingOfficialApi.getRanking800().body()
                            8 -> response = ApiObject.rankingOfficialApi.getRanking900().body()
                            9 -> response = ApiObject.rankingOfficialApi.getRanking1000().body()
                            10 -> response = ApiObject.rankingOfficialApi.getRanking1100().body()
                            11 -> response = ApiObject.rankingOfficialApi.getRanking1200().body()
                            12 -> response = ApiObject.rankingOfficialApi.getRanking1300().body()
                            13 -> response = ApiObject.rankingOfficialApi.getRanking1400().body()
                            14 -> response = ApiObject.rankingOfficialApi.getRanking1500().body()
                            15 -> response = ApiObject.rankingOfficialApi.getRanking5000().body()
                            else -> response = ApiObject.rankingOfficialApi.getRanking100().body()
                        }
                    }
                    else if(rank == 1)
                    {
                        when (nb)
                        {
                            1 -> response = ApiObject.rankingLiveApi.getRanking200().body()
                            2 -> response = ApiObject.rankingLiveApi.getRanking300().body()
                            3 -> response = ApiObject.rankingLiveApi.getRanking400().body()
                            4 -> response = ApiObject.rankingLiveApi.getRanking500().body()
                            5 -> response = ApiObject.rankingLiveApi.getRanking600().body()
                            6 -> response = ApiObject.rankingLiveApi.getRanking700().body()
                            7 -> response = ApiObject.rankingLiveApi.getRanking800().body()
                            8 -> response = ApiObject.rankingLiveApi.getRanking900().body()
                            9 -> response = ApiObject.rankingLiveApi.getRanking1000().body()
                            10 -> response = ApiObject.rankingLiveApi.getRanking1100().body()
                            11 -> response = ApiObject.rankingLiveApi.getRanking1200().body()
                            12 -> response = ApiObject.rankingLiveApi.getRanking1300().body()
                            13 -> response = ApiObject.rankingLiveApi.getRanking1400().body()
                            14 -> response = ApiObject.rankingLiveApi.getRanking1500().body()
                            15 -> response = ApiObject.rankingLiveApi.getRanking5000().body()
                            else -> response = ApiObject.rankingLiveApi.getRanking100().body()
                        }
                    }
                    else if (rank == 2)
                    {
                        response = ApiObject.rankingRaceApi.getRankingRace().body()
                    }
                    else
                    {
                        response = null
                    }

                    if (response != null) {
                        withContext(Dispatchers.Main) {
                            playerList.addAll(response)
                            notifyDataSetChanged()
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