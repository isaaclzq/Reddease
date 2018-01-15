package com.example.isaac.reddease.ui.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.isaac.reddease.R;
import com.example.isaac.reddease.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Isaac on 1/14/2018.
 */

public class LoginActivity extends BaseActivity {

    @BindView(R.id.login_text)
    TextView mText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void testing() {
        mText.setText("Login");
    }

    @Override
    protected void setUp() {
        setContentView(R.layout.login_activity);
        setUnBinder(ButterKnife.bind(this));
        testing();
    }
}
