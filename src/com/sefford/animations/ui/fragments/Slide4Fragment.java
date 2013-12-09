package com.sefford.animations.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sefford.animations.R;

/**
 * Created with IntelliJ IDEA.
 * User: sefford
 * Date: 7/12/13
 * Time: 20:17
 * To change this template use File | Settings | File Templates.
 */
public class Slide4Fragment extends BaseFragment {

    private View ivProtip;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.screen_slide4, container, false);
    }

    @Override
    public void goToNextFragment() {
        getMainActivity().addFragment(new Slide5Fragment(), "Slide5", 0, 0);
    }

    @Override
    public String getFragmentTag() {
        return "Slide4";  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void mapGUI() {
        ivProtip = findView(R.id.iv_protip);
    }

    @Override
    protected void hookUpActionListeners() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void doNextStep() {
        switch (steps) {
            case 0:
                ivProtip.setAlpha(1f);
                break;
            default:
                goToNextFragment();
                break;
        }
        steps++;
    }
}
