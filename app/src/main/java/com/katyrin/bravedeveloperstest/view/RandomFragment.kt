package com.katyrin.bravedeveloperstest.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.katyrin.bravedeveloperstest.R
import com.katyrin.bravedeveloperstest.databinding.FragmentRandomBinding
import com.katyrin.bravedeveloperstest.model.entities.PokemonDTO
import com.katyrin.bravedeveloperstest.utils.loadPokemonImage
import com.katyrin.bravedeveloperstest.utils.toast
import com.katyrin.bravedeveloperstest.viewmodel.AppState
import com.katyrin.bravedeveloperstest.viewmodel.ErrorState
import com.katyrin.bravedeveloperstest.viewmodel.RandomViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RandomFragment : Fragment() {

    private val viewModel: RandomViewModel by viewModel()
    private var binding: FragmentRandomBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentRandomBinding.inflate(inflater, container, false)
        .also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.liveData.observe(viewLifecycleOwner) { renderData(it) }
        initVies()
    }

    private fun initVies() {
        binding?.apply {
            searchButton.setOnClickListener { viewModel.searchRandomPokemon() }
            favoriteChip.setOnCheckedChangeListener { _, isChecked ->
                viewModel.changeFavoriteStatus(isChecked)
            }
        }
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> showPokemon(appState.pokemonDTO)
            is AppState.Error -> setErrorState(appState.errorState)
            is AppState.Loading -> showLoadingState()
        }
    }

    private fun setErrorState(errorState: ErrorState) {
        showNotFoundState()
        when (errorState) {
            is ErrorState.NotFound -> toast(R.string.enter_another_pokemon)
            is ErrorState.TimOut -> toast(R.string.timeout_error_message)
            is ErrorState.UnknownHost -> toast(R.string.unknown_host_error_message)
            is ErrorState.Connection -> toast(R.string.connection_error_message)
            is ErrorState.Server -> toast(R.string.server_error_message)
            is ErrorState.Unknown -> toast(errorState.message)
        }
    }

    private fun showNotFoundState() {
        binding?.apply {
            progressBar.isVisible = false
            infoLayout.isVisible = false
        }
    }

    private fun showPokemon(pokemonDTO: PokemonDTO) {
        setPokemonData(pokemonDTO)
        binding?.apply {
            progressBar.isVisible = false
            infoLayout.isVisible = true
        }
    }

    private fun setPokemonData(pokemonDTO: PokemonDTO) {
        binding?.apply {
            pokemonName.text = pokemonDTO.name
            favoriteChip.isChecked = pokemonDTO.isFavorite
            pokemonImage.loadPokemonImage(pokemonDTO.imageUrl)
            heightValue.text = pokemonDTO.height.toString()
            weightValue.text = pokemonDTO.weight.toString()
            typeValue.text = pokemonDTO.types.toString()
            attackValue.text = pokemonDTO.attack.toString()
            defenseValue.text = pokemonDTO.defense.toString()
            hpValue.text = pokemonDTO.hp.toString()
        }
    }

    private fun showLoadingState() {
        binding?.apply {
            progressBar.isVisible = true
            infoLayout.isVisible = false
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}