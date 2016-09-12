package com.mogujie.natasha;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

/**
 * Created by xiaochuang on 4/22/16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MainActivityTest extends ActivityTestBase<MainActivity> {

    @Test
    @JSpec(desc = "should activity not null")
    public void testactivitynotnull() {
        ann(getActivity());
    }

    @Override
    protected Class<MainActivity> activityClass() {
        return MainActivity.class;
    }
}
