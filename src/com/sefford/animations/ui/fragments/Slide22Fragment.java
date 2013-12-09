package com.sefford.animations.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sefford.animations.R;

/**
 * Created with IntelliJ IDEA.
 * User: sefford
 * Date: 9/12/13
 * Time: 1:16
 * To change this template use File | Settings | File Templates.
 */
public class Slide22Fragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.screen_slide22, container, false);
    }

    @Override
    public void goToNextFragment() {
        getMainActivity().addFragment(new Slide23Fragment(), "Slide23", 0, 0);
    }

    @Override
    public String getFragmentTag() {
        return "Slide22";
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
