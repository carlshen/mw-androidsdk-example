package com.magicwindow.deeplink.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.magicwindow.deeplink.R;
import com.magicwindow.deeplink.app.BaseAppCompatActivity;
import com.magicwindow.deeplink.config.Config;
import cn.magicwindow.MarketingHelper;
import cn.magicwindow.mlink.annotation.MLinkRouter;

import cn.salesuite.saf.inject.annotation.InjectView;
@MLinkRouter(keys = "NewsDetail")
public class NewsDetailActivity extends BaseAppCompatActivity {
    @InjectView
    Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        initToolBar();
    }


    private void initToolBar() {

        toolbar.setTitle(R.string.news_detail);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(MarketingHelper.currentMarketing(this).isActive(Config.MW_NEWS_SHARE)){
            getMenuInflater().inflate(R.menu.menu_shop_detail, menu);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_share) {
            if (MarketingHelper.currentMarketing(this).isActive(Config.MW_NEWS_SHARE)) {
                MarketingHelper.currentMarketing(this).click(this, Config.MW_NEWS_SHARE);
            } else {
                toast(R.string.share_closed);
            }
        }

        return super.onOptionsItemSelected(item);

    }
}
