package com.sefford.animations.ui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import com.sefford.animations.ui.activities.MainActivity;

/**
 * Base Fragment which provides services for the rest of the
 * activities in the application
 *
 * @author Saul Diaz<saul@feverup.com>
 */
public abstract class BaseFragment extends Fragment implements Handler.Callback {

    protected Handler handler = new Handler(this);
    protected int steps = 0;

    @Override
    public void onViewCreated(android.view.View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapGUI();
        hookUpActionListeners();
    }

    /**
     * Gets a FeverBaseActivity for those times we will need some kind
     * of specific behavior from our parent Activity
     *
     * @return The referenced instance of the FeverBaseActivity
     */
    public MainActivity getMainActivity() {
        return (MainActivity) getActivity();
    }

    /**
     * Helper method to avoid unnecesary boilerplate with constant castings.
     * <p/>
     * This variation is intended to be used with ViewGroups which are not
     * part of the ContentView of the Activity (i.e List Headers, Empty Views...)
     *
     * @param root Root view to find the view from
     * @param id   Resource of the view to extract
     * @param <T>  Type view to extract
     * @return Extracted view, or NULL if there is no such view
     */
    public <T extends View> T findView(View root, int id) {
        return (T) root.findViewById(id);
    }

    /**
     * Helper method to avoid unnecesary boilerplate with constant castings.
     * <p/>
     * This variation is intended to be used with Views directly attached to the
     * activity via setContentView()
     *
     * @param id  Resource of the view to extract
     * @param <T> Type view to extract
     * @return Extracted view, or NULL if there is no such view
     */
    public <T extends View> T findView(int id) {
        return (T) findView(getView(), id);
    }

    public abstract void goToNextFragment();

    /**
     * Returns the fragment identifier
     *
     * @return String with the fragment tag identifier
     */
    public abstract String getFragmentTag();

    /**
     * Maps the views with the Fragment.
     */
    protected abstract void mapGUI();

    /**
     * Hooks the Action Listeners to the views
     */
    protected abstract void hookUpActionListeners();

    public abstract void doNextStep();

    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }
}