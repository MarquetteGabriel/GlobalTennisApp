/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.view.loading

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import fr.gmarquette.globaltennisapp.R

class LoadingVideoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.loading_fragment, container, false)

        Navigation.findNavController(view.rootView.findViewById(R.id.navComponentATP))
            .navigate(LoadingVideoFragmentDirections.actionLoadingVideoFragmentToCalendarFragment())

        return view
    }
}