package com.mogujie.natasha;

import android.app.Activity;
import android.content.Intent;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.robolectric.Robolectric;

/**
 * Created by xiaochuang on 4/22/16.
 */
public class ActivityRule<T extends Activity> implements TestRule {
    private final Intent intent;
    private final Class<T> clazz;
    private final boolean needStartActivity;
    private T mActivity;

    public ActivityRule(Class<T> clazz, Intent intent, boolean needStartActivity) {
        this.intent = intent;
        this.clazz = clazz;
        this.needStartActivity = needStartActivity;
    }

    public T getActivity() {
        return mActivity;
    }

    @Override
    public Statement apply(Statement base, Description description) {
        return new ActivityStatement(base);
    }

    private T setupActivity() {
        return Robolectric.buildActivity(clazz).withIntent(intent).create().get();
    }

    private void finishActivity() {
        if (mActivity != null) mActivity.finish();
    }

    private class ActivityStatement extends Statement {
        private final Statement mBase;

        public ActivityStatement(Statement base) {
            mBase = base;
        }

        @Override
        public void evaluate() throws Throwable {
            try {
                if (needStartActivity) mActivity = setupActivity();
                mBase.evaluate();
            } finally {
                finishActivity();
            }
        }
    }



}
