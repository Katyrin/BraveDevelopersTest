package com.katyrin.bravedeveloperstest.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.katyrin.bravedeveloperstest.databinding.ItemFavoriteBinding
import com.katyrin.bravedeveloperstest.model.entities.PokemonDTO
import com.katyrin.bravedeveloperstest.utils.loadPokemonImage

class FavoritesAdapter(
    private val pokemonList: List<PokemonDTO> = listOf(),
    private val onClickItem: (PokemonDTO) -> Unit,
    private val onDeleteItem: (PokemonDTO) -> Unit
) : RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {

    inner class FavoritesViewHolder(
        private val itemBinding: ItemFavoriteBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(pokemonDTO: PokemonDTO): Unit = with(itemBinding) {
            previewImage.loadPokemonImage(pokemonDTO.imageUrl)
            nameTextView.text = pokemonDTO.name
            root.setOnClickListener { onClickItem(pokemonDTO) }
            deleteImageView.setOnClickListener { onDeleteItem(pokemonDTO) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder =
        FavoritesViewHolder(
            ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.bind(pokemonList[position])
    }

    override fun getItemCount(): Int = pokemonList.size
}