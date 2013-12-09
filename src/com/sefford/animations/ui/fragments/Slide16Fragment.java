package com.sefford.animations.ui.fragments;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sefford.animations.R;

/**
 * Created with IntelliJ IDEA.
 * User: sefford
 * Date: 8/12/13
 * Time: 23:35
 * To change this template use File | Settings | File Templates.
 */
public class Slide16Fragment extends BaseFragment {
    private static final long ANIMATION_DURATION = 240;
    private LinearLayout llDemo;
    private View ivPlay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.screen_slide16, container, false);
    }

    @Override
    public void goToNextFragment() {
        getMainActivity().addFragment(new Slide17Fragment(), "Slide17", R.animator.fragment_slide_in_left, 0);
    }

    @Override
    public String getFragmentTag() {
        return "Slide16";  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void mapGUI() {
        llDemo = findView(R.id.ll_demo);
        ivPlay = findView(R.id.iv_play);
    }

    @Override
    protected void hookUpActionListeners() {

    }

    @Override
    public void doNextStep() {
        switch (steps) {
            case 0:
                animatePlay();
            case 1:
            case 2:
            case 3:
                LayoutInflater inflater = LayoutInflater.from(getActivity());
                TextView newView = (TextView) inflater.inflate(R.layout.listitem_googleplus, null);
                newView.setText("Element " + steps);
                newView.setTextSize(16);
                llDemo.addView(newView, 0);
                break;
            default:
                goToNextFragment();
                break;
        }
        steps++;
    }

    private void animatePlay() {
        Animator alpha = ObjectAnimator.ofFloat(ivPlay, View.ALPHA, 1f, 0f);
        alpha.setDuration(ANIMATION_DURATION);

        Animator scaleX = ObjectAnimator.ofFloat(ivPlay, View.SCALE_X, 1, 1.3f);
        scaleX.setInterpolator(new AccelerateInterpolator());
        scaleX.setDuration(ANIMATION_DURATION);

        Animator scaleY = ObjectAnimator.ofFloat(ivPlay, View.SCALE_Y, 1, 1.3f);
        scaleY.setInterpolator(new AccelerateInterpolator());
        scaleY.setDuration(ANIMATION_DURATION);

        AnimatorSet animation = new AnimatorSet();
        animation.playTogether(alpha, scaleX, scaleY);
        animation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                ivPlay.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animation.start();
    }
}
