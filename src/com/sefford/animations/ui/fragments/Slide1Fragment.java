package com.sefford.animations.ui.fragments;

import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sefford.animations.R;

/**
 * Created with IntelliJ IDEA.
 * User: sefford
 * Date: 7/12/13
 * Time: 18:41
 * To change this template use File | Settings | File Templates.
 */
public class Slide1Fragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.screen_slide1, container, false);
    }

    @Override
    public void goToNextFragment() {
        getMainActivity().addFragment(new Slide2Fragment(), "Slide2", 0,0);
    }

    @Override
    public String getFragmentTag() {
        return "Slide1";
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

    @Override
    public boolean handleMessage(Message msg) {
        return super.handleMessage(msg);
    }
}
