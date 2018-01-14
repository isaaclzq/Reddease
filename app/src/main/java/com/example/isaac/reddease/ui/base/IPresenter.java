package com.example.isaac.reddease.ui.base;

/**
 * Created by Isaac on 1/14/2018.
 */

public interface IPresenter<V extends IView> {

    void onAttach(V view);

    void onDetach();

    void handleApiError(String error);

    void setUserAsLoggedOut();
}
