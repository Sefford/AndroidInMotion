package com.sefford.animations.ui.fragments;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ListView;
import com.sefford.animations.R;
import com.sefford.animations.ui.adapters.GooglePlusAdapter;

/**
 * Created with IntelliJ IDEA.
 * User: sefford
 * Date: 8/12/13
 * Time: 20:24
 * To change this template use File | Settings | File Templates.
 */
public class Slide11Fragment extends BaseFragment {

    private static final long ANIMATION_DURATION = 200;
    private GooglePlusAdapter adapter;
    private ListView lvGooglePlus;
    private LayoutAnimationController translationAnimation;
    private View ivPlay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.screen_slide11, container, false);
    }

    @Override
    public void goToNextFragment() {
        getMainActivity().addFragment(new Slide12Fragment(), "Slide12", 0, 0);
    }

    @Override
    public String getFragmentTag() {
        return "Slide11";
    }

    @Override
    protected void mapGUI() {
        ivPlay = findView(R.id.iv_play);
        translationAnimation = AnimationUtils.loadLayoutAnimation(getActivity(), R.anim.slide11_layoutanimation);
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        adapter = new GooglePlusAdapter(inflater);
        lvGooglePlus = findView(R.id.lv_demo);
        lvGooglePlus.setLayoutAnimation(translationAnimation);
    }

    @Override
    protected void hookUpActionListeners() {


    }

    @Override
    public void doNextStep() {
        switch (steps) {
            case 0:
                animatePlay();
                lvGooglePlus.setAdapter(adapter);
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
