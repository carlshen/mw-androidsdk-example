package com.magicwindow.deeplink.adapter;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.magicwindow.deeplink.activity.WebViewActivity;
import com.magicwindow.deeplink.app.MWApplication;
import com.magicwindow.deeplink.config.Config;
import com.zxinsight.MWImageView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.salesuite.saf.imagecache.ImageLoader;

/**
 * @author aaron
 * @date 16/2/26
 */
public class ImageAdapter extends PagerAdapter {
    List<Integer> list = new ArrayList<>();
    ImageLoader imageLoader;
//    Integer[] images;
    int mWPosition;

    public ImageAdapter(int mWPosition, List<Integer> res) {
        list = res;
//        imageLoader = MWApplication.getInstance().imageLoader;
        this.mWPosition = mWPosition;
    }

    /*public void addView(List<String> list){
        this.list = list;
        notifyDataSetChanged();
    }*/

    @Override
    public int getCount() {
        return list.size();
    }
    @Override
    public void destroyItem(ViewGroup view, int position, Object object) {
        view.removeView((ImageView)object);
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(final ViewGroup view, int position) {
        MWImageView imageView = new MWImageView(view.getContext());
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageResource(list.get(position));
        imageView.bindEventWithMLink(Config.MWS[mWPosition+position],new JSONObject());
        view.addView(imageView,ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        return imageView;
    }

}