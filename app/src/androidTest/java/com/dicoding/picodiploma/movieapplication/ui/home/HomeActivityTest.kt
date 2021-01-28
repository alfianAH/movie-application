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
import androidx.test.rule.ActivityTestRule
import com.dicoding.picodiploma.movieapplication.R
import com.dicoding.picodiploma.movieapplication.utils.ConvertDate
import com.dicoding.picodiploma.movieapplication.utils.DataDummy
import com.dicoding.picodiploma.movieapplication.utils.EspressoIdlingResources
import org.hamcrest.core.AllOf.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {

    private val dummyMovie = DataDummy.generateDummyMovies()
    private val dummyTVSeries = DataDummy.generateDummyTVSeries()

    @get:Rule
    var activityRule = ActivityTestRule(HomeActivity::class.java)

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

        // Check matches in releaseDate
        val convertedDate = "Release Date: ${ConvertDate.convertStringToDate(dummyMovie[0].releaseDate)}"
        onView(withId(R.id.text_release_date)).check(matches(withText(convertedDate)))

        // Check matches in summary
        onView(withId(R.id.text_summary)).check(matches(withText(dummyMovie[0].overview)))

        // Check matches in status
        val score = "Score: ${dummyMovie[0].voteAverage} / 10"
        onView(withId(R.id.text_score)).check(matches(withText(score)))

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
        onView(withId(R.id.text_title)).check(matches(withText(dummyTVSeries[0].name)))

        // Check matches in releaseYear
        val convertedDate = "Release Date: ${ConvertDate.convertStringToDate(dummyTVSeries[0].firstAirDate)}"
        onView(withId(R.id.text_release_date)).check(matches(withText(convertedDate)))

        // Check matches in summary
        onView(withId(R.id.text_summary)).check(matches(withText(dummyTVSeries[0].overview)))

        // Check matches in status
        val score = "Score: ${dummyTVSeries[0].voteAverage} / 10"
        onView(withId(R.id.text_score)).check(matches(withText(score)))

        // Check image is displayed
        onView(withId(R.id.image_poster)).check(matches(isDisplayed()))
    }
}