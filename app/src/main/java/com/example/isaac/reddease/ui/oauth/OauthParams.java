package com.example.isaac.reddease.ui.oauth;

import com.example.isaac.reddease.data.prefs.OauthPrefs.OauthPrefs;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Isaac on 1/15/2018.
 */

public class OauthParams {

    @SerializedName(OauthPrefs.ACCESS_TOKEN)
    public String accessToken;

    @SerializedName(OauthPrefs.REFRESH_TOKEN)
    public String refreshToken;

    @SerializedName(OauthPrefs.TOKEN_TYPE)
    public String tokenType;

    @SerializedName(OauthPrefs.EXPIRATION)
    public String expiration;

    @SerializedName(OauthPrefs.SCOPE)
    public String scope;

    @Override
    public String toString() {
        return String.format("access_token = %s, refresh_token = %s", accessToken, refreshToken);
    }
}
