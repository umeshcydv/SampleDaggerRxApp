package com.umeshcydv.sampledaggerrxapp.ui;


import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.umeshcydv.sampledaggerrxapp.MusicAlbumApplication;
import com.umeshcydv.sampledaggerrxapp.R;
import com.umeshcydv.sampledaggerrxapp.adapters.AlbumAdapter;
import com.umeshcydv.sampledaggerrxapp.base.MusicAlbumPresenter;
import com.umeshcydv.sampledaggerrxapp.interfaces.MusicAlbumViewInterface;
import com.umeshcydv.sampledaggerrxapp.model.Album;
import com.umeshcydv.sampledaggerrxapp.service.MusicAlbumService;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class MainActivity extends AppCompatActivity implements MusicAlbumViewInterface, AlbumAdapter.AlbumClickListener {

    @Inject
    MusicAlbumService mMusicAlbumService;

    private MusicAlbumPresenter mMusicAlbumPresenter;
    private ProgressDialog mDialog;

    RecyclerView mRecyclerView;

    private AlbumAdapter mAlbumAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resolveDependency();

        configViews();
        mMusicAlbumPresenter = new MusicAlbumPresenter(MainActivity.this);
        mMusicAlbumPresenter.onCreate();
        mMusicAlbumPresenter.fetchAlbums();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mMusicAlbumPresenter.onResume();
    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAlbums(List<Album> albums) {
        mAlbumAdapter.addAlbums(albums);
    }

    @Override
    public Observable<List<Album>> getMusicAlbums() {
        return mMusicAlbumService.getMusicAlbums();
    }

    @Override
    public void onClick(int position, String name, View itemView) {
        startTransition(itemView, mAlbumAdapter.getItem(position));
    }

    private void resolveDependency() {
        ((MusicAlbumApplication) getApplication())
                .getApiComponent()
                .inject(MainActivity.this);
    }

    private void configViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAlbumAdapter = new AlbumAdapter(this, getLayoutInflater());
        mRecyclerView.setAdapter(mAlbumAdapter);
    }

    private void startTransition(View view, Album album) {
        Intent i = new Intent(MainActivity.this, DetailViewActivity.class);
        i.putExtra("ITEM", album);

        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, view.findViewById(R.id.album_default_image), "detail_image");


        Pair<View, String>[] transitionPairs = new Pair[5];
        //transitionPairs[0] = Pair.create(findViewById(R.id.toolbar), "toolbar"); // Transition the Toolbar
        transitionPairs[0] = Pair.create(view.findViewById(R.id.album_default_image), "detail_image"); // Transition the content_area (This will be the content area on the detail screen)
        // We also want to transition the status and navigation bar barckground. Otherwise they will flicker
        transitionPairs[1] = Pair.create(findViewById(android.R.id.statusBarBackground), Window.STATUS_BAR_BACKGROUND_TRANSITION_NAME);
        transitionPairs[2] = Pair.create(findViewById(android.R.id.navigationBarBackground), Window.NAVIGATION_BAR_BACKGROUND_TRANSITION_NAME);
        transitionPairs[3] = Pair.create(view.findViewById(R.id.album_title), "title");
        transitionPairs[4] = Pair.create(view.findViewById(R.id.album_default_image), "artist");
        Bundle b = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, transitionPairs).toBundle();

        ActivityCompat.startActivity(MainActivity.this, i, b);
    }
}
