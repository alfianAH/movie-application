package com.dicoding.picodiploma.movieapplication.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.dicoding.picodiploma.movieapplication.R
import com.dicoding.picodiploma.movieapplication.utils.DataDummy
import com.dicoding.picodiploma.movieapplication.utils.EspressoIdlingResources
import org.hamcrest.core.AllOf
import org.hamcrest.core.Is
import org.hamcrest.core.IsInstanceOf
import org.junit.After
import org.junit.Before
import org.junit.Test

class HomeActivityTest {

    private val dummyMovies = DataDummy.generateDummyMovies()
    private val dummyTVSeries = DataDummy.generateDummyTVSeries()

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
        Espresso.onView(AllOf.allOf(ViewMatchers.isDisplayed(), ViewMatchers.withId(R.id.rv_list)))

        // Scroll until dummyMovies.size
        Espresso.onView(AllOf.allOf(ViewMatchers.isDisplayed(), ViewMatchers.withId(R.id.rv_list)))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovies.size))
    }

    @Test
    fun loadTVSeries(){
        // Change bottom navigation to tv series
        Espresso.onView(AllOf.allOf(ViewMatchers.isDisplayed(), ViewMatchers.withText(R.string.tv_series))).perform(ViewActions.click())

        // Check data is displayed or not
        Espresso.onView(AllOf.allOf(ViewMatchers.isDisplayed(), ViewMatchers.withId(R.id.rv_list)))

        // Scroll until dummyTVSeries.size
        Espresso.onView(AllOf.allOf(ViewMatchers.isDisplayed(), ViewMatchers.withId(R.id.rv_list)))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTVSeries.size))
    }

    @Test
    fun sortMovies(){
        // Click spinner
        Espresso.onView(AllOf.allOf(ViewMatchers.isDisplayed(), ViewMatchers.withId(R.id.sort_dropdown))).perform(ViewActions.click())

        // Sort by rating
        Espresso.onData(AllOf.allOf(Is.`is`(IsInstanceOf.instanceOf(String::class.java)), Is.`is`("Rating"))).perform(ViewActions.click())

        // Check data is displayed or not
        Espresso.onView(AllOf.allOf(ViewMatchers.isDisplayed(), ViewMatchers.withId(R.id.rv_list)))
    }

    @Test
    fun sortTVSeries(){
        // Change bottom navigation to tv series
        Espresso.onView(AllOf.allOf(ViewMatchers.isDisplayed(), ViewMatchers.withText(R.string.tv_series))).perform(ViewActions.click())

        // Click spinner
        Espresso.onView(AllOf.allOf(ViewMatchers.isDisplayed(), ViewMatchers.withId(R.id.sort_dropdown))).perform(ViewActions.click())

        // Sort by rating
        Espresso.onData(AllOf.allOf(Is.`is`(IsInstanceOf.instanceOf(String::class.java)), Is.`is`("Rating"))).perform(ViewActions.click())

        // Check data is displayed or not
        Espresso.onView(AllOf.allOf(ViewMatchers.isDisplayed(), ViewMatchers.withId(R.id.rv_list)))
    }
}