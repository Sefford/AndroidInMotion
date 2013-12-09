package com.sefford.animations.ui.fragments;

import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import com.sefford.animations.R;

/**
 * Created with IntelliJ IDEA.
 * User: sefford
 * Date: 8/12/13
 * Time: 22:36
 * To change this template use File | Settings | File Templates.
 */
public class Slide13Fragment extends BaseFragment {

    public static final int MARGINS = 64;
    private Canvas canvas;
    private Bitmap bitmap;
    private ImageView ivBlackBoard;
    private Point size;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.screen_slide13, container, false);
    }

    @Override
    public void goToNextFragment() {
        getMainActivity().addFragment(new Slide14Fragment(), "Slide14", 0, 0);
    }

    @Override
    public String getFragmentTag() {
        return "Slide13";
    }

    @Override
    protected void mapGUI() {
        ivBlackBoard = findView(R.id.iv_blackboard);
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        bitmap = Bitmap.createBitmap(1280, 768, Bitmap.Config.ARGB_8888);
        ivBlackBoard.setImageBitmap(bitmap);
        canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(android.R.color.white));
        paint.setStrokeWidth(2);
        canvas.drawLine(2 * MARGINS, 2 * MARGINS, 2 * MARGINS, size.y - MARGINS, paint);
        canvas.drawLine(MARGINS, size.y - 2 * MARGINS, size.x - MARGINS, size.y - 2 * MARGINS, paint);

    }

    @Override
    protected void hookUpActionListeners() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void doNextStep() {
        final Paint paint = new Paint();
        paint.setStrokeWidth(8);
        final ValueAnimator animator = ValueAnimator.ofFloat(140, size.y - 140);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                drawPoint(animation.getCurrentPlayTime() + 140, (Float) animation.getAnimatedValue(), paint);
            }
        });
        animator.setDuration(1000);
        switch (steps) {
            case 0:
                paint.setColor(getResources().getColor(R.color.red_kk));
                animator.setInterpolator(new Interpolator() {
                    @Override
                    public float getInterpolation(float input) {
                        return input;
                    }
                });
                animator.start();
                break;
            case 1:
                paint.setColor(getResources().getColor(android.R.color.holo_orange_dark));
                animator.setInterpolator(new AccelerateInterpolator());
                animator.start();
                break;
            case 2:
                paint.setColor(getResources().getColor(R.color.blue_ics));
                animator.setInterpolator(new DecelerateInterpolator());
                animator.start();
                break;
            case 3:
                paint.setColor(getResources().getColor(R.color.green_gingerbread));
                animator.setInterpolator(new BounceInterpolator());
                animator.start();
                break;
            default:
                goToNextFragment();
                break;
        }
        steps++;
    }

    protected void drawPoint(float x, float y, Paint paint) {
        canvas.drawPoint(x, y, paint);
        ivBlackBoard.invalidate();
    }
}
