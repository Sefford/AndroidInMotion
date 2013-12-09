package com.sefford.animations.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sefford.animations.R;

/**
 * Created with IntelliJ IDEA.
 * User: sefford
 * Date: 8/12/13
 * Time: 12:12
 * To change this template use File | Settings | File Templates.
 */
public class Slide6Fragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.screen_slide6, container, false);
    }

    @Override
    public void goToNextFragment() {
        getMainActivity().addFragment(new Slide7Fragment(), "Slide7", 0, 0);
    }

    @Override
    public String getFragmentTag() {
        return null;
    }

    @Override
    protected void mapGUI() {

    }

    @Override
    protected void hookUpActionListeners() {

    }

    @Override
    public void doNextStep() {
        goToNextFragment();
    }
}
