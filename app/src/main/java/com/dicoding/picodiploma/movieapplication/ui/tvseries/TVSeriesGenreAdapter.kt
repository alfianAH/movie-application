package com.dicoding.picodiploma.movieapplication.ui.tvseries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.tvseries.TVSeriesGenreEntity
import com.dicoding.picodiploma.movieapplication.databinding.ItemsGenreBinding

class TVSeriesGenreAdapter: RecyclerView.Adapter<TVSeriesGenreAdapter.GenreViewHolder>() {

    private val listGenres = ArrayList<TVSeriesGenreEntity>()

    fun setGenres(genres: List<TVSeriesGenreEntity>?){
        if(genres == null) return
        listGenres.clear()
        listGenres.addAll(genres)
    }

    class GenreViewHolder(private val binding: ItemsGenreBinding):
            RecyclerView.ViewHolder(binding.root) {

        fun bind(genre: TVSeriesGenreEntity){
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