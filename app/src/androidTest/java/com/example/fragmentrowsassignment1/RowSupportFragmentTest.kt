package com.example.fragmentrowsassignment1

import android.app.Instrumentation
import android.view.KeyEvent
import android.view.View
import com.example.fragmentrowsassignment1.MovieList.setupMovies
import org.junit.runner.RunWith
import junit.framework.TestCase
import androidx.test.rule.ActivityTestRule
import org.junit.Before
import kotlin.Throws
import androidx.test.espresso.Espresso
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.assertion.ViewAssertions
import androidx.leanback.widget.ListRow
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.runner.AndroidJUnitRunner
import org.hamcrest.Matcher
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import java.lang.Exception

@RunWith(AndroidJUnit4ClassRunner::class)
class RowSupportFragmentTest : TestCase() {
    private var rowPosition = 0
    private var columnPosition = 0

    @get:Rule
    public val activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)
    private var movieList: List<Movie>? = null
    private var mainFragment: MainFragment? = null

    @Before
    @Throws(Exception::class)
    public override fun setUp() {
        super.setUp()
        movieList = setupMovies()
    }

    private fun setupMainFragment(): MainFragment {
        val fragmentManager = activityRule.activity.supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val fragment = MainFragment()
        transaction.add(R.id.rows_frame, fragment)
        transaction.commit()
        mainFragment = fragment
        return fragment
    }

    @Test
    fun test_movieDataLoaded() {
        assertEquals(movieList!!.size, 5)
    }

    @Test
    fun test_rowSupportFragmentDisplayed() {
        activityRule.activity.runOnUiThread { setupMainFragment() }
        Espresso.onView(ViewMatchers.isRoot()).perform(setDelay(200))
        Espresso.onView(ViewMatchers.withId(R.id.rows_frame)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun test_firstRowAdded() {
        rowPosition = 0
        columnPosition = 1
        activityRule.activity.runOnUiThread { setupMainFragment() }
        Espresso.onView(ViewMatchers.isRoot()).perform(setDelay(500))
        val listRow = mainFragment!!.getRowViewHolder(rowPosition).rowObject as ListRow
        if (listRow.adapter.getPresenter(movieList!![rowPosition]) is CardPresenterPortrait) {
            val firstRowDataFromAdapter = listRow.adapter[columnPosition] as Movie
            Assert.assertEquals(firstRowDataFromAdapter.title, movieList!![columnPosition].title)
        } else {
            fail()
        }
    }

    @Test
    fun test_secondRowAdded() {
        rowPosition = 1
        columnPosition = 4
        activityRule.activity.runOnUiThread { setupMainFragment() }
        Espresso.onView(ViewMatchers.isRoot()).perform(setDelay(500))
        val listRow = mainFragment!!.getRowViewHolder(rowPosition).rowObject as ListRow
        if (listRow.adapter.getPresenter(movieList!![rowPosition]) is CardPresenterLandscape) {
            val firstRowDataFromAdapter = listRow.adapter[columnPosition] as Movie
            Assert.assertEquals(firstRowDataFromAdapter.title, movieList!![columnPosition].title)
        } else {
            fail()
        }
    }

    @Test
    fun test_thirdRowAdded() {
        rowPosition = 2
        columnPosition = 4
        activityRule.activity.runOnUiThread { setupMainFragment() }
        Espresso.onView(ViewMatchers.isRoot()).perform(setDelay(500))
        val listRow = mainFragment!!.getRowViewHolder(rowPosition).rowObject as ListRow
        if (listRow.adapter.getPresenter(movieList!![rowPosition]) is BigCardPresenterLand) {
            val firstRowDataFromAdapter = listRow.adapter[columnPosition] as Movie
            Assert.assertEquals(firstRowDataFromAdapter.title, movieList!![columnPosition].title)
        } else {
            fail()
        }
    }

    private fun setDelay(delay: Int): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return ViewMatchers.isAssignableFrom(View::class.java)
            }

            override fun getDescription(): String {
                return "set delay"
            }

            override fun perform(uiController: UiController, view: View) {
                uiController.loopMainThreadForAtLeast(delay.toLong())
            }
        }
    }
}