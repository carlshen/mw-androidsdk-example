package com.magicwindow.deeplink.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.magicwindow.deeplink.R;
import com.magicwindow.deeplink.app.BaseAppCompatActivity;

import cn.salesuite.saf.inject.annotation.InjectView;
import cn.salesuite.saf.inject.annotation.OnClick;

public class ShopDoneActivity extends BaseAppCompatActivity {

    @InjectView
    Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_done);
        initViews();
    }

    private void initViews() {
        toolbar.setTitle(R.string.shop_pay_done);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @OnClick(id = R.id.go_home)
    public void goHome() {
        Intent intent = new Intent(mContext, MainActivity.class);
        intent.putExtra(MainActivity.PAGE,"dianshang");
        startActivity(intent);
    }
}
