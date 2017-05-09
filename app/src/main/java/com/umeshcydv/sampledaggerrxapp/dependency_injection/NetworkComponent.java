package com.umeshcydv.sampledaggerrxapp.dependency_injection;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by 17790 on 26/04/17.
 */
@Singleton
@Component(modules = NetworkModule.class)
public interface NetworkComponent {
    Retrofit getRetrofit();
}
