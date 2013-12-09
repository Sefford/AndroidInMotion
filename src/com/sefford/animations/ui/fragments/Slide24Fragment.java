package com.sefford.animations.ui.fragments;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.TextView;
import com.sefford.animations.R;

/**
 * Created with IntelliJ IDEA.
 * User: sefford
 * Date: 9/12/13
 * Time: 1:34
 * To change this template use File | Settings | File Templates.
 */
public class Slide24Fragment extends BaseFragment {
    private TextView tvRepoAddress;
    private TextView tvTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.screen_slide24, container, false);
    }

    @Override
    public void goToNextFragment() {
        getActivity().finish();
    }

    @Override
    public String getFragmentTag() {
        return "Slide24";
    }

    @Override
    protected void mapGUI() {
        tvTitle = findView(R.id.tv_title);
        tvRepoAddress = findView(R.id.tv_repo);
        tvRepoAddress.setText(Html.fromHtml("http://github.com/Sefford/AndroidInMotion"));
    }

    @Override
    protected void hookUpActionListeners() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void doNextStep() {
        switch (steps) {
            case 0:
                tvRepoAddress.animate()
                        .alpha(1)
                        .setInterpolator(new AccelerateInterpolator())
                        .setDuration(300).start();
                break;
            case 1:
                animate("Questions?");
                break;
            case 2:
                animate("Thank You!");
                break;
            default:
                goToNextFragment();
                break;
        }
        steps++;
    }

    public void animate(final String text) {
        Animator fadeOut = ObjectAnimator.ofFloat(tvTitle, View.ALPHA, 1, 0);
        fadeOut.setDuration(500);
        fadeOut.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                tvTitle.setText(text);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });

        Animator fadeIn = ObjectAnimator.ofFloat(tvTitle, View.ALPHA, 0, 1);
        fadeIn.setDuration(500);

        AnimatorSet set = new AnimatorSet();
        set.playSequentially(fadeOut, fadeIn);
        set.start();
    }
}
