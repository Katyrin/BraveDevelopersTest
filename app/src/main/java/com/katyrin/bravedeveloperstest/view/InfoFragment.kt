package com.katyrin.bravedeveloperstest.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.katyrin.bravedeveloperstest.databinding.FragmentInfoBinding
import com.katyrin.bravedeveloperstest.model.entities.PokemonDTO
import com.katyrin.bravedeveloperstest.utils.loadPokemonImage

class InfoFragment : Fragment() {

    private var binding: FragmentInfoBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentInfoBinding.inflate(inflater, container, false)
        .also { binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pokemonData = InfoFragmentArgs.fromBundle(requireArguments()).pokemonDTO
        initViews(pokemonData)
    }

    private fun initViews(pokemonDTO: PokemonDTO?) {
        binding?.apply {
            pokemonName.text = pokemonDTO?.name
            pokemonImage.loadPokemonImage(pokemonDTO?.imageUrl)
            heightValue.text = pokemonDTO?.height.toString()
            weightValue.text = pokemonDTO?.weight.toString()
            typeValue.text = pokemonDTO?.types.toString()
            attackValue.text = pokemonDTO?.attack.toString()
            defenseValue.text = pokemonDTO?.defense.toString()
            hpValue.text = pokemonDTO?.hp.toString()
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}