package com.example.gsb.Activities;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import com.example.gsb.R;

@RunWith(AndroidJUnit4.class)
public class CreateVisiteActivityTest {

    @Rule
    public ActivityScenarioRule<CreateVisiteActivity> activityRule =
            new ActivityScenarioRule<>(CreateVisiteActivity.class);

    @Test
    public void testCreateVisiteActivityDisplayed() {
        onView(withId(R.id.edit_date)).check(matches(isDisplayed()));
    }
}