package com.example.isaac.reddease.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.widget.TextView;

import com.example.isaac.reddease.R;
import com.example.isaac.reddease.ReddeaseApplication;
import com.example.isaac.reddease.data.network.api.OauthApi;
import com.example.isaac.reddease.data.prefs.OauthPrefs.OauthPrefs;
import com.example.isaac.reddease.ui.base.BaseActivity;
import com.example.isaac.reddease.ui.oauth.OauthActivity;
import com.example.isaac.reddease.ui.oauth.OauthParams;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Isaac on 1/14/2018.
 */

public class LoginActivity extends BaseActivity {

    public static final int OAUTH_REQ = 1;

    @BindView(R.id.oauth_info)
    TextView mOauthInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setUp() {
        setContentView(R.layout.login_activity);
        setUnBinder(ButterKnife.bind(this));

        OauthPrefs oauthPrefs = new OauthPrefs(LoginActivity.this);

        if (oauthPrefs.isLoggedIn()) {
            if (oauthPrefs.isExpired()) {
                refreshToken();
            } else {
                mOauthInfo.setText(oauthPrefs.toString());
            }
        } else {
            onClick();
        }
    }

    private void refreshToken() {

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
        String authString = OauthApi.CLIENT_ID + ":";
        String encodedAuthString = Base64.encodeToString(authString.getBytes(), Base64.NO_WRAP);

        OauthApi oauthApi = ReddeaseApplication.get(this).getOauthRetrofit().create(OauthApi.class);
        oauthApi.postForOauth2Tokens("Basic " +encodedAuthString,
                "Sample App",
                "authorization_code", code, OauthApi.REDIRECT_URI)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<OauthParams>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(OauthParams oauthParams) {
                        OauthPrefs oauthPrefs = new OauthPrefs(LoginActivity.this);
                        oauthPrefs.updateOauthInfo(oauthParams);
                        mOauthInfo.setText(oauthParams.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        mOauthInfo.setText(e.getMessage());
                    }
                });

    }
}
