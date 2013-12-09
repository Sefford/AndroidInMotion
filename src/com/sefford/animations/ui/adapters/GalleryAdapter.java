package com.sefford.animations.ui.adapters;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.sefford.animations.R;
import com.sefford.animations.ui.transformations.RoundedBordersTransformation;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sefford
 * Date: 29/11/13
 * Time: 13:18
 * To change this template use File | Settings | File Templates.
 */
public class GalleryAdapter extends PagerAdapter {

    private final List<Integer> data;
    private final Context context;
    private final Picasso picasso;
    private RoundedBordersTransformation transformation;

    public GalleryAdapter(Context context) {
        this.data = new ArrayList<Integer>();
        this.data.add(R.drawable.slide8_image1);
        this.data.add(R.drawable.slide8_image2);
        this.data.add(R.drawable.slide8_image3);
        this.context = context;
        this.picasso = Picasso.with(context);
        this.transformation = new RoundedBordersTransformation(context.getResources().getDimension(R.dimen.corner_radius));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final ImageView view = new ImageView(context);
        picasso.load(data.get(position))
                .resize(640, 384)
                .transform(transformation)
                .into(view);
        ((ViewPager) container).addView(view, 0);
        return view;
    }

    public Integer getItem(int position) {
        return data.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }
}