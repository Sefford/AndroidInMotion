package com.sefford.animations.ui.fragments;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import com.sefford.animations.R;
import com.sefford.animations.ui.adapters.GalleryAdapter;
import com.sefford.animations.ui.transformations.BlurTransformation;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * Created with IntelliJ IDEA.
 * User: sefford
 * Date: 8/12/13
 * Time: 13:22
 * To change this template use File | Settings | File Templates.
 */
public class Slide8Fragment extends BaseFragment {

    public static final int ANIMATION_DURATION = 200;
    private GalleryAdapter adapter;
    private ImageView ivCover;
    private ViewPager vpGallery;
    private Picasso picasso;
    private View ivPlay;
    private Target loadCoverTarget = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            Drawable[] drawables = new Drawable[2];
            final TransitionDrawable oldDrawable;
            if (ivCover.getDrawable() instanceof TransitionDrawable) {
                drawables[0] = ivCover.getDrawable();
            } else {
                drawables[0] = new ColorDrawable(getResources().getColor(R.color.black_card_text));
            }
            drawables[1] = new BitmapDrawable(getResources(), bitmap);
            TransitionDrawable drawable = new TransitionDrawable(drawables);
            drawable.setCrossFadeEnabled(true);
            ivCover.setImageDrawable(drawable);
            drawable.startTransition(600);

        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {
            //To change body of implemented methods use File | Settings | File Templates.
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.screen_slide8, container, false);
    }

    @Override
    public void goToNextFragment() {
        getMainActivity().addFragment(new Slide9Fragment(), "Slide9", 0, 0);
    }

    @Override
    public String getFragmentTag() {
        return "Slide9";
    }

    @Override
    protected void mapGUI() {
        ivCover = findView(R.id.iv_cover);
        ivPlay = findView(R.id.iv_play);
        picasso = Picasso.with(getActivity());
        adapter = new GalleryAdapter(getActivity());

        vpGallery = findView(R.id.vp_gallery);
        vpGallery.setPageMargin(24);
        vpGallery.setOffscreenPageLimit(3);
        vpGallery.setAdapter(adapter);
    }

    @Override
    protected void hookUpActionListeners() {
        vpGallery.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int position) {
                final int resource = adapter.getItem(position);
                picasso.load(resource)
                        .transform(new BlurTransformation(getActivity()))
                        .into(loadCoverTarget);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void doNextStep() {
        switch (steps) {
            case 0:
                animatePlay();
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
