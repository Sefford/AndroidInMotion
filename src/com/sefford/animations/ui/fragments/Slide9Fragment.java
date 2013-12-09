package com.sefford.animations.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.sefford.animations.R;

/**
 * Created with IntelliJ IDEA.
 * User: sefford
 * Date: 8/12/13
 * Time: 19:23
 * To change this template use File | Settings | File Templates.
 */
public class Slide9Fragment extends BaseFragment {
    private View ivRotate;
    private View ivScale;
    private View ivAlpha;
    private View ivTranslateR;
    private View ivTranslateL;
    private Animation scaleAnimation;
    private Animation rotateAnimation;
    private Animation alphaAnimation;
    private Animation translateL;
    private Animation translateR;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.screen_slide9, container, false);
    }

    @Override
    public void goToNextFragment() {
        getMainActivity().addFragment(new Slide10Fragment(), "Slide10", 0, 0);
    }

    @Override
    public String getFragmentTag() {
        return "Slide10";
    }

    @Override
    protected void mapGUI() {
        ivRotate = findView(R.id.iv_rotate);
        ivScale = findView(R.id.iv_scale);
        ivAlpha = findView(R.id.iv_alpha);
        ivTranslateR = findView(R.id.iv_translate_r);
        ivTranslateL = findView(R.id.iv_translate_l);

        alphaAnimation = new AlphaAnimation(0f, 1f);
        alphaAnimation.setDuration(500);
        ivAlpha.setAnimation(alphaAnimation);
        translateL = AnimationUtils.loadAnimation(getActivity(), R.anim.slide9_translate_left);
        translateR = AnimationUtils.loadAnimation(getActivity(), R.anim.slide9_translate_right);
        ivTranslateR.setAnimation(translateR);
        ivTranslateL.setAnimation(translateL);
        scaleAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.slide9_scale_anim);
        rotateAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.slide9_rotation_anim);
    }

    @Override
    protected void hookUpActionListeners() {

    }

    @Override
    public void doNextStep() {
        switch (steps) {
            case 0:
                ivRotate.setVisibility(View.VISIBLE);
                ivRotate.startAnimation(rotateAnimation);
                break;
            case 1:
                ivScale.setVisibility(View.VISIBLE);
                ivScale.startAnimation(scaleAnimation);
                break;
            case 2:
                ivTranslateL.setVisibility(View.VISIBLE);
                ivTranslateR.setVisibility(View.VISIBLE);
                ivTranslateL.startAnimation(translateL);
                ivTranslateR.startAnimation(translateR);
                break;
            case 3:
                ivAlpha.setVisibility(View.VISIBLE);
                ivAlpha.startAnimation(alphaAnimation);
                break;
            default:
                goToNextFragment();
                break;
        }
        steps++;
    }
}
