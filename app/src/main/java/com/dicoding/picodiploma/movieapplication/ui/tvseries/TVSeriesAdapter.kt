package com.dicoding.picodiploma.movieapplication.ui.tvseries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.movieapplication.R
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.tvseries.TVSeriesEntity
import com.dicoding.picodiploma.movieapplication.databinding.ItemsMovieBinding

class TVSeriesAdapter: PagedListAdapter<TVSeriesEntity, TVSeriesAdapter.TVSeriesViewHolder>(DIFF_CALLBACK) {

    companion object{
        private const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TVSeriesEntity>(){
            override fun areItemsTheSame(oldItem: TVSeriesEntity, newItem: TVSeriesEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TVSeriesEntity, newItem: TVSeriesEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    inner class TVSeriesViewHolder(private val binding: ItemsMovieBinding):
            RecyclerView.ViewHolder(binding.root) {

        fun bind(tvSeries: TVSeriesEntity){
            with(binding){
                textItemTitle.text = tvSeries.title
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
        val tvSeries = getItem(position)
        if(tvSeries != null) holder.bind(tvSeries)
    }

    interface OnItemClickCallback{
        fun onItemClicked(tvSeries: TVSeriesEntity)
    }
}