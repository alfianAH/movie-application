package com.dicoding.picodiploma.movieapplication.ui.favorite

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dicoding.picodiploma.movieapplication.R

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager):
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object{
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.movie,
            R.string.tv_series
        )
    }

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        return FavoriteFragment.newInstance(position+1)
    }

    override fun getPageTitle(position: Int): CharSequence? =
        context.resources.getString(TAB_TITLES[position])
}