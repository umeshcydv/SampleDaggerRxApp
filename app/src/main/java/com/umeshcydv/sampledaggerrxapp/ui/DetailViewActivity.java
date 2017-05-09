package com.umeshcydv.sampledaggerrxapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeshcydv.sampledaggerrxapp.R;
import com.umeshcydv.sampledaggerrxapp.TransitionHelper;
import com.umeshcydv.sampledaggerrxapp.model.Album;

public class DetailViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        Album album = (Album) getIntent().getSerializableExtra("ITEM");

        ((TextView) findViewById(R.id.album_title)).setText("Album Name: " + album.getTitle());
        ((TextView) findViewById(R.id.album_artist)).setText("Artist: " + album.getArtistName());
        Glide.with(this).load(album.getImageUrl()).into((ImageView)findViewById(R.id.album_default_image));

//        // if we transition the status and navigation bar we have to wait till everything is available
//        TransitionHelper.fixSharedElementTransitionForStatusAndNavigationBar(this);
//        // set a custom shared element enter transition
//        TransitionHelper.setSharedElementEnterTransition(this, R.transition.detail_activity_shared_element_enter_transition);

    }
}
