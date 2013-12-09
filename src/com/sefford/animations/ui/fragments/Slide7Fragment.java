package com.sefford.animations.ui.fragments;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.sefford.animations.R;

/**
 * Created with IntelliJ IDEA.
 * User: sefford
 * Date: 8/12/13
 * Time: 12:57
 * To change this template use File | Settings | File Templates.
 */
public class Slide7Fragment extends BaseFragment {

    private ImageView ivDemoDrawable;
    private TransitionDrawable transition;
    private AnimationDrawable animation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.screen_slide7, container, false);
    }

    @Override
    public void goToNextFragment() {
         getMainActivity().addFragment(new Slide8Fragment(), "Slide1", 0, 0);
    }

    @Override
    public String getFragmentTag() {
        return "Slide7";
    }

    @Override
    protected void mapGUI() {
        ivDemoDrawable = findView(R.id.iv_drawable_demos);
        transition = (TransitionDrawable) ivDemoDrawable.getDrawable();
        animation = (AnimationDrawable) getResources().getDrawable(R.drawable.wifi_animation);
    }

    @Override
    protected void hookUpActionListeners() {

    }

    @Override
    public void doNextStep() {
        switch (steps) {
            case 0:
                transition.startTransition(800);
                break;
            case 1:
                transition.reverseTransition(800);
                break;
            case 2:
                ivDemoDrawable.setImageDrawable(animation);
                break;
            case 3:
                animation.start();
                break;
            default:
                goToNextFragment();
                break;
        }
        steps++;
    }
}
