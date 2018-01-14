package com.example.isaac.reddease.ui.base;

/**
 * Created by Isaac on 1/14/2018.
 */

public interface IView {

    void showLoading();

    void hideLoading();

    void openActivityOnTokenExpire();

    void onError(String message);

    void showMessage(String message);

    boolean isNetworkConnected();

    void hideKeyboard();
}
