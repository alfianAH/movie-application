package com.dicoding.picodiploma.movieapplication.ui.tvseries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.movieapplication.R
import com.dicoding.picodiploma.movieapplication.data.source.remote.response.TVSeriesResultsItem
import com.dicoding.picodiploma.movieapplication.databinding.ItemsMovieBinding

class TVSeriesAdapter: RecyclerView.Adapter<TVSeriesAdapter.TVSeriesViewHolder>() {

    companion object{
        private const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"
    }

    private var listTVSeries = ArrayList<TVSeriesResultsItem>()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    fun setTVSeries(tvSeries: List<TVSeriesResultsItem>?){
        if(tvSeries == null) return
        listTVSeries.clear()
        listTVSeries.addAll(tvSeries)
    }

    inner class TVSeriesViewHolder(private val binding: ItemsMovieBinding):
            RecyclerView.ViewHolder(binding.root) {

        fun bind(tvSeries: TVSeriesResultsItem){
            with(binding){
                textItemTitle.text = tvSeries.name
                textScore.text = itemView.context.getString(R.string.score, tvSeries.voteAverage)
                textSummary.text = tvSeries.overview

                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(tvSeries) }

                Glide.with(itemView.context)
                        .load(IMAGE_URL + tvSeries.posterPath)
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                        .error(R.drawable.ic_error)
                        .into(imgPoster)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVSeriesViewHolder {
        val itemsMovieBinding = ItemsMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TVSeriesViewHolder(itemsMovieBinding)
    }

    override fun onBindViewHolder(holder: TVSeriesViewHolder, position: Int) {
        val movie = listTVSeries[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listTVSeries.size

    interface OnItemClickCallback{
        fun onItemClicked(tvSeries: TVSeriesResultsItem)
    }
}