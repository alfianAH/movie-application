package com.dicoding.picodiploma.movieapplication.ui.genre

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.movieapplication.data.GenresItem
import com.dicoding.picodiploma.movieapplication.databinding.ItemsGenreBinding

class GenreAdapter: RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {

    private val listGenres = ArrayList<GenresItem>()

    fun setGenres(genres: List<GenresItem>?){
        if(genres == null) return
        listGenres.clear()
        listGenres.addAll(genres)
    }

    class GenreViewHolder(private val binding: ItemsGenreBinding):
            RecyclerView.ViewHolder(binding.root) {

        fun bind(genre: GenresItem){
            with(binding){
                textGenre.text = genre.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val itemsGenreBinding = ItemsGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GenreViewHolder(itemsGenreBinding)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val genre = listGenres[position]
        holder.bind(genre)
    }

    override fun getItemCount(): Int = listGenres.size
}