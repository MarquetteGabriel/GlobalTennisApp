/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */
package fr.gmarquette.globaltennisapp

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

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navComponentATP) as NavHostFragment
        navController = navHostFragment.navController

        // Start of Api Worker
        val apiWorkRequest = OneTimeWorkRequestBuilder<ApiService>()
            .build()
        WorkManager.getInstance(this).enqueue(apiWorkRequest)

    }

    override fun onDestroy() {
        super.onDestroy()
        val workManager = WorkManager.getInstance(this)
        workManager.cancelUniqueWork(ApiService.WORK_NAME)

    }
}