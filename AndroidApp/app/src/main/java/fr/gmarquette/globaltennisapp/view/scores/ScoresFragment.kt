/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.view.scores

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.gmarquette.globaltennisapp.R
import fr.gmarquette.globaltennisapp.api.ApiObject
import fr.gmarquette.globaltennisapp.databinding.FragmentScoresBinding
import fr.gmarquette.globaltennisapp.model.matches.Match
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ScoresFragment : Fragment() {

    private lateinit var binding: FragmentScoresBinding
    private val mainScope = CoroutineScope(Dispatchers.Main)
    private val matchList: MutableList<Match> = mutableListOf()
    private lateinit var adapterList: ScoresAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_scores, container, false)
        val view = binding.root

        getScores()

        adapterList = ScoresAdapter ({
            // Navigate to match page
        }, {
            // Navigate to tournament PAge
        })

        val matchesList = emptyList<Match>()
        adapterList.updateList(matchesList)

        binding.matchRecyclerView.apply {
            layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
            adapter = adapterList
        }

        return view
    }

    private fun getScores()
    {
        mainScope.launch {
            try {
                CoroutineScope(Dispatchers.IO).launch {
                    val response = ApiObject.livescoreApi.getLiveScore()
                    withContext(Dispatchers.Main) {
                        if (response.body() != null) {
                            matchList.addAll(response.body()!!)
                            adapterList.updateList(matchList)
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