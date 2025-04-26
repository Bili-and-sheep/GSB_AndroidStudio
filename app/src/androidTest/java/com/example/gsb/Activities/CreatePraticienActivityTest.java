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
public class CreatePraticienActivityTest {

    @Rule
    public ActivityScenarioRule<CreatePraticienActivity> activityRule =
            new ActivityScenarioRule<>(CreatePraticienActivity.class);

    @Test
    public void testCreatePraticienActivityDisplayed() {
        onView(withId(R.id.edit_nom)).check(matches(isDisplayed()));
        onView(withId(R.id.edit_prenom)).check(matches(isDisplayed()));
        onView(withId(R.id.edit_tel)).check(matches(isDisplayed()));
        onView(withId(R.id.edit_email)).check(matches(isDisplayed()));
        onView(withId(R.id.edit_rue)).check(matches(isDisplayed()));
        onView(withId(R.id.edit_code_postal)).check(matches(isDisplayed()));
        onView(withId(R.id.edit_ville)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_valider)).check(matches(isDisplayed()));
    }
}
