package com.remytabardel.henripotier;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

/**
 * @author Remy Tabardel
 *         provide MyMockApplication instead of MyApplication for custom component
 */

public class MockTestRunner extends AndroidJUnitRunner {
    @Override
    public Application newApplication(ClassLoader cl, String className, Context context)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return super.newApplication(cl, MyMockApplication.class.getName(), context);
    }
}
