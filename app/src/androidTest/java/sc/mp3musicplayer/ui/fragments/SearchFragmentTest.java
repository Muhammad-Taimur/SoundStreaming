package sc.mp3musicplayer.ui.fragments;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import sc.mp3musicplayer.R;
import sc.mp3musicplayer.ui.activities.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Created by regulosarmiento on 21/06/16.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class SearchFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);


    @Test
    public void testSearch() throws Exception {
        // Types the keyword "Adele" into the EditText
        onView(withId(R.id.search_et)).perform(typeText("Adele"));

        // Clicks on the search icon to start searching
        onView(withId(R.id.ic_search)).perform(click());

        // Checks if the listView is populated.
        onView(withId(R.id.search_results)).check(matches(isDisplayed()));
    }

    @Test
    public void testOnSearchLoadedSuccess() throws Exception {

    }

    @Test
    public void testOnSearchLoadedFailure() throws Exception {
        // checks if these views are visible when this method is triggered.
        onView(withId(R.id.no_search_results))
                .check(matches(isEnabled()));

        onView(withId(R.id.no_search_results_title))
                .check(matches(isEnabled()));

        onView(withId(R.id.no_search_results_description))
                .check(matches(isEnabled()));
    }
}