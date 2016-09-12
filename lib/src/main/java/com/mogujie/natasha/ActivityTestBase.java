package com.mogujie.natasha;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.Robolectric;
import org.robolectric.Shadows;
import org.robolectric.shadows.ShadowToast;
import org.robolectric.util.ReflectionHelpers;

/**
 * Created by xiaochuang on 1/7/16.
 */
public abstract class ActivityTestBase<T extends Activity> extends ViewTestBase {

    protected T mActivity;

    @Override
    protected View createView() {
        return Shadows.shadowOf(getActivity()).getContentView();
    }

    /**
     * Will start the activity under testing
     */
    @Before
    public void setup() {
        mActivity = setupActivity(activityClass(), activityIntent());
        super.setup();
    }

    @Test
    public void testDumb() {
        at(true);
    }

    /**
     * Intent to build this activity, used in Robolectric.setupActivity()
     * Override this method if you want to build the target activity with a specific intent
     * @return
     */
    public Intent activityIntent() {
        return null;
    }

    /**
     * Get the activity under testing
     * @return the activity under testing
     */
    public T getActivity() {
        return mActivity;
//        return activityRule.getActivity();
    }

    public T setupActivity(Class<T> clazz, Intent intent) {
        return Robolectric.buildActivity(clazz).withIntent(intent).create().get();
    }

    public T setupActivity() {
        return setupActivity(activityClass(), activityIntent());
    }

    /**
     * Override this method and return the class of the target activity (the activity under testing)
     *
     * @return the class of the target activity
     */
    protected abstract Class<T> activityClass();

    /**
     * Assert that a toast was shown
     *
     * @param text the text of the toast
     */
    protected void assertToast(String text) {
        ae(text, ShadowToast.getTextOfLatestToast());
    }

    /**
     * Assert the view with the given id was enabled
     *
     * @param viewId the view id that should be enabled
     */
    protected void assertEnabled(int viewId) {
        at(view(viewId).isEnabled());
    }

    /**
     * Return the view with the given id, same as calling findViewById(viewId) on the target activity
     *
     * @param viewId the id of the view to return
     * @return a View with the given Id
     */
    protected View view(int viewId) {
        return getActivity().findViewById(viewId);
    }

    /**
     * Assert that the view with the given id has the given text
     *
     * @param viewId
     * @param text
     */
    protected void assertViewHasText(int viewId, String text) {
        String viewText = textOfView(viewId);
        at("Expect: " + viewText + " to contain: " + text, viewText.contains(text));
    }


    /**
     * Get the text of the view with the given id
     *
     * @param viewId
     * @return
     */
    @NonNull
    private String textOfView(int viewId) {
        return tv(viewId).getText().toString();
    }

    /**
     * Return the TextView with the given id, same as calling (TextView)findViewById(viewId) on the target activity
     *
     * @param viewId
     * @return A TextView with the given id
     */
    private TextView tv(int viewId) {
        return (TextView) view(viewId);
    }

    public void assertActivityHasField(String fieldName) {
        ann(ReflectionHelpers.getField(getActivity(), fieldName));
    }

    protected void assertViewVisible(View view) {
        ae(View.VISIBLE, view.getVisibility());
    }

    protected void assertViewVisible(int viewId) {
        assertViewVisible(view(viewId));
    }

    protected void assertViewHasText(int viewId, int textRes) {
        assertViewHasText(viewId, getString(textRes));
    }

    private String getString(int textRes) {
        return getActivity().getString(textRes);
    }

    protected void assertViewText(int viewId, String text) {
        ae(text, textOfView(viewId));
    }

    protected void assertViewText(int viewId, int textRes) {
        assertViewText(viewId, getString(textRes));
    }


    protected void assertViewExists(int viewId) {
        ann(view(viewId));
    }

    protected void click(int viewId) {
        view(viewId).performClick();
    }

    protected void assertViewGone(View view) {
        ae(View.GONE, view.getVisibility());
    }

    public void assertViewGone(int viewId) {
        assertViewGone(view(viewId));
    }

    protected LinearLayout ll(int llId) {
        return (LinearLayout) view(llId);
    }

    protected void assertNextActivity(Class<? extends Activity> clazz) {
        Intent intent = Shadows.shadowOf(getActivity()).getNextStartedActivity();
        ae(new Intent(getActivity(), clazz), intent);
    }
}
