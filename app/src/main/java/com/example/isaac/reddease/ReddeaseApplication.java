package com.example.isaac.reddease;

import android.app.Application;
import android.content.Context;

import com.example.isaac.reddease.data.network.api.OauthApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * Created by Isaac on 1/14/2018.
 */

public class ReddeaseApplication extends Application {

    public static Context get(Context context) {
        return (ReddeaseApplication) context;
    }

    private Retrofit oauthRetrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        // gson
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        // timber
        Timber.plant(new Timber.DebugTree());

        // interceptor && httpClient
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Timber.i(message);
            }
        });

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl(OauthApi.BASE_URL)
                .build();

        oauthRetrofit = new Retrofit.Builder()
                .baseUrl(OauthApi.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public Retrofit getOauthRetrofit() {
        return this.oauthRetrofit;
    }
}
