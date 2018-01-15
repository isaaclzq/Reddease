package com.example.isaac.reddease.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.isaac.reddease.R;
import com.example.isaac.reddease.ui.base.BaseFragment.Callback;
import com.example.isaac.reddease.utils.NetworkUtils;

import butterknife.Unbinder;
import timber.log.Timber;

/**
 * Created by Isaac on 1/14/2018.
 */

public abstract class BaseActivity extends AppCompatActivity
        implements IView, Callback {

    private Unbinder mUnBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setUp();
    }

    protected abstract void setUp();

    @Override
    protected void onDestroy() {
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        super.onDestroy();
    }

    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }

    private void showSnackBar(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView
                .findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, R.color.white));
        snackbar.show();
    }


    @Override
    public void showLoading() {
        Timber.i("showing loading");
    }

    @Override
    public void hideLoading() {
        Timber.i("hiding loading");
    }

    /**
     * redirect the user to log in page
     */
    @Override
    public void openActivityOnTokenExpire() {
        Timber.i("error occurred at token expiration");
        finish();
    }

    @Override
    public void onError(String message) {
        if (message != null) {
            showSnackBar(message);
        } else {
            showSnackBar(getString(R.string.internal_error));
        }
    }

    @Override
    public void showMessage(String message) {
        if (message == null) {
            Toast.makeText(this, getString(R.string.internal_error), Toast.LENGTH_LONG).show();
            return;
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(this);
    }

    @Override
    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }
}
