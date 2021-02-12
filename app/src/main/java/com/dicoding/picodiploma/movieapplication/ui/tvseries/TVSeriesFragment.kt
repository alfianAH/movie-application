package com.dicoding.picodiploma.movieapplication.ui.tvseries

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.tvseries.TVSeriesEntity
import com.dicoding.picodiploma.movieapplication.databinding.FragmentTvSeriesBinding
import com.dicoding.picodiploma.movieapplication.ui.detail.DetailActivity
import com.dicoding.picodiploma.movieapplication.valueobject.Status.*
import com.dicoding.picodiploma.movieapplication.viewmodel.ViewModelFactory

class TVSeriesFragment : Fragment() {

    private lateinit var fragmentTVSeriesBinding: FragmentTvSeriesBinding
    private lateinit var viewModel: TVSeriesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentTVSeriesBinding = FragmentTvSeriesBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return fragmentTVSeriesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(activity != null){
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[TVSeriesViewModel::class.java]

            showTVSeriesList()
        }
    }

    /**
     * Show movie list in fragment
     */
    private fun showTVSeriesList(){
        val tvSeriesAdapter = TVSeriesAdapter()

        viewModel.getTVSeries().observe(viewLifecycleOwner, { tvSeries ->
            if (tvSeries != null) {
                when (tvSeries.status) {
                    LOADING -> setLoading(true)

                    SUCCESS -> {
                        setLoading(false)
                        tvSeriesAdapter.setTVSeries(tvSeries.data)
                        tvSeriesAdapter.notifyDataSetChanged()
                    }

                    ERROR -> {
                        setLoading(false)
                        Toast.makeText(context, "Something wrong", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })


        with(fragmentTVSeriesBinding.rvList) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = tvSeriesAdapter
        }

        tvSeriesAdapter.setOnItemClickCallback(object : TVSeriesAdapter.OnItemClickCallback {
            override fun onItemClicked(tvSeries: TVSeriesEntity) {
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_TV_SERIES, tvSeries.id)
                intent.putExtra(DetailActivity.EXTRA_ID, DetailActivity.TV_SERIES_ID)
                startActivity(intent)
            }
        })
    }

    /**
     * Set visibility of progressBar
     */
    private fun setLoading(isVisible: Boolean){
        if(isVisible)
            fragmentTVSeriesBinding.progressBar.visibility = View.VISIBLE
        else
            fragmentTVSeriesBinding.progressBar.visibility = View.GONE
    }
}