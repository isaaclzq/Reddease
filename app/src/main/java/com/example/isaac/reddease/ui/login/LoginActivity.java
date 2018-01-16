package com.example.isaac.reddease.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.isaac.reddease.R;
import com.example.isaac.reddease.ui.base.BaseActivity;
import com.example.isaac.reddease.ui.oauth.OauthActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

/**
 * Created by Isaac on 1/14/2018.
 */

public class LoginActivity extends BaseActivity {

    public static final int OAUTH_REQ = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setUp() {
        setContentView(R.layout.login_activity);
        setUnBinder(ButterKnife.bind(this));
    }

    @OnClick(R.id.login_btn)
    public void onClick() {
        Intent intent = new Intent(LoginActivity.this, OauthActivity.class);
        startActivityForResult(intent, OAUTH_REQ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case OAUTH_REQ:
                String code = data.getExtras().getString("code");
                if (code == OauthActivity.ERROR) {

                } else {
                    retrieveTokens(code);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void retrieveTokens(String code) {
        Timber.i(code);
    }
}
