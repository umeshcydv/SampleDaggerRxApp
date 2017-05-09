package com.umeshcydv.sampledaggerrxapp.base;

import com.umeshcydv.sampledaggerrxapp.interfaces.MusicAlbumViewInterface;
import com.umeshcydv.sampledaggerrxapp.model.Album;

import java.util.List;

import rx.Observer;

/**
 * Created by 17790 on 05/05/17.
 */

public class MusicAlbumPresenter extends BasePresenter implements Observer<List<Album>> {

    MusicAlbumViewInterface musicAlbumViewInterface;

    public MusicAlbumPresenter(MusicAlbumViewInterface musicAlbumViewInterface) {
        this.musicAlbumViewInterface = musicAlbumViewInterface;
    }


    @Override
    public void onCompleted() {
        musicAlbumViewInterface.onCompleted();
    }

    @Override
    public void onError(Throwable e) {
        musicAlbumViewInterface.onError(e.getMessage());
    }

    @Override
    public void onNext(List<Album> albums) {
        musicAlbumViewInterface.onAlbums(albums);
    }

    public void fetchAlbums() {
        unSubscribeAll();
        subscribe(musicAlbumViewInterface.getMusicAlbums(), MusicAlbumPresenter.this);
    }
}
