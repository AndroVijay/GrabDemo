package com.example.rationalheads.vijay.grabdemo.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.rationalheads.vijay.grabdemo.R;

import java.util.ArrayList;

/**
 * Created by vijay on 17-04-2018.
 */

public class ImageFlipperAdapter  extends PagerAdapter{


    private ArrayList<Integer> IMAGES;
    private LayoutInflater inflater;
    private Context context;

    public ImageFlipperAdapter(ArrayList<Integer> IMAGES, Context context) {
        this.IMAGES = IMAGES;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
    @Override
    public int getCount() {
        return IMAGES.size();
    }
    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.slidingimages_layout, view, false);

        assert imageLayout != null;
        final ImageView imageView =  imageLayout.findViewById(R.id.image);


        imageView.setImageResource(IMAGES.get(position));

        view.addView(imageLayout, 0);

        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}
