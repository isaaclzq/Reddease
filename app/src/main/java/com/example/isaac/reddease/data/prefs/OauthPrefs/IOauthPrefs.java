package com.example.isaac.reddease.data.prefs.OauthPrefs;

/**
 * Created by Isaac on 1/14/2018.
 */

public interface IOauthPrefs {

    // access_token
    void setAccessToken(String accessToken);
    String getAccessToken();

    // refresh_token
    void setRefreshToken(String refreshToken);
    String getRefreshToken();

    // token_type
    void setTokenType(String type);
    String getTokenTYpe();

    // expiration
    void setExpiration(String expiration);
    String getExpiration();

    // Scope
    void setScope(String scope);
    String getScope();

    boolean isLoggedIn();

    boolean isExpired();

    void clearAll();
}
