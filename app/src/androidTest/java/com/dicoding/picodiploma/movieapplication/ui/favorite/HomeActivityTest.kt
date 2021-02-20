package com.dicoding.picodiploma.movieapplication.ui.favorite

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.ViewPagerActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.dicoding.picodiploma.movieapplication.R
import com.dicoding.picodiploma.movieapplication.ui.home.HomeActivity
import com.dicoding.picodiploma.movieapplication.utils.ConvertDate
import com.dicoding.picodiploma.movieapplication.utils.DataDummy
import com.dicoding.picodiploma.movieapplication.utils.EspressoIdlingResources
import org.hamcrest.core.AllOf.allOf
import org.hamcrest.core.Is.`is`
import org.hamcrest.core.IsInstanceOf.instanceOf
import org.junit.After
import org.junit.Before
import org.junit.Test

class HomeActivityTest {

    private val dummyMovies = DataDummy.generateDummyMovies()
    private val dummyTVSeries = DataDummy.generateDummyTVSeries()
    private val dummyDetailMovie = DataDummy.generateDummyDetailMovie(dummyMovies[0], false)
    private val dummyDetailTVSeries = DataDummy.generateDummyDetailTVSeries(dummyTVSeries[0], false)

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

        // Scroll until dummyMovies.size
        onView(allOf(isDisplayed(), withId(R.id.rv_list)))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovies.size))
    }

    @Test
    fun loadTVSeries(){
        // Change bottom navigation to tv series
        onView(allOf(isDisplayed(), withText(R.string.tv_series))).perform(click())

        // Check data is displayed or not
        onView(allOf(isDisplayed(), withId(R.id.rv_list)))

        // Scroll until dummyTVSeries.size
        onView(allOf(isDisplayed(), withId(R.id.rv_list)))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTVSeries.size))
    }

    @Test
    fun sortMovies(){
        // Click spinner
        onView(allOf(isDisplayed(), withId(R.id.sort_dropdown))).perform(click())

        // Sort by rating
        onData(allOf(`is`(instanceOf(String::class.java)), `is`("Rating"))).perform(click())

        // Check data is displayed or not
        onView(allOf(isDisplayed(), withId(R.id.rv_list)))
    }

    @Test
    fun sortTVSeries(){
        // Change bottom navigation to tv series
        onView(allOf(isDisplayed(), withText(R.string.tv_series))).perform(click())

        // Click spinner
        onView(allOf(isDisplayed(), withId(R.id.sort_dropdown))).perform(click())

        // Sort by rating
        onData(allOf(`is`(instanceOf(String::class.java)), `is`("Rating"))).perform(click())

        // Check data is displayed or not
        onView(allOf(isDisplayed(), withId(R.id.rv_list)))
    }

    @Test
    fun loadFavoriteMovies(){
        // Change bottom navigation to favorite
        onView(allOf(isDisplayed(), withText(R.string.favorite))).perform(click())

        // Check data is displayed or not
        onView(allOf(isDisplayed(), withId(R.id.rv_list)))
    }

    @Test
    fun loadFavoriteTVSeries(){
        // Change bottom navigation to favorite
        onView(allOf(isDisplayed(), withText(R.string.favorite))).perform(click())

        // Scroll view pager to tv series
        onView(withId(R.id.view_pager)).perform(ViewPagerActions.scrollRight(true))

        // Check data is displayed or not
        onView(allOf(isDisplayed(), withId(R.id.rv_list)))
    }

    @Test
    fun sortFavoriteMovies(){
        // Change bottom navigation to favorite
        onView(allOf(isDisplayed(), withText(R.string.favorite))).perform(click())

        // Click spinner
        onView(allOf(isDisplayed(), withId(R.id.sort_dropdown))).perform(click())

        // Sort by rating
        onData(allOf(`is`(instanceOf(String::class.java)), `is`("Rating"))).perform(click())

        // Check data is displayed or not
        onView(allOf(isDisplayed(), withId(R.id.rv_list)))
    }

    @Test
    fun sortFavoriteTVSeries(){
        // Change bottom navigation to favorite
        onView(allOf(isDisplayed(), withText(R.string.favorite))).perform(click())

        // Scroll view pager to tv series
        onView(withId(R.id.view_pager)).perform(ViewPagerActions.scrollRight(true))

        // Click spinner
        onView(allOf(isDisplayed(), withId(R.id.sort_dropdown))).perform(click())

        // Sort by rating
        onData(allOf(`is`(instanceOf(String::class.java)), `is`("Rating"))).perform(click())

        // Check data is displayed or not
        onView(allOf(isDisplayed(), withId(R.id.rv_list)))
    }

    @Test
    fun loadDetailMovie(){
        // Click the first list
        onView(allOf(isDisplayed(), withId(R.id.rv_list)))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        checkDetailMovie()
    }

    @Test
    fun loadDetailTVSeries(){
        // Change bottom navigation to tv series
        onView(allOf(isDisplayed(), withText(R.string.tv_series))).perform(click())

        // Click the first list
        onView(allOf(isDisplayed(), withId(R.id.rv_list)))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        checkDetailTVSeries()
    }

    @Test
    fun loadDetailFavoriteMovie(){
        // Click the first list
        onView(allOf(isDisplayed(), withId(R.id.rv_list)))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        // Click favorite floating action button
        onView(withId(R.id.fab_favorite)).perform(click())

        // Back to home
        onView(isRoot()).perform(ViewActions.pressBack())

        // Change bottom navigation to favorite
        onView(allOf(isDisplayed(), withText(R.string.favorite))).perform(click())

        // Check data is displayed or not
        onView(allOf(isDisplayed(), withId(R.id.rv_list)))

        // Click the first list
        onView(allOf(isDisplayed(), withId(R.id.rv_list)))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        checkDetailMovie()

        // Click favorite floating action button to remove favorite
        onView(withId(R.id.fab_favorite)).perform(click())

        // Back to home
        onView(isRoot()).perform(ViewActions.pressBack())
    }

    @Test
    fun loadDetailFavoriteTVSeries(){
        // Change bottom navigation to tv series
        onView(allOf(isDisplayed(), withText(R.string.tv_series))).perform(click())

        // Click the first list
        onView(allOf(isDisplayed(), withId(R.id.rv_list)))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        // Click favorite floating action button
        onView(withId(R.id.fab_favorite)).perform(click())

        // Back to home
        onView(isRoot()).perform(ViewActions.pressBack())

        // Change bottom navigation to favorite
        onView(allOf(isDisplayed(), withText(R.string.favorite))).perform(click())

        // Scroll view pager to tv series
        onView(withId(R.id.view_pager)).perform(ViewPagerActions.scrollRight(true))

        // Check data is displayed or not
        onView(allOf(isDisplayed(), withId(R.id.rv_list)))

        // Click the first list
        onView(allOf(isDisplayed(), withId(R.id.rv_list)))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        checkDetailTVSeries()

        // Click favorite floating action button to remove favorite
        onView(withId(R.id.fab_favorite)).perform(click())

        // Back to home
        onView(isRoot()).perform(ViewActions.pressBack())
    }

    private fun checkDetailMovie(){
        // Check matches in title
        onView(withId(R.id.text_title)).check(matches(withText(dummyDetailMovie.movieEntity.title)))

        // Check matches in score
        val score = "Rating: ${dummyDetailMovie.movieEntity.voteAverage} / 10"
        onView(withId(R.id.text_score)).check(matches(withText(score)))

        // Check matches in releaseDate
        val convertedDate = "Release Date: ${ConvertDate.convertStringToDate(
            dummyDetailMovie.movieEntity.releaseDate)}"
        onView(withId(R.id.text_release_date)).check(matches(withText(convertedDate)))

        // Check genres
        onView(allOf(isDisplayed(), withId(R.id.rv_genre_list)))

        // Scroll until last position
        onView(allOf(isDisplayed(), withId(R.id.rv_genre_list)))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyDetailMovie.genres.size))

        // Check matches in summary
        onView(withId(R.id.text_summary)).check(matches(withText(dummyDetailMovie.movieEntity.overview)))

        // Check image is displayed
        onView(withId(R.id.image_poster)).check(matches(isDisplayed()))
    }

    private fun checkDetailTVSeries(){
        // Check matches in title
        onView(withId(R.id.text_title)).check(matches(withText(dummyDetailTVSeries.tvSeriesEntity.title)))

        // Check matches in score
        val score = "Rating: ${dummyDetailTVSeries.tvSeriesEntity.voteAverage} / 10"
        onView(withId(R.id.text_score)).check(matches(withText(score)))

        // Check matches in releaseDate
        val convertedDate = "Release Date: ${ConvertDate.convertStringToDate(
            dummyDetailTVSeries.tvSeriesEntity.releaseDate)}"
        onView(withId(R.id.text_release_date)).check(matches(withText(convertedDate)))

        // Check genres
        onView(allOf(isDisplayed(), withId(R.id.rv_genre_list)))

        // Scroll until last position
        onView(allOf(isDisplayed(), withId(R.id.rv_genre_list)))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyDetailTVSeries.genres.size-1))

        // Check matches in summary
        onView(withId(R.id.text_summary)).check(matches(withText(dummyDetailTVSeries.tvSeriesEntity.overview)))

        // Check image is displayed
        onView(withId(R.id.image_poster)).check(matches(isDisplayed()))
    }
}