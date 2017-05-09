package com.umeshcydv.sampledaggerrxapp;

import android.app.Application;

import com.umeshcydv.sampledaggerrxapp.dependency_injection.ApiComponent;
import com.umeshcydv.sampledaggerrxapp.dependency_injection.DaggerApiComponent;
import com.umeshcydv.sampledaggerrxapp.dependency_injection.DaggerNetworkComponent;
import com.umeshcydv.sampledaggerrxapp.dependency_injection.NetworkComponent;
import com.umeshcydv.sampledaggerrxapp.dependency_injection.NetworkModule;

/**
 * Created by 17790 on 26/04/17.
 */

public class MusicAlbumApplication extends Application {

    ApiComponent mApiComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        resolveDependency();

    }

    private void resolveDependency() {
        mApiComponent = DaggerApiComponent.builder()
                .networkComponent(getNetworkComponent()).build();

    }

    public NetworkComponent getNetworkComponent() {
        return DaggerNetworkComponent.builder().networkModule(new NetworkModule(Constant.BASE_URL)).build();
    }

    public ApiComponent getApiComponent() {
        return mApiComponent;
    }
}
