package com.mogujie.natasha;

import android.app.Application;

import com.mogujie.lib.BuildConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

/**
 * Created by xiaochuang on 1/7/16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class RobolectricTestBase extends TestBase {

    public RobolectricTestBase() {
        ShadowLog.stream = System.out;
    }

    /**
     * The context in testing environment
     * @return
     */
    public Application getContext() {
        return RuntimeEnvironment.application;
    }

    /**
     * Just to get rid of the "No test method found" error
     */
    @Test
    public void testDumb() {
        at(true);
    }

}
