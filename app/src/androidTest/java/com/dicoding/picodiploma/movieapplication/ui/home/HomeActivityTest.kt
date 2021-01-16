package com.dicoding.picodiploma.movieapplication.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.ViewPagerActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.dicoding.picodiploma.movieapplication.R
import com.dicoding.picodiploma.movieapplication.utils.DataDummy
import org.hamcrest.core.AllOf.allOf
import org.junit.Before
import org.junit.Test

class HomeActivityTest {

    private val dummyMovie = DataDummy.generateDummyMovies()
    private val dummyTVSeries = DataDummy.generateDummyTVSeries()

    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
    }

    @Test
    fun loadMovies(){
        // Check data is displayed or not
        onView(allOf(isDisplayed(), withId(R.id.rv_list)))
        // Scroll until last position
        onView(allOf(isDisplayed(), withId(R.id.rv_list)))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun loadTVSeries(){
        // Scroll view pager to tv series
        onView(withId(R.id.view_pager)).perform(ViewPagerActions.scrollRight(true))
        // Check data is displayed or not
        onView(allOf(isDisplayed(), withId(R.id.rv_list)))
        // Scroll until last position
        onView(allOf(isDisplayed(), withId(R.id.rv_list)))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun loadDetailMovie(){
        // Click the first list
        onView(allOf(isDisplayed(), withId(R.id.rv_list)))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        // Check matches in title
        onView(withId(R.id.text_title)).check(matches(withText(dummyMovie[0].title)))
        // Check matches in director
        onView(withId(R.id.text_directors)).check(matches(withText(dummyMovie[0].director)))
        // Check matches in genre
        onView(withId(R.id.text_genre)).check(matches(withText(dummyMovie[0].genre)))
        // Check matches in releaseYear
        onView(withId(R.id.text_release_year)).check(matches(withText(dummyMovie[0].releaseYear.toString())))
        // Check matches in summary
        onView(withId(R.id.text_summary)).check(matches(withText(dummyMovie[0].summary)))
        // Check image is displayed
        onView(withId(R.id.image_poster)).check(matches(isDisplayed()))
    }

    @Test
    fun loadDetailTVSeries(){
        // Scroll view pager to tv series
        onView(withId(R.id.view_pager)).perform(ViewPagerActions.scrollRight(true))
        // Click the first list
        onView(allOf(isDisplayed(), withId(R.id.rv_list)))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        // Check matches in title
        onView(withId(R.id.text_title)).check(matches(withText(dummyTVSeries[0].title)))
        // Check matches in creator
        onView(withId(R.id.text_directors)).check(matches(withText(dummyTVSeries[0].creator)))
        // Check matches in genre
        onView(withId(R.id.text_genre)).check(matches(withText(dummyTVSeries[0].genre)))
        // Check matches in releaseYear
        onView(withId(R.id.text_release_year)).check(matches(withText(dummyTVSeries[0].releaseYear.toString())))
        // Check matches in summary
        onView(withId(R.id.text_summary)).check(matches(withText(dummyTVSeries[0].summary)))
        // Check image is displayed
        onView(withId(R.id.image_poster)).check(matches(isDisplayed()))
    }
}