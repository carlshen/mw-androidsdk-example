package com.magicwindow.deeplink.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.magicwindow.deeplink.R;
import com.magicwindow.deeplink.adapter.BusinessListAdapter;
import com.magicwindow.deeplink.adapter.ImageAdapter;
import com.magicwindow.deeplink.app.BaseFragment;
import com.magicwindow.deeplink.config.Config;
import com.magicwindow.deeplink.prefs.AppPrefs;
import com.magicwindow.deeplink.ui.CircleIndicator;
import com.magicwindow.deeplink.ui.ListViewForScrollView;
import com.zxinsight.MWImageView;
import com.zxinsight.MarketingHelper;
import com.zxinsight.TrackAgent;

import java.util.ArrayList;

import cn.salesuite.saf.inject.Injector;
import cn.salesuite.saf.inject.annotation.InjectView;
import cn.salesuite.saf.log.L;

public class EBusinessFragment extends BaseFragment {

    @InjectView(id = R.id.viewpager)
    ViewPager viewPager;

    @InjectView(id = R.id.indicator)
    CircleIndicator indicator;

    @InjectView(id = R.id.top_1_layout)
    RelativeLayout top_1_layout;
    @InjectView(id = R.id.top_1)
    TextView top_1;
    @InjectView(id = R.id.business_icon01)
    MWImageView ic_top_1;

    @InjectView(id = R.id.top_2_layout)
    RelativeLayout top_2_layout;
    @InjectView(id = R.id.top_2)
    TextView top_2;
    @InjectView(id = R.id.business_icon02)
    MWImageView ic_top_2;

    @InjectView(id = R.id.top_3_layout)
    RelativeLayout top_3_layout;
    @InjectView(id = R.id.top_3)
    TextView top_3;
    @InjectView(id = R.id.business_icon03)
    MWImageView ic_top_3;

    @InjectView(id = R.id.top_4_layout)
    RelativeLayout top_4_layout;
    @InjectView(id = R.id.top_4)
    TextView top_4;
    @InjectView(id = R.id.business_icon04)
    MWImageView ic_top_4;

    @InjectView(id = R.id.top_5_layout)
    RelativeLayout top_5_layout;
    @InjectView(id = R.id.top_5)
    TextView top_5;
    @InjectView(id = R.id.business_icon05)
    MWImageView ic_top_5;

    @InjectView(id = R.id.top_6_layout)
    RelativeLayout top_6_layout;
    @InjectView(id = R.id.top_6)
    TextView top_6;
    @InjectView(id = R.id.business_icon06)
    MWImageView ic_top_6;

    @InjectView(id = R.id.top_7_layout)
    RelativeLayout top_7_layout;
    @InjectView(id = R.id.top_7)
    TextView top_7;
    @InjectView(id = R.id.business_icon07)
    MWImageView ic_top_7;

    @InjectView(id = R.id.top_8_layout)
    RelativeLayout top_8_layout;
    @InjectView(id = R.id.top_8)
    TextView top_8;
    @InjectView(id = R.id.business_icon08)
    MWImageView ic_top_8;

    @InjectView(id = R.id.business_img_1)
    MWImageView img_1;

    @InjectView(id = R.id.business_img_2)
    MWImageView img_2;

    @InjectView(id = R.id.business_img_3)
    MWImageView img_3;

    @InjectView(id = R.id.business_list)
    ListViewForScrollView businessList;

    private MarketingHelper marketingHelper;
    private int guideCount = 0;
    private FrameLayout guideFrameLayout;
    private AppPrefs appPrefs;

    @Override
    public void onStart() {
        super.onStart();
        appPrefs = AppPrefs.get(mContext);
        if (!appPrefs.getGuideEbusiness()) {
            addGuideImage();// 添加新手引导图片
        }
    }

    private void addGuideImage() {
        View view = mContext.getWindow().getDecorView().findViewById(R.id.root);//
        // 查找通过setContentView上的根布局
        if (view == null)
            return;

        ViewParent viewParent = view.getParent();
        if (viewParent instanceof FrameLayout) {
            guideFrameLayout = (FrameLayout) viewParent;
        }

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View guideView = inflater.inflate(R.layout.guide_ebusiness, null);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        guideView.setLayoutParams(params);
        final RelativeLayout guideLayout = (RelativeLayout) guideView.findViewById(R.id.guide_root);
        guideView.getBackground().setAlpha(80);
        final RelativeLayout topLayout = (RelativeLayout) guideView.findViewById(R.id.top);
        final RelativeLayout centerLayout = (RelativeLayout) guideView.findViewById(R.id.center);
        final RelativeLayout bottomLayout = (RelativeLayout) guideView.findViewById(R.id.bottom);
        ImageView iKnow1 = (ImageView) guideView.findViewById(R.id.guide_i_know_1);
        ImageView iKnow2 = (ImageView) guideView.findViewById(R.id.guide_i_know_2);
        ImageView experience = (ImageView) guideView.findViewById(R.id.guide_experience);
        iKnow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                topLayout.setVisibility(View.GONE);
                centerLayout.setVisibility(View.VISIBLE);
                bottomLayout.setVisibility(View.GONE);
            }
        });
        iKnow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                topLayout.setVisibility(View.GONE);
                centerLayout.setVisibility(View.GONE);
                bottomLayout.setVisibility(View.VISIBLE);
            }
        });
        experience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guideFrameLayout.removeView(guideView);
                appPrefs.setGuideEbusiness(true);
            }
        });
        guideFrameLayout.addView(guideView);// 添加引导图片
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            L.e("EBusinessFragment visible");
            TrackAgent.currentEvent().onPageStart("电商");

        } else {
            L.e("EBusinessFragment invisible");
            TrackAgent.currentEvent().onPageEnd("电商");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_business, container, false);
        Injector.injectInto(this, view);

        marketingHelper = MarketingHelper.currentMarketing(mContext);

        BusinessListAdapter adapter = new BusinessListAdapter(mContext);
        businessList.setAdapter(adapter);
        initViewPager();
        bindMW();

        return view;
    }

    private void initViewPager() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(R.drawable.business_banner001);
        list.add(R.drawable.business_banner002);
        list.add(R.drawable.business_banner003);
        list.add(R.drawable.business_banner004);
        viewPager.setAdapter(new ImageAdapter(23, list));
        indicator.setViewPager(viewPager);
    }

    private void bindMW() {

        //采用自定义方法
        if (marketingHelper.isActive(Config.MWS[27])) {
            top_1.setText(marketingHelper.getTitle(Config.MWS[27]));
            ic_top_1.bindEvent(Config.MWS[27]);
            top_1_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    marketingHelper.click(mContext, Config.MWS[27]);
                }
            });
        }

        //采用自定义方法
        if (marketingHelper.isActive(Config.MWS[28])) {
            top_2.setText(marketingHelper.getTitle(Config.MWS[28]));
            ic_top_2.bindEvent(Config.MWS[28]);
            top_2_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    marketingHelper.click(mContext, Config.MWS[28]);
                }
            });
        }

        //采用自定义方法
        if (marketingHelper.isActive(Config.MWS[29])) {
            top_3.setText(marketingHelper.getTitle(Config.MWS[29]));
            ic_top_3.bindEvent(Config.MWS[29]);
            top_3_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    marketingHelper.click(mContext, Config.MWS[29]);
                }
            });
        }

        //采用自定义方法
        if (marketingHelper.isActive(Config.MWS[30])) {
            top_4.setText(marketingHelper.getTitle(Config.MWS[30]));
            ic_top_4.bindEvent(Config.MWS[30]);
            top_4_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    marketingHelper.click(mContext, Config.MWS[30]);
                }
            });
        }


        //采用自定义方法
        if (marketingHelper.isActive(Config.MWS[31])) {
            top_5.setText(marketingHelper.getTitle(Config.MWS[31]));
            ic_top_5.bindEvent(Config.MWS[31]);
            top_5_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    marketingHelper.click(mContext, Config.MWS[31]);
                }
            });
        }

        Log.e("aaron", "parent id  = " + ic_top_5.getParent());
        Log.e("aaron", "root id  = " + ic_top_5.getRootView());

        //采用自定义方法
        if (marketingHelper.isActive(Config.MWS[32])) {
            top_6.setText(marketingHelper.getTitle(Config.MWS[32]));
            ic_top_6.bindEvent(Config.MWS[32]);
            top_6_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    marketingHelper.click(mContext, Config.MWS[32]);
                }
            });
        }

        //采用自定义方法
        if (marketingHelper.isActive(Config.MWS[33])) {
            top_7.setText(marketingHelper.getTitle(Config.MWS[33]));
            ic_top_7.bindEvent(Config.MWS[33]);
            top_7_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    marketingHelper.click(mContext, Config.MWS[33]);
                }
            });
        }

        //采用自定义方法
        if (marketingHelper.isActive(Config.MWS[34])) {
            top_8.setText(marketingHelper.getTitle(Config.MWS[34]));
            ic_top_8.bindEvent(Config.MWS[34]);
            top_8_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    marketingHelper.click(mContext, Config.MWS[34]);
                }
            });
        }


        //middle第1个魔窗位
        img_1.bindEvent(Config.MWS[35]);
        //middle第2个魔窗位
        img_2.bindEvent(Config.MWS[36]);
        //middle第3个魔窗位
        img_3.bindEvent(Config.MWS[37]);

    }

    @Override
    public void onPause() {
//        TrackAgent.currentEvent().onPageEnd("主页");

        super.onPause();
    }

    @Override
    public void onResume() {
//        TrackAgent.currentEvent().onPageStart("主页");

        super.onResume();
    }

}
