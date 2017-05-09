package com.umeshcydv.sampledaggerrxapp.service;

import com.umeshcydv.sampledaggerrxapp.model.Album;

import java.util.List;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by 17790 on 26/04/17.
 */

public interface MusicAlbumService {

    @GET("api/music_albums")
    Observable<List<Album>> getMusicAlbums();
}
