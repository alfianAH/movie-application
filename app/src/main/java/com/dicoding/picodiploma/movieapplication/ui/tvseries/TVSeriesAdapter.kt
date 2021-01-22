package com.dicoding.picodiploma.movieapplication.ui.tvseries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.movieapplication.R
import com.dicoding.picodiploma.movieapplication.data.TVSeriesEntity
import com.dicoding.picodiploma.movieapplication.databinding.ItemsMovieBinding

class TVSeriesAdapter: RecyclerView.Adapter<TVSeriesAdapter.TVSeriesViewHolder>() {

    private var listTVSeries = ArrayList<TVSeriesEntity>()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    fun setTVSeries(tvSeries: List<TVSeriesEntity>?){
        if(tvSeries == null) return
        listTVSeries.clear()
        listTVSeries.addAll(tvSeries)
    }

    inner class TVSeriesViewHolder(private val binding: ItemsMovieBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvSeries: TVSeriesEntity){
            with(binding){
                textItemTitle.text = tvSeries.title
                textItemGenre.text = tvSeries.genre
                textSummary.text = tvSeries.summary

                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(tvSeries) }

                Glide.with(itemView.context)
                        .load(tvSeries.imagePath)
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
        fun onItemClicked(tvSeries: TVSeriesEntity)
    }
}