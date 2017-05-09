package com.umeshcydv.sampledaggerrxapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeshcydv.sampledaggerrxapp.R;
import com.umeshcydv.sampledaggerrxapp.model.Album;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 17790 on 05/05/17.
 */

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {

    private AlbumClickListener mAlbumClickListener;
    private LayoutInflater mLayoutInflater;
    private List<Album> mAlbumList;

    public AlbumAdapter(AlbumClickListener albumClickListener, LayoutInflater layoutInflater) {
        this.mAlbumClickListener = albumClickListener;
        this.mLayoutInflater = layoutInflater;
        this.mAlbumList = new ArrayList<>();
    }

    @Override
    public AlbumAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.album_row, parent, false));
    }

    @Override
    public void onBindViewHolder(AlbumAdapter.ViewHolder holder, int position) {
        holder.onBind(position, mAlbumList.get(position));
    }

    @Override
    public int getItemCount() {
        return mAlbumList.size();
    }

    public Album getItem(int position) {
        return mAlbumList.get(position);
    }

    public void addAlbums(List<Album> albums) {
        mAlbumList.addAll(albums);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout mainContainer;

        ImageView defaultImage;

        TextView albumTitle;

        TextView albumArtist;

        public ViewHolder(View itemView) {
            super(itemView);
            albumArtist = (TextView) itemView.findViewById(R.id.album_artist);
            albumTitle = (TextView) itemView.findViewById(R.id.album_title);
            mainContainer = (LinearLayout) itemView.findViewById(R.id.main_container);
            defaultImage = (ImageView) itemView.findViewById(R.id.album_default_image);
        }

        public void onBind(final int position, final Album album) {
            albumTitle.setText("Album Name: " + album.getTitle());
            albumArtist.setText("Artist: " + album.getArtistName());
            Glide.with(itemView.getContext()).load(album.getImageUrl()).into(defaultImage);
            mainContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAlbumClickListener.onClick(position, album.getTitle(), itemView);
                }
            });
        }
    }


    public interface AlbumClickListener {
        void onClick(int position, String name, View itemView);
    }
}
