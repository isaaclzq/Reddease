package com.example.isaac.reddease.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.example.isaac.reddease.utils.NetworkUtils;

import butterknife.Unbinder;

/**
 * Created by Isaac on 1/14/2018.
 */

public abstract class BaseFragment extends Fragment implements IView{

    private BaseActivity mActivity;
    private Unbinder mUnBinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            this.mActivity = activity;
        }
    }

    protected abstract void setUp(View view);

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUp(view);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void openActivityOnTokenExpire() {

    }

    @Override
    public void onError(String message) {
        if (mActivity == null || message == null) {
            return;
        }
        mActivity.onError(message);
    }

    @Override
    public void showMessage(String message) {
        if (mActivity == null && message == null) {
            return;
        }
        mActivity.showMessage(message);
    }

    @Override
    public boolean isNetworkConnected() {
        if (mActivity == null) {
            return false;
        }
        return mActivity.isNetworkConnected();
    }

    @Override
    public void hideKeyboard() {
        if (mActivity == null) {
            return;
        }
        mActivity.hideKeyboard();
    }

    @Override
    public void onDetach() {
        mActivity = null;
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        if (mUnBinder != null) {
            mUnBinder.unbind();
            mUnBinder = null;
        }
        super.onDestroy();
    }

    public interface Callback {
        void onFragmentAttached();
        void onFragmentDetached(String tag);
    }
}
