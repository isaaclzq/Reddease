package com.example.isaac.reddease.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.isaac.reddease.R;
import com.example.isaac.reddease.ui.base.BaseActivity;
import com.example.isaac.reddease.ui.oauth.OauthActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

/**
 * Created by Isaac on 1/14/2018.
 */

public class LoginActivity extends BaseActivity {

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
        startActivity(intent);
    }
}
