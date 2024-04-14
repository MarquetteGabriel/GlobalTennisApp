/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */
package fr.gmarquette.globaltennisapp

import android.database.CursorWindow
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import fr.gmarquette.globaltennisapp.api.ApiService
import fr.gmarquette.globaltennisapp.databinding.ActivityAtpBinding
import fr.gmarquette.globaltennisapp.view.calendar.CalendarFragmentDirections
import fr.gmarquette.globaltennisapp.view.rankings.LeaderboardFragmentDirections
import fr.gmarquette.globaltennisapp.view.tournamentpage.TournamentPageFragmentDirections

class ATPActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAtpBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAtpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val field = CursorWindow::class.java.getDeclaredField("sCursorWindowSize")
        field.isAccessible = true
        field.set(null, 100 * 1024 * 1024)

        // Start of Api Worker
        val apiWorkRequest = OneTimeWorkRequestBuilder<ApiService>()
            .build()
        WorkManager.getInstance(this).enqueue(apiWorkRequest)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navComponentATP) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.matchesMenu -> {
                    /*
                    when (navController.currentDestination?.id) {
                        R.id.leaderboardFragment -> navController.navigate(R.id.action_leaderboardFragment_to_scoresFragment)
                        R.id.calendarFragment -> navController.navigate(R.id.action_calendarFragment_to_scoresFragment)
                        R.id.favoritesFragment -> navController.navigate(R.id.action_favoritesFragment_to_scoresFragment)

                        R.id.playerProfileFragment -> navController.navigate(R.id.action_playerProfileFragment_to_scoresFragment)
                        R.id.tournamentPageFragment -> navController.navigate(R.id.action_tournamentProfilFragment_to_scoresFragment)
                        R.id.matchFragment -> navController.navigate(R.id.action_matchFragment_to_scoresFragment)
                   }
                     */

                }
                R.id.calendarMenu -> {
                    when (navController.currentDestination?.id) {
                        R.id.leaderboardFragment -> navController.navigate(LeaderboardFragmentDirections.actionLeaderboardFragmentToCalendarFragment())
                        // R.id.scoresFragment -> navController.navigate(ScoresFragmentDirections.actionScoresFragmentToCalendarFragment("Roger Federer"))
                        // R.id.favoritesFragment -> navController.navigate(R.id.action_favoritesFragment_to_calendarFragment)

                        // R.id.tournamentPageFragment -> navController.navigate(R.id.action_tournamentProfilFragment_to_calendarFragment)
                        // R.id.matchFragment -> navController.navigate(R.id.action_matchFragment_to_calendarFragment)
                        // R.id.playerProfileFragment -> navController.navigate(R.id.action_playerProfileFragment_to_calendarFragment)
                    }
                }
                R.id.rankMenu -> {
                    when (navController.currentDestination?.id) {
                        // R.id.scoresFragment -> navController.navigate(R.id.action_scoresFragment_to_leaderboardFragment)
                        R.id.calendarFragment -> navController.navigate(CalendarFragmentDirections.actionCalendarFragmentToLeaderboardFragment())
                        //R.id.favoritesFragment -> navController.navigate(R.id.action_favoritesFragment_to_leaderboardFragment)

                        R.id.tournamentPageFragment -> navController.navigate(TournamentPageFragmentDirections.actionTournamentPageFragmentToLeaderboardFragment())
                        //R.id.matchFragment -> navController.navigate(R.id.action_matchFragment_to_leaderboardFragment)
                        //R.id.playerProfileFragment -> navController.navigate(R.id.action_playerProfileFragment_to_leaderboardFragment)
                    }
                }
                R.id.favMenu -> {
                    /*
                    when (navController.currentDestination?.id) {
                        R.id.leaderboardFragment -> navController.navigate(R.id.action_leaderboardFragment_to_favoritesFragment)
                        R.id.scoresFragment -> navController.navigate(R.id.action_scoresFragment_to_favoritesFragment)
                        R.id.calendarFragment -> navController.navigate(R.id.action_calendarFragment_to_favoritesFragment)

                        R.id.playerProfileFragment -> navController.navigate(R.id.action_playerProfileFragment_to_favoritesFragment)
                        R.id.tournamentPageFragment -> navController.navigate(R.id.action_tournamentProfilFragment_to_favoritesFragment)
                        R.id.matchFragment -> navController.navigate(R.id.action_matchFragment_to_favoritesFragment)
                    }
                     */

                }
            }
            true
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        val workManager = WorkManager.getInstance(this)
        workManager.cancelUniqueWork(ApiService.WORK_NAME)

    }

}