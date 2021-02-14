package com.bironader.musicsearch.framework.presentation.musiclist

import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.matcher.ViewMatchers.Visibility.GONE
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bironader.musicsearch.R
import com.bironader.musicsearch.framework.presentation.MainActivity
import com.bironader.musicsearch.framework.presentation.musiclist.widget.MusicListAdapter
import com.bironader.musicsearch.framework.utils.EspressoIdlingResource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class MusicListFragmentTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun search() {
        onView(withId(R.id.search)).check(matches((isDisplayed())))
        onView(withId(R.id.search)).perform(click())
        onView(withId(R.id.search)).perform(typeSearchViewText("asdasdasdas"))
        var isEmpty = false
        Thread.sleep(500)
        scenario.onActivity {
            isEmpty = it.findViewById<RecyclerView>(R.id.musicList).adapter?.itemCount == 0
        }
        if (isEmpty) {
            onView(withId(R.id.empty)).check(matches((isDisplayed())))
        } else {
            onView(withId(R.id.empty)).check(
                matches(
                    withEffectiveVisibility(
                        GONE
                    )
                )
            )
            onView(withId(R.id.musicList)).check(matches((isDisplayed())))

        }
    }


    @Test
    fun whenClickGoToDetails() {
        onView(withId(R.id.search)).check(matches((isDisplayed())))
        onView(withId(R.id.search)).perform(click())
        onView(withId(R.id.search)).perform(typeSearchViewText("asd"))
        var isEmpty = false
        Thread.sleep(500)
        scenario.onActivity {
            isEmpty = it.findViewById<RecyclerView>(R.id.musicList).adapter?.itemCount == 0
        }
        if (isEmpty.not()) {
            onView(withId(R.id.musicList)).perform(
                RecyclerViewActions.actionOnItemAtPosition<MusicListAdapter.MusicHolder>(
                    1,
                    click()
                )
            )
            onView(withId(R.id.musicDetail)).check(matches(isDisplayed()))
        }
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)

    }
}

fun typeSearchViewText(text: String): ViewAction {
    return object : ViewAction {
        override fun getDescription(): String {
            return "Change view text"
        }

        override fun getConstraints(): Matcher<View> {
            return allOf(isDisplayed(), isAssignableFrom(SearchView::class.java))
        }

        override fun perform(uiController: UiController?, view: View?) {
            (view as SearchView).setQuery(text, false)
        }
    }
}