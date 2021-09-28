package com.katyrin.bravedeveloperstest.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.katyrin.bravedeveloperstest.R
import com.katyrin.bravedeveloperstest.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {

    private var binding: FragmentMenuBinding? = null
    private var navController: NavController? = null

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
            searchPokemonButton.setOnClickListener { }
            randomPokemonButton.setOnClickListener { }
            favoritesButton.setOnClickListener { }
        }
    }

    override fun onDestroyView() {
        binding = null
        navController = null
        super.onDestroyView()
    }
}