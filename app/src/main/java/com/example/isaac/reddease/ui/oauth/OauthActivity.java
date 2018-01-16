package com.example.isaac.reddease.ui.oauth;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Toolbar;

import com.example.isaac.reddease.R;
import com.example.isaac.reddease.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Isaac on 1/15/2018.
 */

public class OauthActivity extends BaseActivity implements IOauthView{

    @BindView(R.id.oauth_webview)
    WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setUp() {
        setContentView(R.layout.oauth_acitivty);

        ActionBar toolbar = getSupportActionBar();
        if (toolbar != null) {
            toolbar.setHomeButtonEnabled(true);
            toolbar.setDisplayHomeAsUpEnabled(true);
        }

        this.setUnBinder(ButterKnife.bind(this));

        this.initWebView();
    }

    @Override
    public void initWebView() {
        mWebView.loadUrl("https://www.google.com");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
