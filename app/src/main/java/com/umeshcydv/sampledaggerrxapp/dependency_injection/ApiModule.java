package com.umeshcydv.sampledaggerrxapp.dependency_injection;

import com.umeshcydv.sampledaggerrxapp.service.MusicAlbumService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by 17790 on 26/04/17.
 */

@Module
public class ApiModule {

    @Provides
    @CustomScope
    MusicAlbumService provideMusicAlbumService(Retrofit retrofit) {
        return retrofit.create(MusicAlbumService.class);
    }


}
