package com.sefford.animations.ui.fragments;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import com.sefford.animations.R;

/**
 * Created with IntelliJ IDEA.
 * User: sefford
 * Date: 7/12/13
 * Time: 19:43
 * To change this template use File | Settings | File Templates.
 */
public class Slide3Fragment extends BaseFragment {
    public static final int ANIMATION_DURATION = 1000;
    private View ivEye;
    private View ivBrain;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.screen_slide3, container, false);
    }

    @Override
    public void goToNextFragment() {
        getMainActivity().addFragment(new Slide4Fragment(), "Slide4", 0, 0);
    }

    @Override
    public String getFragmentTag() {
        return "Slide3";  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void mapGUI() {
        ivEye = findView(R.id.iv_eye);
        ivBrain = findView(R.id.iv_brain);
    }

    @Override
    protected void hookUpActionListeners() {

    }

    @Override
    public void doNextStep() {
        switch (steps) {
            case 0:
                Display display = getActivity().getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                float width = size.x;
                float height = ((float) size.y / 3) * 2;
                AnimatorSet set = new AnimatorSet();
                AnimatorSet eyeAnimation = prepareTranslationAnimation(ivEye, -(width / 6), height - ivEye.getHeight() / 2);
                AnimatorSet brainAnimation = prepareTranslationAnimation(ivBrain, (width / 3), height - ivBrain.getHeight() / 2);
                set.playTogether(eyeAnimation, brainAnimation);
                set.start();
                break;
            default:
                goToNextFragment();
                break;
        }
        steps++;
    }

    public AnimatorSet prepareTranslationAnimation(View target, float positionX, float positionY) {
        Animator animationX = ObjectAnimator.ofFloat(target, View.X, target.getX(), positionX);
        animationX.setInterpolator(new AccelerateDecelerateInterpolator());
        animationX.setDuration(ANIMATION_DURATION);

        Animator animationY = ObjectAnimator.ofFloat(target, View.Y, target.getY(), positionY);
        animationY.setInterpolator(new AccelerateDecelerateInterpolator());
        animationY.setDuration(ANIMATION_DURATION);

        Animator alpha = ObjectAnimator.ofFloat(target, View.ALPHA, target.getAlpha(), 1f);
        alpha.setInterpolator(new AccelerateDecelerateInterpolator());
        alpha.setDuration(ANIMATION_DURATION);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(animationX, animationY, alpha);
        return set;
    }
}
