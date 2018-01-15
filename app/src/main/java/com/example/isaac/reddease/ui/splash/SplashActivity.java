package com.example.isaac.reddease.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.isaac.reddease.R;
import com.example.isaac.reddease.ui.base.BaseActivity;
import com.example.isaac.reddease.ui.login.LoginActivity;
import com.example.isaac.reddease.ui.main.MainActivity;

import butterknife.ButterKnife;

/**
 * Created by Isaac on 1/14/2018.
 */

public class SplashActivity extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setUp() {
        setContentView(R.layout.activity_main);
        setUnBinder(ButterKnife.bind(this));
        decideActivity();
    }

    private void decideActivity() {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
