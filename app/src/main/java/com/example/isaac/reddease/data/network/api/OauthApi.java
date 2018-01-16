package com.example.isaac.reddease.data.network.api;

import com.example.isaac.reddease.ui.oauth.OauthParams;

import java.util.Observable;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by Isaac on 1/15/2018.
 */

public interface OauthApi {
    public static final String BASE_URL = "https://www.reddit.com/";
    public static final String AUTH_URL = "https://www.reddit.com/api/v1/authorize.compact?client_id=%s" +
            "&response_type=code&state=%s&redirect_uri=%s&" +
            "duration=permanent&scope=identity";

    public static final String CLIENT_ID = "lp22xNZtNg9jvw";
    public static final String REDIRECT_URI = "http://www.example.com/my_redirect";
    public static final String STATE = "123123123";
    public static final String ACCESS_TOKEN_URL = "https://www.reddit.com/";


    @FormUrlEncoded
    @POST("/api/v1/access_token/")
    Single<OauthParams> postForOauth2Tokens(@Header("Authorization") String auth,
                                            @Header("User-Agent") String name,
                                            @Field("grant_type") String grant_type,
                                            @Field("code") String code,
                                            @Field("redirect_uri") String redirect_uri);
}
