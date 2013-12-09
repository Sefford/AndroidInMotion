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
 * Time: 20:06
 * To change this template use File | Settings | File Templates.
 */
public class Slide10Fragment extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.screen_slide10, container, false);
    }

    @Override
    public void goToNextFragment() {
        getMainActivity().addFragment(new Slide11Fragment(), "Slide11", 0, 0);
    }

    @Override
    public String getFragmentTag() {
        return "Slide10";
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
