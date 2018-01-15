package com.example.isaac.reddease.ui.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.isaac.reddease.R;
import com.example.isaac.reddease.ui.base.BaseActivity;
import com.example.isaac.reddease.ui.login.LoginActivity;

import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setUp() {
        setContentView(R.layout.activity_main);
        setUnBinder(ButterKnife.bind(this));
        decideActivity();
    }

    private void decideActivity() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
