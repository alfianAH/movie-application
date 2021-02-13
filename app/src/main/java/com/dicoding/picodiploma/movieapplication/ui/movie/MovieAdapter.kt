package com.dicoding.picodiploma.movieapplication.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.movieapplication.R
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.movie.MovieEntity
import com.dicoding.picodiploma.movieapplication.databinding.ItemsMovieBinding

class MovieAdapter: PagedListAdapter<MovieEntity, MovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    companion object{
        private const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>(){
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    inner class MovieViewHolder(private val binding: ItemsMovieBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity){
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
        val movie = getItem(position)
        if(movie != null) holder.bind(movie)
    }

    fun getSwipedData(swipedPosition: Int): MovieEntity? = getItem(swipedPosition)

    interface OnItemClickCallback{
        fun onItemClicked(movie: MovieEntity)
    }
}