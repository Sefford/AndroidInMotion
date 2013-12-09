package com.sefford.animations.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.sefford.animations.R;

/**
 * Created with IntelliJ IDEA.
 * User: sefford
 * Date: 8/12/13
 * Time: 20:29
 * To change this template use File | Settings | File Templates.
 */
public class GooglePlusAdapter extends BaseAdapter {
    private final LayoutInflater inflater;

    public GooglePlusAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return 1000;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listitem_googleplus, parent, false);
        }
        ((TextView) convertView).setText("Slide " + position);
        return convertView;
    }
}
