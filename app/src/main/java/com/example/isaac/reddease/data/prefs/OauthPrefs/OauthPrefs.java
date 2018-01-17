package com.example.isaac.reddease.data.prefs.OauthPrefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.isaac.reddease.ui.oauth.OauthParams;

/**
 * Created by Isaac on 1/14/2018.
 */

public class OauthPrefs implements IOauthPrefs {

    public static final String OAUTHPREFS_KEY = "Oauth";
    public static final String ACCESS_TOKEN = "access_token";
    public static final String REFRESH_TOKEN = "refresh_token";
    public static final String TOKEN_TYPE = "token_type";
    public static final String EXPIRATION = "expires_in";
    public static final String SCOPE = "scope";

    SharedPreferences mOauthSharedPrefs;
    Context mContext;

    public OauthPrefs(Context context) {
        mContext = context;
        mOauthSharedPrefs = context.getSharedPreferences(OauthPrefs.OAUTHPREFS_KEY, Context.MODE_PRIVATE);
    }

    public void updateOauthInfo(OauthParams params) {
        if (params.accessToken != null) {
            setAccessToken(params.accessToken);
        }

        if (params.refreshToken != null) {
            setRefreshToken(params.refreshToken);
        }

        if (params.scope != null) {
            setRefreshToken(params.scope);
        }

        if (params.expiration != null) {
            long epochNow = System.currentTimeMillis() / 1000;
            long epochExpiration = epochNow + Long.parseLong(params.expiration);
            setRefreshToken(Long.toString(epochExpiration));
        }

        if (params.tokenType != null) {
            setTokenType(params.tokenType);
        }
    }

    @Override
    public boolean isLoggedIn() {
        return getAccessToken() != null && getRefreshToken() != null;
    }

    @Override
    public boolean isExpired() {
        long epochNow = System.currentTimeMillis() / 1000;
        String expirationString = getExpiration();
        long expiration = 0;
        if (expirationString != null) {
            expiration = Long.parseLong(expirationString);
            return epochNow > expiration;
        }
        return true;
    }

    @Override
    public void clearAll() {
        mOauthSharedPrefs.edit().clear().commit();
    }

    @Override
    public void setAccessToken(String accessToken) {
        mOauthSharedPrefs.edit()
                .putString(OauthPrefs.ACCESS_TOKEN, accessToken)
                .commit();
    }

    @Override
    public String getAccessToken() {
        return mOauthSharedPrefs.getString(OauthPrefs.ACCESS_TOKEN, null);
    }

    @Override
    public void setRefreshToken(String refreshToken) {
        mOauthSharedPrefs.edit()
                .putString(OauthPrefs.REFRESH_TOKEN, refreshToken)
                .commit();
    }

    @Override
    public String getRefreshToken() {
        return mOauthSharedPrefs.getString(OauthPrefs.REFRESH_TOKEN, null);
    }

    @Override
    public void setTokenType(String type) {
        mOauthSharedPrefs.edit()
                .putString(OauthPrefs.TOKEN_TYPE, type)
                .commit();
    }

    @Override
    public String getTokenTYpe() {
        return mOauthSharedPrefs.getString(OauthPrefs.TOKEN_TYPE, null);
    }

    @Override
    public void setExpiration(String expiration) {
        mOauthSharedPrefs.edit()
                .putString(OauthPrefs.EXPIRATION, expiration)
                .commit();
    }

    @Override
    public String getExpiration() {
        return mOauthSharedPrefs.getString(OauthPrefs.EXPIRATION, null);
    }

    @Override
    public void setScope(String scope) {
        mOauthSharedPrefs.edit()
                .putString(OauthPrefs.SCOPE, scope)
                .commit();
    }

    @Override
    public String getScope() {
        return mOauthSharedPrefs.getString(OauthPrefs.SCOPE, null);
    }

    @Override
    public String toString() {
        return String.format("access_token = %s, refresh_token = %s", getAccessToken(), getRefreshToken());
    }
}
