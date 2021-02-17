package com.dicoding.picodiploma.movieapplication.ui.tvseries

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.movieapplication.R
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.tvseries.TVSeriesEntity
import com.dicoding.picodiploma.movieapplication.databinding.FragmentTvSeriesBinding
import com.dicoding.picodiploma.movieapplication.ui.detail.DetailActivity
import com.dicoding.picodiploma.movieapplication.utils.SortUtils
import com.dicoding.picodiploma.movieapplication.valueobject.Status.*
import com.dicoding.picodiploma.movieapplication.viewmodel.ViewModelFactory
import java.util.*

class TVSeriesFragment : Fragment(), AdapterView.OnItemSelectedListener {

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

            setSpinner()
        }
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?,
                                position: Int, id: Long) {
        when(parent.getItemAtPosition(position).toString().toLowerCase(Locale.getDefault())){
            parent.resources.getString(R.string.name).toLowerCase(Locale.getDefault()) -> { // Sort by name
                showTVSeriesList(SortUtils.NAME)
            }

            parent.resources.getString(R.string.rating).toLowerCase(Locale.getDefault()) -> {  // Sort by rating
                showTVSeriesList(SortUtils.RATING)
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>) {}

    /**
     * Set spinner resources
     */
    private fun setSpinner(){
        val spinner: Spinner = fragmentTVSeriesBinding.sort.sortDropdown
        ArrayAdapter.createFromResource(
                requireActivity(),
                R.array.sort_array,
                R.layout.support_simple_spinner_dropdown_item
        ).also {adapter ->
            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = this
    }

    /**
     * Show movie list in fragment
     */
    private fun showTVSeriesList(sortBy: String){
        val tvSeriesAdapter = TVSeriesAdapter()

        viewModel.getTVSeries(sortBy).observe(viewLifecycleOwner, { tvSeries ->
            if (tvSeries != null) {
                when (tvSeries.status) {
                    LOADING -> setLoading(true)

                    SUCCESS -> {
                        setLoading(false)
                        tvSeriesAdapter.submitList(tvSeries.data)
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