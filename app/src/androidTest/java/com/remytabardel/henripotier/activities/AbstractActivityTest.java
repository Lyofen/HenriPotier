package com.remytabardel.henripotier.activities;

import android.app.Activity;
import android.content.Context;
import android.support.test.rule.ActivityTestRule;

import com.remytabardel.henripotier.utils.LogUtils;

import org.junit.Rule;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

/**
 * provide common methods to test activities
 *
 * @param <A> indicate activity to test
 * @author Remy Tabardel
 */
public class AbstractActivityTest<A extends Activity> {
    @Rule public ActivityTestRule<A> mActivityRule;
    private Context mAppContext;

    public AbstractActivityTest(Class<A> activityClass) {
        mActivityRule = new ActivityTestRule<>(activityClass);
        mAppContext = getInstrumentation().getTargetContext();
    }

    public A getActivity() {
        return mActivityRule.getActivity();
    }

    public ActivityTestRule<A> getActivityTestRule() {
        return mActivityRule;
    }

    public String getString(int resId) {
        return mAppContext.getString(resId);
    }

    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            LogUtils.e("error during thread sleep", e);
        }
    }
}
