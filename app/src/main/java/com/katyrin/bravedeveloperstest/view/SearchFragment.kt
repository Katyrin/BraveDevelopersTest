package com.katyrin.bravedeveloperstest.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.katyrin.bravedeveloperstest.R
import com.katyrin.bravedeveloperstest.databinding.FragmentSearchBinding
import com.katyrin.bravedeveloperstest.model.entities.PokemonDTO
import com.katyrin.bravedeveloperstest.utils.hideKeyboard
import com.katyrin.bravedeveloperstest.utils.loadPokemonImage
import com.katyrin.bravedeveloperstest.utils.toast
import com.katyrin.bravedeveloperstest.viewmodel.AppState
import com.katyrin.bravedeveloperstest.viewmodel.ErrorState
import com.katyrin.bravedeveloperstest.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModel()
    private var binding: FragmentSearchBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentSearchBinding.inflate(inflater, container, false)
        .also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.liveData.observe(viewLifecycleOwner) { renderData(it) }
        initVies()
    }

    private fun initVies() {
        binding?.apply {
            searchButton.setOnClickListener { searchPokemonByName() }
            inputText.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) searchPokemonByName()
                false
            }
            favoriteChip.setOnCheckedChangeListener { _, isChecked ->
                viewModel.changeFavoriteStatus(isChecked)
            }
        }
    }

    private fun searchPokemonByName() {
        viewModel.searchPokemonByName(binding?.inputText?.text.toString().lowercase())
        hideKeyboard()
    }

    private fun renderData(appState: AppState<PokemonDTO>) {
        when (appState) {
            is AppState.Success -> showPokemon(appState.value)
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
            notFoundText.isVisible = true
            infoLayout.isVisible = false
        }
    }

    private fun showPokemon(pokemonDTO: PokemonDTO) {
        setPokemonData(pokemonDTO)
        binding?.apply {
            progressBar.isVisible = false
            notFoundText.isVisible = false
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
            notFoundText.isVisible = false
            infoLayout.isVisible = false
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}