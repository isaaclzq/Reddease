package com.example.isaac.reddease.ui.oauth;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.isaac.reddease.R;
import com.example.isaac.reddease.data.network.api.OauthApi;
import com.example.isaac.reddease.ui.base.BaseActivity;
import com.example.isaac.reddease.ui.login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by Isaac on 1/15/2018.
 */

public class OauthActivity extends BaseActivity implements IOauthView {

    public static final String ERROR = "-1";

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

        String url = String.format(OauthApi.AUTH_URL, OauthApi.CLIENT_ID, OauthApi.STATE, OauthApi.REDIRECT_URI);

        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                Uri uri = Uri.parse(url);
                Intent intent = new Intent();
                if (url.contains("&code=")) {
                    String state = uri.getQueryParameter("state");
                    if (state.equals(OauthApi.STATE)){
                        String code = uri.getQueryParameter("code");
                        intent.putExtra("code", code);
                    } else {
                        intent.putExtra("code", ERROR);
                    }
                    OauthActivity.this.setResult(LoginActivity.OAUTH_REQ, intent);
                    finish();
                } else if (url.contains("error")){
                    intent.putExtra("code", ERROR);
                    OauthActivity.this.setResult(LoginActivity.OAUTH_REQ, intent);
                    finish();
                }
            }
        });

        mWebView.loadUrl(url);
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
