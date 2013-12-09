package com.sefford.animations.ui.fragments;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import com.sefford.animations.R;

/**
 * Created with IntelliJ IDEA.
 * User: sefford
 * Date: 9/12/13
 * Time: 0:50
 * To change this template use File | Settings | File Templates.
 */
public class Slide21Fragment extends BaseFragment {
    private static final long ANIMATION_DURATION = 300;
    private ImageView ivParallax;
    private Bitmap bitmap;
    private Point size = new Point();
    private ValueAnimator animator;
    private View ivPlay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.screen_slide21, container, false);
    }

    @Override
    public void goToNextFragment() {
        getMainActivity().addFragment(new Slide22Fragment(), "Slide22", 0, 0);
    }

    @Override
    public String getFragmentTag() {
        return "Slide21";  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void mapGUI() {
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        display.getSize(size);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.slide21_image);
        ivParallax = findView(R.id.iv_parallax_demo);
        ivPlay = findView(R.id.iv_play);
        cropBitmap(0, 0);
    }

    @Override
    protected void hookUpActionListeners() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void doNextStep() {
        switch (steps) {
            case 0:
                animatePlay();
                animator = ValueAnimator.ofInt(0, bitmap.getWidth());
                animator.setDuration(100000);
                animator.setInterpolator(new LinearInterpolator());
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        cropBitmap((Integer) animation.getAnimatedValue(), 0);
                    }
                });
                animator.start();
                break;
            default:
                animator.cancel();
                goToNextFragment();
                break;
        }
        steps++;
    }

    public void cropBitmap(int x, int y) {
        Log.d("CROP", "X:" + x + "+" + size.x + "<" + bitmap.getWidth());
        if (x + size.x < bitmap.getWidth()) {
            ivParallax.setImageBitmap(Bitmap.createBitmap(bitmap, x, y, size.x, size.y));
        } else {
            animator.cancel();
        }
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
