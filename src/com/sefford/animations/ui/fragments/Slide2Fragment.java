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
 * Time: 18:45
 * To change this template use File | Settings | File Templates.
 */
public class Slide2Fragment extends BaseFragment {

    private View ivIndraLogo;
    private View ivTuentiLogo;
    private View ivFeverLogo;
    private View ivASLogo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.screen_slide2, container, false);
    }

    @Override
    public void goToNextFragment() {
        getMainActivity().addFragment(new Slide3Fragment(), "Slide3", 0, 0);
    }

    @Override
    public String getFragmentTag() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void mapGUI() {
        ivIndraLogo = findView(R.id.iv_indra_logo);
        ivTuentiLogo = findView(R.id.iv_tuenti_logo);
        ivFeverLogo = findView(R.id.iv_fever_logo);
        ivASLogo = findView(R.id.iv_as_logo);
    }

    @Override
    protected void hookUpActionListeners() {

    }

    @Override
    public void doNextStep() {
        switch (steps) {
            case 0:
                ivIndraLogo.animate()
                        .alpha(1f)
                        .setDuration(240)
                        .start();
                break;
            case 1:
                ivTuentiLogo.animate()
                        .alpha(1f)
                        .setDuration(240)
                        .start();
                break;
            case 2:
                ivFeverLogo.animate()
                        .alpha(1f)
                        .setDuration(240)
                        .start();
                break;
            case 3:
                ivASLogo.animate()
                        .alpha(1f)
                        .setDuration(240)
                        .start();
                break;
            default:
                goToNextFragment();
                break;
        }
        steps++;
    }

    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }
}
