package com.katyrin.bravedeveloperstest.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.katyrin.bravedeveloperstest.R
import com.katyrin.bravedeveloperstest.databinding.FragmentFavoritesBinding
import com.katyrin.bravedeveloperstest.model.entities.PokemonDTO
import com.katyrin.bravedeveloperstest.utils.toast
import com.katyrin.bravedeveloperstest.viewmodel.AppState
import com.katyrin.bravedeveloperstest.viewmodel.FavoritesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment : Fragment() {

    private val viewModel: FavoritesViewModel by viewModel()
    private var binding: FragmentFavoritesBinding? = null
    private var adapter: FavoritesAdapter? = null
    private var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentFavoritesBinding.inflate(inflater, container, false)
        .also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(requireActivity(), R.id.main_container)
        viewModel.liveData.observe(viewLifecycleOwner) { renderData(it) }
        updatePokemonList(listOf())
    }

    private fun renderData(appState: AppState<List<PokemonDTO>>) {
        when (appState) {
            is AppState.Success -> updatePokemonList(appState.value)
            is AppState.Error -> toast(R.string.empty_list)
            is AppState.Loading -> {
            }
        }
    }

    private fun updatePokemonList(pokemonList: List<PokemonDTO>) {
        adapter = FavoritesAdapter(pokemonList, ::onClickItem, ::onDeleteItem)
        binding?.recyclerView?.adapter = adapter
    }

    private fun onClickItem(pokemonDTO: PokemonDTO) {
        val navDirections =
            FavoritesFragmentDirections.actionFavoritesFragmentToInfoFragment(pokemonDTO)
        navController?.navigate(navDirections)
    }

    private fun onDeleteItem(pokemonDTO: PokemonDTO) {
        adapter = null
        viewModel.deletePokemon(pokemonDTO)
    }

    override fun onDestroyView() {
        binding = null
        adapter = null
        navController = null
        super.onDestroyView()
    }
}