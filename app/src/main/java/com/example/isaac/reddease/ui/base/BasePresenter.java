package com.example.isaac.reddease.ui.base;

/**
 * Created by Isaac on 1/14/2018.
 */

public class BasePresenter<V extends IView> implements IPresenter<V> {
    @Override
    public void onAttach(V view) {
        
    }

    @Override
    public void onDetach() {

    }

    @Override
    public void handleApiError(String error) {

    }

    @Override
    public void setUserAsLoggedOut() {

    }
}
