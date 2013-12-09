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
 * Time: 0:11
 * To change this template use File | Settings | File Templates.
 */
public class Slide19Fragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.screen_slide19, container, false);
    }

    @Override
    public void goToNextFragment() {
        getMainActivity().addFragment(new Slide20Fragment(), "Slide20", 0, 0);
    }

    @Override
    public String getFragmentTag() {
        return "Slide19";  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void mapGUI() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void hookUpActionListeners() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void doNextStep() {
        goToNextFragment();
    }
}
