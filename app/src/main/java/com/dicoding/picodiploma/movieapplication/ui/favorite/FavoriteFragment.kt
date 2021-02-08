package com.dicoding.picodiploma.movieapplication.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dicoding.picodiploma.movieapplication.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {

    private lateinit var fragmentFavoriteBinding: FragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentFavoriteBinding =
            FragmentFavoriteBinding.inflate(layoutInflater, container, false)

        // Inflate the layout for this fragment
        return fragmentFavoriteBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if(activity != null){
            val sectionsPagerAdapter = SectionsPagerAdapter(requireActivity(), childFragmentManager)
            fragmentFavoriteBinding.viewPager.adapter = sectionsPagerAdapter
            fragmentFavoriteBinding.tabs.setupWithViewPager(
                fragmentFavoriteBinding.viewPager
            )
        }

    }
}