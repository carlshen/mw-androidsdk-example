package com.magicwindow.deeplink.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.magicwindow.deeplink.R;
import com.magicwindow.deeplink.app.MWApplication;
import com.magicwindow.deeplink.config.Config;

import cn.magicwindow.ClickParamsBuilder;
import cn.magicwindow.MWImageView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.salesuite.saf.utils.Preconditions;

/**
 * @author aaron
 * @date 16/2/26
 */
public class ImageAdapter extends PagerAdapter {
    List<String> list = new ArrayList<>();
    //    Integer[] images;
    int mWPosition;

    int defaultRes = R.drawable.default_570_250;

    public ImageAdapter(int mWPosition, List res) {
        list = res;
        this.mWPosition = mWPosition;
    }

    public ImageAdapter(int mWPosition, List res, int defaultRes) {
        list = res;
        this.mWPosition = mWPosition;
        this.defaultRes = defaultRes;
    }

    public void addView(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list == null || list.isEmpty() ? 0 : list.size();
    }

    @Override
    public void destroyItem(ViewGroup view, int position, Object object) {
        view.removeView((ImageView) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(final ViewGroup view, int position) {
        MWImageView imageView = new MWImageView(view.getContext());
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageResource(defaultRes);

        if (Preconditions.isNotBlank(list) && Preconditions.isNotBlank(list.get(position))) {
            MWApplication.getInstance().imageLoader.displayImage(list.get(position), imageView);
        }
        if (mWPosition != -1) {
//            imageView.bindEventWithMLink(Config.MWS[mWPosition + position], new JSONObject(), null);
            imageView.bindEventWithParams(new ClickParamsBuilder(view.getContext(),Config.MWS[mWPosition+position]).mLinkParam(new JSONObject()).build());
        }
        view.addView(imageView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        return imageView;
    }

}
