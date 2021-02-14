package com.bironader.musicsearch.framework.presentation.musicdetail

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bironader.musicsearch.R
import com.bironader.musicsearch.framework.presentation.MainActivity
import com.bironader.musicsearch.framework.presentation.musiclist.typeSearchViewText
import com.bironader.musicsearch.framework.presentation.musiclist.widget.MusicListAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class MusicDetailFragmentTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
//        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)

    }


    @Test
    fun openDetailsAndCheckViews() {
        onView(withId(R.id.search)).check(matches((isDisplayed())))
        onView(withId(R.id.search)).perform(ViewActions.click())
        onView(withId(R.id.search)).perform(typeSearchViewText("asd"))
        var isEmpty = false
        Thread.sleep(500)
        scenario.onActivity {
            isEmpty = it.findViewById<RecyclerView>(R.id.musicList).adapter?.itemCount == 0
        }
        if (isEmpty.not()) {
            onView(withId(R.id.musicList)).perform(
                RecyclerViewActions.actionOnItemAtPosition<MusicListAdapter.MusicHolder>(
                    0,
                    ViewActions.click()
                )
            )
            Thread.sleep(1000)
            onView(withId(R.id.musicDetail)).check(matches(isDisplayed()))
            onView(withId(R.id.image)).check(matches(isDisplayed()))
            onView(withId(R.id.by)).check(matches(isDisplayed()))
            onView(withId(R.id.type)).check(matches(isDisplayed()))
            onView(withId(R.id.published_date)).check(matches(isDisplayed()))
            onView(withId(R.id.title)).check(matches(isDisplayed()))
        }


    }



    @After
    fun tearDown() {
//        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)

    }
}