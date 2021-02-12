package com.dicoding.picodiploma.movieapplication.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.movie.MovieGenreEntity
import com.dicoding.picodiploma.movieapplication.data.source.remote.response.GenresItem
import com.dicoding.picodiploma.movieapplication.databinding.ItemsGenreBinding

class MovieGenreAdapter: RecyclerView.Adapter<MovieGenreAdapter.GenreViewHolder>() {

    private val listGenres = ArrayList<MovieGenreEntity>()

    fun setGenres(genres: List<MovieGenreEntity>?){
        if(genres == null) return
        listGenres.clear()
        listGenres.addAll(genres)
    }

    class GenreViewHolder(private val binding: ItemsGenreBinding):
            RecyclerView.ViewHolder(binding.root) {

        fun bind(genre: MovieGenreEntity){
            with(binding){
                textGenre.text = genre.genreName
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