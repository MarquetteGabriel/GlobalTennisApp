/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.view.rankings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.gmarquette.globaltennisapp.R
import fr.gmarquette.globaltennisapp.databinding.FragmentLeaderboardBinding

class LeaderboardFragment : Fragment() {

    private lateinit var binding: FragmentLeaderboardBinding
    private val SINGLES_RANKING: Int = 0
    private val LIVE_RANKING: Int = 1
    private val RACE_RANKING: Int = 2
    private var scrolledTimes = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_leaderboard, container, false)
        val view = binding.root
        scrolledTimes = 0

        val singleRankTextView: View = view.findViewById(R.id.leaderboardSinglesTextView)
        val raceRankTextView: View = view.findViewById(R.id.leaderboardRaceTextView)
        val liveRankTextView: View = view.findViewById(R.id.leaderboardLiveTextView)

        val recyclerView: RecyclerView = view.findViewById(R.id.leaderboardRecyclerView)

        singleRankTextView.setOnClickListener {
            scrolledTimes = 0
            setRecyclerView(recyclerView, SINGLES_RANKING)
        }

        raceRankTextView.setOnClickListener {
            scrolledTimes = 0
            setRecyclerView(recyclerView, RACE_RANKING)
        }

        liveRankTextView.setOnClickListener {
            scrolledTimes = 0
            setRecyclerView(recyclerView, LIVE_RANKING)
        }

        setRecyclerView(recyclerView, SINGLES_RANKING)

        return view
    }

    private fun setRecyclerView(recyclerView: RecyclerView, rankType: Int)
    {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
        recyclerView.adapter = LeaderboardAdapter(requireContext()) {
            Navigation.findNavController(requireView()).navigate(LeaderboardFragmentDirections.actionLeaderboardFragmentToPlayerFragment())
        }
        (recyclerView.adapter as LeaderboardAdapter).updateRankings(scrolledTimes, rankType)

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if(!recyclerView.canScrollVertically(1))
                {
                    scrolledTimes++
                    (recyclerView.adapter as LeaderboardAdapter).updateRankings(scrolledTimes, rankType)
                }
            }
        })
    }
}