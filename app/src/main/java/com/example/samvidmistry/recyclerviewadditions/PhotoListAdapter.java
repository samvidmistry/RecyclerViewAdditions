package com.example.samvidmistry.recyclerviewadditions;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.samvidmistry.recyclerviewadditions.databinding.LayoutPhotoBinding;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.UrlConnectionDownloader;

import java.util.List;

/**
 * Created by samvidmistry on 11/19/16.
 */

public class PhotoListAdapter extends RecyclerView.Adapter<PhotoViewHolder> {
    private List<Photo> mPhotos;

    public PhotoListAdapter(List<Photo> photos) {
        mPhotos = photos;
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PhotoViewHolder(LayoutPhotoBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        ));
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {
        Picasso.with(holder.mBinding.image.getContext()).load(mPhotos.get(position).getThumbUrl())
                .into(holder.mBinding.image);
    }

    @Override
    public int getItemCount() {
        return mPhotos.size();
    }
}
