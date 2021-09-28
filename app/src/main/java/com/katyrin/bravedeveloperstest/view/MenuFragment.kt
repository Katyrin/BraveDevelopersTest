package com.katyrin.bravedeveloperstest.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.katyrin.bravedeveloperstest.R
import com.katyrin.bravedeveloperstest.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {

    private var binding: FragmentMenuBinding? = null
    private var navController: NavController? = null
    private val navDirectionsSearch: NavDirections by lazy {
        MenuFragmentDirections.actionMenuFragmentToSearchFragment()
    }
    private val navDirectionsRandom: NavDirections by lazy {
        MenuFragmentDirections.actionMenuFragmentToRandomFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentMenuBinding.inflate(inflater, container, false)
        .also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(requireActivity(), R.id.main_container)
        initViews()
    }

    private fun initViews() {
        binding?.apply {
            searchPokemonButton.setOnClickListener { navController?.navigate(navDirectionsSearch) }
            randomPokemonButton.setOnClickListener { navController?.navigate(navDirectionsRandom) }
            favoritesButton.setOnClickListener { }
        }
    }

    override fun onDestroyView() {
        binding = null
        navController = null
        super.onDestroyView()
    }
}