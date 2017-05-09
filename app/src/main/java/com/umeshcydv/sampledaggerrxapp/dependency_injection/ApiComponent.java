package com.umeshcydv.sampledaggerrxapp.dependency_injection;

import com.umeshcydv.sampledaggerrxapp.ui.MainActivity;

import dagger.Component;

/**
 * Created by 17790 on 26/04/17.
 */

@CustomScope
@Component(modules = ApiModule.class, dependencies = NetworkComponent.class)
public interface ApiComponent {

    void inject(MainActivity mainActivity);
}
