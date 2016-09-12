package com.mogujie.natasha;

import android.view.View;
import android.view.ViewGroup;

import org.junit.Before;

/**
 * Base class for testing views, provide many helper methods for velidation views
 * Created by xiaochuang on 3/3/16.
 * @param <T>
 */
public abstract class ViewTestBase<T extends View> extends RobolectricTestBase {

    protected T mTargetView;
    @Before
    public void setup() {
        mTargetView = createView();
    }

    /**
     * Create the target view under testing
     *
     * @return the view under testing
     */
    protected abstract T createView();

    /**
     * Get a child view of the target view
     *
     * @param id the id of the child view
     * @return
     */
    public View child(int id) {
        return mTargetView.findViewById(id);
    }

    /**
     * Check that given view has visibility of {@code View.VISIBLE}
     *
     * @param view the view to be checked
     */
    protected void assertViewVisible(View view) {
        ae(View.VISIBLE, view.getVisibility());
    }

    /**
     * Check that the given view has visibility of {@code View.GONE}
     *
     * @param view the child view to be checked
     */
    protected void assertViewGone(View view) {
        ae("Expect view to be gone, but was: " + getReadableVisibility(view.getVisibility()), View.GONE, view.getVisibility());
    }

    private String getReadableVisibility(int visibility) {
        switch (visibility) {
            case View.GONE:
                return "GONE";
            case View.VISIBLE:
                return "VISIBLE";
            case View.INVISIBLE:
                return "INVISIBLE";
            default:
                return "UNKNOWN";
        }
    }

    /**
     * Check that the child view with the given view id has visibility of {@code View.GONE}
     *
     * @param childViewId the id of the child view to be checked
     */
    public void assertChildGone(int childViewId) {
        assertViewGone(child(childViewId));
    }

    /**
     * Check that the child view with the given view id has visibility of {@code View.VISIBLE}
     *
     * @param childViewId the id of the child view to be checked
     */
    public void assertChildVisible(int childViewId) {
        assertViewVisible(child(childViewId));
    }

    private ViewGroup vg(int viewGroupId) {
        return (ViewGroup) child(viewGroupId);
    }

    public int getVisibleChildCount(int viewGroupId) {
        ViewGroup vg = vg(viewGroupId);
        return getVisibleChildCount(vg);
    }

    public int getVisibleChildCount() {
        return getVisibleChildCount(mTargetView);
    }

    protected int getVisibleChildCount(View vg) {
        int count = 0;
        for (int i = 0; i < ((ViewGroup) vg).getChildCount(); i++) {
            View v = ((ViewGroup) vg).getChildAt(i);
            if (v.getVisibility() == View.VISIBLE) count++;
        }

        return count;
    }

}
