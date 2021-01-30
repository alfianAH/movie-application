package com.dicoding.picodiploma.movieapplication.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.ViewPagerActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.dicoding.picodiploma.movieapplication.R
import com.dicoding.picodiploma.movieapplication.utils.ConvertDate
import com.dicoding.picodiploma.movieapplication.utils.DataDummy
import com.dicoding.picodiploma.movieapplication.utils.EspressoIdlingResources
import org.hamcrest.core.AllOf.allOf
import org.junit.After
import org.junit.Before
import org.junit.Test

class HomeActivityTest {

    private val dummyMovies = DataDummy.generateDummyMovies()
    private val dummyTVSeries = DataDummy.generateDummyTVSeries()
    private val dummyDetailMovie = DataDummy.generateDummyDetailMovie()
    private val dummyDetailTVSeries = DataDummy.generateDummyDetailTVSeries()

    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResources.espressoTestIdlingResource)
    }

    @After
    fun tearDown(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResources.espressoTestIdlingResource)
    }

    @Test
    fun loadMovies(){
        // Check data is displayed or not
        onView(allOf(isDisplayed(), withId(R.id.rv_list)))

        // Scroll until last position
        onView(allOf(isDisplayed(), withId(R.id.rv_list)))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovies.size))
    }

    @Test
    fun loadTVSeries(){
        // Scroll view pager to tv series
        onView(withId(R.id.view_pager)).perform(ViewPagerActions.scrollRight(true))

        // Check data is displayed or not
        onView(allOf(isDisplayed(), withId(R.id.rv_list)))

        // Scroll until last position
        onView(allOf(isDisplayed(), withId(R.id.rv_list)))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTVSeries.size))
    }

    @Test
    fun loadDetailMovie(){
        // Click the first list
        onView(allOf(isDisplayed(), withId(R.id.rv_list)))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        // Check matches in title
        onView(withId(R.id.text_title)).check(matches(withText(dummyDetailMovie.title)))

        // Check matches in status
        val status = "Status: ${dummyDetailMovie.status}"
        onView(withId(R.id.text_status)).check(matches(withText(status)))

        // Check matches in status
        val score = "Score: ${dummyDetailMovie.voteAverage} / 10"
        onView(withId(R.id.text_score)).check(matches(withText(score)))

        // Check matches in releaseDate
        val convertedDate = "Release Date: ${ConvertDate.convertStringToDate(dummyDetailMovie.releaseDate)}"
        onView(withId(R.id.text_release_date)).check(matches(withText(convertedDate)))

        // Check genres
        onView(allOf(isDisplayed(), withId(R.id.rv_genre_list)))
        // Scroll until last position
        onView(allOf(isDisplayed(), withId(R.id.rv_genre_list)))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyDetailMovie.genres.size))

        // Check matches in summary
        onView(withId(R.id.text_summary)).check(matches(withText(dummyDetailMovie.overview)))

        // Check image is displayed
        onView(withId(R.id.image_poster)).check(matches(isDisplayed()))
    }

    @Test
    fun loadDetailTVSeries(){
        // Scroll view pager to tv series
        onView(withId(R.id.view_pager)).perform(ViewPagerActions.scrollRight(true))

        // Click the first list
        onView(allOf(isDisplayed(), withId(R.id.rv_list)))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))

        // Check matches in title
        onView(withId(R.id.text_title)).check(matches(withText(dummyDetailTVSeries.name)))

        // Check matches in status
        val status = "Status: ${dummyDetailTVSeries.status}"
        onView(withId(R.id.text_status)).check(matches(withText(status)))

        // Check matches in status
        val score = "Score: ${dummyDetailTVSeries.voteAverage} / 10"
        onView(withId(R.id.text_score)).check(matches(withText(score)))

        // Check matches in releaseDate
        val convertedDate = "Release Date: ${ConvertDate.convertStringToDate(dummyDetailTVSeries.firstAirDate)}"
        onView(withId(R.id.text_release_date)).check(matches(withText(convertedDate)))

        // Check genres
        onView(allOf(isDisplayed(), withId(R.id.rv_genre_list)))
        // Scroll until last position
        onView(allOf(isDisplayed(), withId(R.id.rv_genre_list)))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyDetailTVSeries.genres.size-1))

        // Check matches in summary
        onView(withId(R.id.text_summary)).check(matches(withText(dummyDetailTVSeries.overview)))

        // Check image is displayed
        onView(withId(R.id.image_poster)).check(matches(isDisplayed()))
    }
}