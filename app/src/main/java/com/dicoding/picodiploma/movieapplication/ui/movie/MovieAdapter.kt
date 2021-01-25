package com.dicoding.picodiploma.movieapplication.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.movieapplication.R
import com.dicoding.picodiploma.movieapplication.data.MovieResultsItem
import com.dicoding.picodiploma.movieapplication.databinding.ItemsMovieBinding

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    companion object{
        private const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"
    }

    private var listMovies = ArrayList<MovieResultsItem>()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    fun setMovies(movies: List<MovieResultsItem>?){
        if(movies == null) return
        listMovies.clear()
        listMovies.addAll(movies)
    }

    inner class MovieViewHolder(private val binding: ItemsMovieBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieResultsItem){
            with(binding){
                textItemTitle.text = movie.title
                textScore.text = itemView.context.getString(R.string.score, movie.voteAverage)
                textSummary.text = movie.overview

                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(movie) }

                Glide.with(itemView.context)
                        .load(IMAGE_URL + movie.posterPath)
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                        .error(R.drawable.ic_error)
                        .into(imgPoster)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemsMovieBinding = ItemsMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemsMovieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listMovies.size

    interface OnItemClickCallback{
        fun onItemClicked(movie: MovieResultsItem)
    }
}