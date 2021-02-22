package com.dicoding.picodiploma.movieapplication.ui.detail

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.ViewPagerActions
import androidx.test.espresso.matcher.ViewMatchers
import com.dicoding.picodiploma.movieapplication.R
import com.dicoding.picodiploma.movieapplication.ui.home.HomeActivity
import com.dicoding.picodiploma.movieapplication.utils.ConvertDate
import com.dicoding.picodiploma.movieapplication.utils.DataDummy
import com.dicoding.picodiploma.movieapplication.utils.EspressoIdlingResources
import org.hamcrest.core.AllOf
import org.junit.After
import org.junit.Before
import org.junit.Test

class DetailActivityTest {

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
    fun loadDetailMovie(){
        // Click the first list
        Espresso.onView(AllOf.allOf(ViewMatchers.isDisplayed(), ViewMatchers.withId(R.id.rv_list)))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))

        checkDetailMovie()
    }

    @Test
    fun loadDetailTVSeries(){
        // Change bottom navigation to tv series
        Espresso.onView(AllOf.allOf(ViewMatchers.isDisplayed(), ViewMatchers.withText(R.string.tv_series))).perform(ViewActions.click())

        // Click the first list
        Espresso.onView(AllOf.allOf(ViewMatchers.isDisplayed(), ViewMatchers.withId(R.id.rv_list)))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))

        checkDetailTVSeries()
    }

    @Test
    fun loadDetailFavoriteMovie(){
        // Click the first list
        Espresso.onView(AllOf.allOf(ViewMatchers.isDisplayed(), ViewMatchers.withId(R.id.rv_list)))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))

        // Click favorite floating action button
        Espresso.onView(ViewMatchers.withId(R.id.fab_favorite)).perform(ViewActions.click())

        // Back to home
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.pressBack())

        // Change bottom navigation to favorite
        Espresso.onView(AllOf.allOf(ViewMatchers.isDisplayed(), ViewMatchers.withText(R.string.favorite))).perform(ViewActions.click())

        // Check data is displayed or not
        Espresso.onView(AllOf.allOf(ViewMatchers.isDisplayed(), ViewMatchers.withId(R.id.rv_list)))

        // Click the first list
        Espresso.onView(AllOf.allOf(ViewMatchers.isDisplayed(), ViewMatchers.withId(R.id.rv_list)))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))

        checkDetailMovie()

        // Click favorite floating action button to remove favorite
        Espresso.onView(ViewMatchers.withId(R.id.fab_favorite)).perform(ViewActions.click())

        // Back to home
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.pressBack())
    }

    @Test
    fun loadDetailFavoriteTVSeries(){
        // Change bottom navigation to tv series
        Espresso.onView(AllOf.allOf(ViewMatchers.isDisplayed(), ViewMatchers.withText(R.string.tv_series))).perform(ViewActions.click())

        // Click the first list
        Espresso.onView(AllOf.allOf(ViewMatchers.isDisplayed(), ViewMatchers.withId(R.id.rv_list)))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))

        // Click favorite floating action button
        Espresso.onView(ViewMatchers.withId(R.id.fab_favorite)).perform(ViewActions.click())

        // Back to home
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.pressBack())

        // Change bottom navigation to favorite
        Espresso.onView(AllOf.allOf(ViewMatchers.isDisplayed(), ViewMatchers.withText(R.string.favorite))).perform(ViewActions.click())

        // Scroll view pager to tv series
        Espresso.onView(ViewMatchers.withId(R.id.view_pager)).perform(ViewPagerActions.scrollRight(true))

        // Check data is displayed or not
        Espresso.onView(AllOf.allOf(ViewMatchers.isDisplayed(), ViewMatchers.withId(R.id.rv_list)))

        // Click the first list
        Espresso.onView(AllOf.allOf(ViewMatchers.isDisplayed(), ViewMatchers.withId(R.id.rv_list)))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))

        checkDetailTVSeries()

        // Click favorite floating action button to remove favorite
        Espresso.onView(ViewMatchers.withId(R.id.fab_favorite)).perform(ViewActions.click())

        // Back to home
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.pressBack())
    }

    private fun checkDetailMovie(){
        // Check matches in title
        Espresso.onView(ViewMatchers.withId(R.id.text_title)).check(ViewAssertions.matches(ViewMatchers.withText(dummyDetailMovie.movieEntity.title)))

        // Check matches in score
        val score = "Rating: ${dummyDetailMovie.movieEntity.voteAverage} / 10"
        Espresso.onView(ViewMatchers.withId(R.id.text_score)).check(ViewAssertions.matches(ViewMatchers.withText(score)))

        // Check matches in releaseDate
        val convertedDate = "Release Date: ${
            ConvertDate.convertStringToDate(
                dummyDetailMovie.movieEntity.releaseDate)}"
        Espresso.onView(ViewMatchers.withId(R.id.text_release_date)).check(ViewAssertions.matches(ViewMatchers.withText(convertedDate)))

        // Check genres
        Espresso.onView(AllOf.allOf(ViewMatchers.isDisplayed(), ViewMatchers.withId(R.id.rv_genre_list)))

        // Scroll until last position
        Espresso.onView(AllOf.allOf(ViewMatchers.isDisplayed(), ViewMatchers.withId(R.id.rv_genre_list)))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyDetailMovie.genres.size))

        // Check matches in summary
        Espresso.onView(ViewMatchers.withId(R.id.text_summary)).check(ViewAssertions.matches(ViewMatchers.withText(dummyDetailMovie.movieEntity.overview)))

        // Check image is displayed
        Espresso.onView(ViewMatchers.withId(R.id.image_poster)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    private fun checkDetailTVSeries(){
        // Check matches in title
        Espresso.onView(ViewMatchers.withId(R.id.text_title)).check(ViewAssertions.matches(ViewMatchers.withText(dummyDetailTVSeries.tvSeriesEntity.title)))

        // Check matches in score
        val score = "Rating: ${dummyDetailTVSeries.tvSeriesEntity.voteAverage} / 10"
        Espresso.onView(ViewMatchers.withId(R.id.text_score)).check(ViewAssertions.matches(ViewMatchers.withText(score)))

        // Check matches in releaseDate
        val convertedDate = "Release Date: ${
            ConvertDate.convertStringToDate(
                dummyDetailTVSeries.tvSeriesEntity.releaseDate)}"
        Espresso.onView(ViewMatchers.withId(R.id.text_release_date)).check(ViewAssertions.matches(ViewMatchers.withText(convertedDate)))

        // Check genres
        Espresso.onView(AllOf.allOf(ViewMatchers.isDisplayed(), ViewMatchers.withId(R.id.rv_genre_list)))

        // Scroll until last position
        Espresso.onView(AllOf.allOf(ViewMatchers.isDisplayed(), ViewMatchers.withId(R.id.rv_genre_list)))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyDetailTVSeries.genres.size-1))

        // Check matches in summary
        Espresso.onView(ViewMatchers.withId(R.id.text_summary)).check(ViewAssertions.matches(ViewMatchers.withText(dummyDetailTVSeries.tvSeriesEntity.overview)))

        // Check image is displayed
        Espresso.onView(ViewMatchers.withId(R.id.image_poster)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}