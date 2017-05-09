package com.umeshcydv.sampledaggerrxapp.interfaces;

import com.umeshcydv.sampledaggerrxapp.model.Album;

import java.util.List;

import rx.Observable;

/**
 * Created by 17790 on 05/05/17.
 */

public interface MusicAlbumViewInterface {

    void onCompleted();

    void onError(String message);

    void onAlbums(List<Album> albums);

    Observable<List<Album>> getMusicAlbums();
}
