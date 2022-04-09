package com.example.uscrecapp_team28;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

import android.app.Activity;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import androidx.test.runner.lifecycle.Stage;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Collection;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class BookingInformationActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<>(MainActivity.class);
    public Activity getCurrentActivity() {
        final Activity[] currentActivity = new Activity[1];
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                Collection<Activity> allActivities = ActivityLifecycleMonitorRegistry.getInstance()
                        .getActivitiesInStage(Stage.RESUMED);
                if (!allActivities.isEmpty()) {
                    currentActivity[0] = allActivities.iterator().next();
                }
            }
        });
        return currentActivity[0];
    }
    @Test
    public void DisplayReservation(){
        onView(withId(R.id.username)).perform(typeText("reservation_test"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("test"), ViewActions.closeSoftKeyboard());
//        Intents.init();
        onView(withId(R.id.signinbtn)).perform(click());
//        intended(hasComponent(MapActivity.class.getName()));
//        Intents.release();
//        Intents.init();
        onView(withId(R.id.summarybtn)).perform(click());
        ArrayList<BookingItem> historyList = ((PastBookingInformationAdapter)((BookingInformationActivity)getCurrentActivity()).getmHistoryAdapter()).getmBookingList();
        assertEquals(1,historyList.size());
        assertEquals("128",historyList.get(0).getmReservation_id());
//        intended(hasComponent(BookingInformationActivity.class.getName()));
        ArrayList<BookingItem> futureList = ((BookingInformationAdapter)((BookingInformationActivity)getCurrentActivity()).getmAdapter()).getmBookingList();
        assertEquals(2,futureList.size());
        int correct_count = 0;
        for(BookingItem b:futureList){
            if(b.getmReservation_id().equals("129")||b.getmReservation_id().equals("130")){
                correct_count++;
            }
        }
        assertEquals(2,correct_count);
        onView(withId(R.id.returnButton)).perform(click());
        onView(withId(R.id.profileButton)).perform(click());
        onView(withId(R.id.profile_logout)).perform(click());
    }
}